package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class LocalMailController {

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

		flash.message = message(code: 'default.created.message', args: [message(code: 'localMail.label', default: 'LocalMail'), localMailInstance.id])
        redirect(action: "show", id: localMailInstance.id)
    }

    def show() {
        def localMailInstance = LocalMail.get(params.id)
        if (!localMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'localMail.label', default: 'LocalMail'), params.id])
            redirect(action: "list")
            return
        }

        [localMailInstance: localMailInstance]
    }

    def edit() {
        def localMailInstance = LocalMail.get(params.id)
        if (!localMailInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localMail.label', default: 'LocalMail'), params.id])
            redirect(action: "list")
            return
        }

        [localMailInstance: localMailInstance]
    }

    def update() {
        def localMailInstance = LocalMail.get(params.id)
        if (!localMailInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localMail.label', default: 'LocalMail'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (localMailInstance.version > version) {
                localMailInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'localMail.label', default: 'LocalMail')] as Object[],
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

		flash.message = message(code: 'default.updated.message', args: [message(code: 'localMail.label', default: 'LocalMail'), localMailInstance.id])
        redirect(action: "show", id: localMailInstance.id)
    }

    def delete() {
        def localMailInstance = LocalMail.get(params.id)
        if (!localMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'localMail.label', default: 'LocalMail'), params.id])
            redirect(action: "list")
            return
        }

        try {
            localMailInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'localMail.label', default: 'LocalMail'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'localMail.label', default: 'LocalMail'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
	def search={
		def query = params.q
	//	log.error(" query: "+query);

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
			return [searchError: true]
		}
	}
}
