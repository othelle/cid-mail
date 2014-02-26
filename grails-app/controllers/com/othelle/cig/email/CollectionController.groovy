package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class CollectionController {
	static scaffold = true
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def LogSenderService logSenderService
	
	def create() {
		//params.code=params.password
		[collectionInstance: new Collection(params)]
	}

	def save() {
		//��� ��� �������� ���� code
		params.code=params.password
		def collectionInstance = new Collection(params)
		if (!collectionInstance.save(flush: true)) {
			render(view: "create", model: [collectionInstance: collectionInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'collection.label', default: 'Collection'),
			collectionInstance.id
		])
		redirect(action: "show", id: collectionInstance.id)
	}
	def update() {
		def collectionInstance = Collection.get(params.id)
		if (!collectionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'collection.label', default: 'Collection'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (collectionInstance.version > version) {
				collectionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'collection.label', default: 'Collection')] as Object[],
						"Another user has updated this Collection while you were editing")
				render(view: "edit", model: [collectionInstance: collectionInstance])
				return
			}
		}

		//��� ��� �������� ���� code
		params.code=params.password
		collectionInstance.properties = params

		if (!collectionInstance.save(flush: true)) {
			render(view: "edit", model: [collectionInstance: collectionInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'collection.label', default: 'Collection'),
			collectionInstance.id
		])
		redirect(action: "show", id: collectionInstance.id)
	}

	def delete() {
		def collectionInstance = Collection.get(params.id)
		if (!collectionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'collection.label', default: 'Collection'),
				params.id
			])
			redirect(action: "list")
			return
		}

		try {
			collectionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'collection.label', default: 'Collection'),
				params.id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'collection.label', default: 'Collection'),
				params.id
			])
			redirect(action: "show", id: params.id)
			log.error(e)
		}
	}
	def search= {
		def query = params.q

		if (!query) {
			//flash.message = message(code: 'search.emply.message', default: 'Not found contact');
			//log.debug("Not found collection");
			return
		}

		try {
			def searchResult = Collection.search(query, params)
			if (!searchResult.total) {
				flash.message = message(code: 'search.not.found.message', default: 'Not found contact');
				//log.error("Not found contact");
				return
			}
			return [searchResult: searchResult]
		} catch (Exception e) {
		logSenderService.sendLog("(CollectionController) Error search <br />"+e.getLocalizedMessage())
			return [searchError: true]
		}
	}
}
