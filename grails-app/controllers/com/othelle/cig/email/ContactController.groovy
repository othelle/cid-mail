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
		//log.info("|"+params.q+"|");
		def query = Utilities.getTrim(params.q)
		//log.info("|"+query+"|");
		if (!query) {
			//flash.message = message(code: 'search.emply.message', default: 'Not found contact');
			//	log.error("Not found contact");
			return
		}

		try {
			def searchResult = Contact.search(query, params)
			//log.info("searchResult: "+searchResult);
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
	def reindex={
		//Contacts	
		def contacts=Contact.getAll();
		def sizeCon=contacts.size();
		log.info("size :"+sizeCon);
		def i=1;
		
		for (contact in contacts) {
			log.info("contact=  "+contact+ "    "+i);
			contact.reindex()
			i++;

		}
		
		//Collection
		def collections=Collection.getAll();
		def sizeCol=collections.size();
		log.info("size :"+sizeCol);
		def ci=1;
		
		for (collection in collections) {
			log.info("Collection=  "+collection.getName()+ "    "+ci);
			collection.reindex()
			ci++;

		}
/*
	
		//CheckMail.reindex()
		def checkMails=CheckMail.getAll();
		def sizecheckMails=checkMails.size();
		log.info("size :"+sizecheckMails);
		def ci2=1;
		
		for (checkMail in checkMails) {
			log.info("checkMails=  "+checkMail.getUid()+ "    "+ci2);
			checkMail.reindex()
			ci2++;

		}

		//LocalMail.reindex()
		def localMails=LocalMail.getAll();
		def sizelocalMails=localMails.size();
		log.info("size :"+sizelocalMails);
		def cii=1;
		
		for (localMail in localMails) {
			log.info("localMail=  "+localMail.getDateCreated()+ "    "+cii);
			localMail.reindex()
			cii++;

		}*/
		flash.message =message(code: 'search.not.found.messagedf', default: 'База проиндексированна');
		
		redirect(uri: "/")
		
	}
}
