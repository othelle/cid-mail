package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class CheckMailController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

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

		flash.message = message(code: 'default.created.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), checkMailInstance.id])
        redirect(action: "show", id: checkMailInstance.id)
    }

    def show() {
        def checkMailInstance = CheckMail.get(params.id)
        if (!checkMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), params.id])
            redirect(action: "list")
            return
        }

        [checkMailInstance: checkMailInstance]
    }

    def edit() {
        def checkMailInstance = CheckMail.get(params.id)
        if (!checkMailInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), params.id])
            redirect(action: "list")
            return
        }

        [checkMailInstance: checkMailInstance]
    }

    def update() {
        def checkMailInstance = CheckMail.get(params.id)
        if (!checkMailInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (checkMailInstance.version > version) {
                checkMailInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'checkMail.label', default: 'CheckMail')] as Object[],
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

		flash.message = message(code: 'default.updated.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), checkMailInstance.id])
        redirect(action: "show", id: checkMailInstance.id)
    }

    def delete() {
        def checkMailInstance = CheckMail.get(params.id)
        if (!checkMailInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), params.id])
            redirect(action: "list")
            return
        }

        try {
            checkMailInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'checkMail.label', default: 'CheckMail'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
