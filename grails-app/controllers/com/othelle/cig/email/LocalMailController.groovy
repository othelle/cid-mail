package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class LocalMailController {
	def LogSenderService logSenderService
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[localMailInstanceList: LocalMail.list(params), localMailInstanceTotal: LocalMail.count()]
	}

	def create() {
		[localMailInstance: new LocalMail(params)]
	}

	def save() {
		def localMailInstance = new LocalMail(params)
		if (!localMailInstance.save(flush: true)) {
			render(view: "create", model: [localMailInstance: localMailInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'localMail.label', default: 'LocalMail'),
			localMailInstance.id
		])
		redirect(action: "show", id: localMailInstance.id)
	}

	def show() {
		def localMailInstance = LocalMail.get(params.id)
		if (!localMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'localMail.label', default: 'LocalMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[localMailInstance: localMailInstance]
	}

	def edit() {
		def localMailInstance = LocalMail.get(params.id)
		if (!localMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'localMail.label', default: 'LocalMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[localMailInstance: localMailInstance]
	}

	def update() {
		def localMailInstance = LocalMail.get(params.id)
		if (!localMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'localMail.label', default: 'LocalMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (localMailInstance.version > version) {
				localMailInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'localMail.label', default: 'LocalMail')] as Object[],
						"Another user has updated this LocalMail while you were editing")
				render(view: "edit", model: [localMailInstance: localMailInstance])
				return
			}
		}

		localMailInstance.properties = params

		if (!localMailInstance.save(flush: true)) {
			render(view: "edit", model: [localMailInstance: localMailInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'localMail.label', default: 'LocalMail'),
			localMailInstance.id
		])
		redirect(action: "show", id: localMailInstance.id)
	}

	def deleteByFlagSend() {
		def flagSend=Boolean.FALSE
		def localMailInstanceList = LocalMail.findAllByFlagSend(flagSend)

		if (!localMailInstanceList) {
			flash.message = message(code: 'localMail.not.found.message')
			redirect(action: "list")
			return
		}
		def flag=false
		def count=0
		log.info("Find local mail "+ localMailInstanceList.size())
		for(def localMailInstance:localMailInstanceList){
			try {
				localMailInstance.delete(flush: true)
				log.info("Deleted local mail "+ localMailInstance.id)
			}
			catch (DataIntegrityViolationException e) {
				flash.message = message(code: 'localMail.not.deleted.message', args: [localMailInstance])
				flag=true
				redirect(action: "show", id: localMailInstance.id)
			}
		}
		if (count>0) {
			log.info("Deledet "+ count+" items")
		}
		if (!flag) {
			log.info("All is well!")
		} else {
			log.info("Is not well!")
		}
		flash.message = message(code: 'localMail.deleted.message', args: [localMailInstanceList.id])
		redirect(action: "list")
	}

	def delete() {
		def localMailInstance = LocalMail.get(params.id)
		if (!localMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'localMail.label', default: 'LocalMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			localMailInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'localMail.label', default: 'LocalMail'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'localMail.label', default: 'LocalMail'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}
	def search={
		//log.info("params.q="+params.q)
		def query = params.q
		//.trim().replaceAll("  ", " ")
		//log.info("query="+query)

		if (!query) {
			//flash.message = message(code: 'search.emply.message', default: 'Not found contact');
			//log.error("Not found contact");
			return
		}

		try {
			def searchResult = LocalMail.search(query, params)
			if (!searchResult.total) {
				flash.message = message(code: 'search.not.found.message', default: 'Not found contact');
				//	log.error("Not found contact");
				return
			}

			return [searchResult: searchResult]
		} catch (Exception e) {
			logSenderService.sendLog("(LocalMailController) Error search <br />"+e.getLocalizedMessage())
			return [searchError: true]
		}
	}
}
