package com.othelle.cig.email

import org.springframework.dao.DataIntegrityViolationException

class CollectionController {
    static scaffold = true
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def update() {
        def collectionInstance = Collection.get(params.id)
        if (!collectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'collection.label', default: 'Collection'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (collectionInstance.version > version) {
                collectionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'collection.label', default: 'Collection')] as Object[],
                          "Another user has updated this Collection while you were editing")
                render(view: "edit", model: [collectionInstance: collectionInstance])
                return
            }
        }

        collectionInstance.properties = params

        if (!collectionInstance.save(flush: true)) {
            render(view: "edit", model: [collectionInstance: collectionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'collection.label', default: 'Collection'), collectionInstance.id])
        redirect(action: "show", id: collectionInstance.id)
    }

    def delete() {
        def collectionInstance = Collection.get(params.id)
        if (!collectionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'collection.label', default: 'Collection'), params.id])
            redirect(action: "list")
            return
        }

        try {
            collectionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'collection.label', default: 'Collection'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'collection.label', default: 'Collection'), params.id])
            redirect(action: "show", id: params.id)
            log.error(e)
        }
    }
}
