package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class ContactController {
	static scaffold = true
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def LogSenderService logSenderService

	def update() {
		def contactInstance = Contact.get(params.id)
		if (!contactInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'contact.label', default: 'Contact'),
				params.id
			])
			redirect(action: "list")
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (contactInstance.version > version) {
				contactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'contact.label', default: 'Contact')] as Object[],
						"Another user has updated this Contact while you were editing")
				render(view: "edit", model: [contactInstance: contactInstance])
				return
			}
		}

		contactInstance.properties = params

		if (!contactInstance.save(flush: true)) {
			render(view: "edit", model: [contactInstance: contactInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'contact.label', default: 'Contact'),
			contactInstance.id
		])
		redirect(action: "show", id: contactInstance.id)
	}
	def search={
		def query = params.q
	//	log.error(" query: "+query);

		if (!query) {
			//flash.message = message(code: 'search.emply.message', default: 'Not found contact');
		//	log.error("Not found contact");
			return
		}

		try {
			def searchResult = Contact.search(query, params)
			if (!searchResult.total) {
				flash.message = message(code: 'search.not.found.message', default: 'Not found contact');
			//	log.error("Not found contact");
				return
			}
		//	log.error(" searchResult.totalCont: "+searchResult.total);
			return [searchResult: searchResult]
		} catch (Exception e) {
			logSenderService.sendLog("(ContactController) Error search <br />"+e.getLocalizedMessage())
			return [searchError: true]
		}
	}
}
