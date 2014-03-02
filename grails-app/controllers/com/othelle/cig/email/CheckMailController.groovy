package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class CheckMailController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def LogSenderService logSenderService
	
	def index() {
		redirect(action: "list", params: params)
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[checkMailInstanceList: CheckMail.list(params), checkMailInstanceTotal: CheckMail.count()]
	}

	def create() {
		[checkMailInstance: new CheckMail(params)]
	}

	def save() {
		def checkMailInstance = new CheckMail(params)
		if (!checkMailInstance.save(flush: true)) {
			render(view: "create", model: [checkMailInstance: checkMailInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'checkMail.label', default: 'CheckMail'),
			checkMailInstance.id
		])
		redirect(action: "show", id: checkMailInstance.id)
	}

	def show() {
		def checkMailInstance = CheckMail.get(params.id)
		if (!checkMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'checkMail.label', default: 'CheckMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[checkMailInstance: checkMailInstance]
	}

	def edit() {
		def checkMailInstance = CheckMail.get(params.id)
		if (!checkMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'checkMail.label', default: 'CheckMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		[checkMailInstance: checkMailInstance]
	}

	def update() {
		def checkMailInstance = CheckMail.get(params.id)
		if (!checkMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'checkMail.label', default: 'CheckMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (checkMailInstance.version > version) {
				checkMailInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'checkMail.label', default: 'CheckMail')] as Object[],
						"Another user has updated this CheckMail while you were editing")
				render(view: "edit", model: [checkMailInstance: checkMailInstance])
				return
			}
		}

		checkMailInstance.properties = params

		if (!checkMailInstance.save(flush: true)) {
			render(view: "edit", model: [checkMailInstance: checkMailInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'checkMail.label', default: 'CheckMail'),
			checkMailInstance.id
		])
		redirect(action: "show", id: checkMailInstance.id)
	}

	def delete() {
		def checkMailInstance = CheckMail.get(params.id)
		if (!checkMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'checkMail.label', default: 'CheckMail'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			checkMailInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'checkMail.label', default: 'CheckMail'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			logSenderService.sendLog("(CheckMailController:delete) DataIntegrityViolationException <br />"+e.getLocalizedMessage())
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'checkMail.label', default: 'CheckMail'),
				params.id
			])
			redirect(action: "show", id: params.id)
		}
	}
	def deleteByFlagNew() {
		def flagNew=Boolean.FALSE
		def checkInstanceList = CheckMail.findAllByFlagNew(flagNew)
		if (!checkInstanceList) {
			flash.message = message(code: 'checkMail.not.found.message')
			redirect(action: "list")
			return
		}
		def flag=false
		def count=0
		log.info("Find checkMail "+ checkInstanceList.size())
		for(def checkMailInstance:checkInstanceList){
			try {
				checkMailInstance.delete(flush: true)
				log.info("Deleted check mail "+ checkMailInstance.id)
				count++
			}
			catch (DataIntegrityViolationException e) {
				logSenderService.sendLog("(CheckMailController:deleteByFlagNew) DataIntegrityViolationException <br />"+e.getLocalizedMessage())
				flash.message = message(code: 'checkMail.not.deleted.message', args: [checkInstanceList])
				flag=true
				redirect(action: "show", id: checkInstanceList.id)
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
		flash.message = message(code: 'checkMail.deleted.message', args: [checkInstanceList.id])
		redirect(action: "list")
	}
	def search={
		
		def query = Utilities.getTrim(params.q)

		if (!query) {
			//flash.message = message(code: 'search.emply.message', default: 'Not found contact');
			//log.error("Not found contact");
			return
		}

		try {
			def searchResult = CheckMail.search(query, params)
			if (!searchResult.total) {
				flash.message = message(code: 'search.not.found.message', default: 'Not found contact');
				//log.error("Not found contact");
				return
			}

			return [searchResult: searchResult]
		} catch (Exception e) {
			return [searchError: true]
		}
	}
}
