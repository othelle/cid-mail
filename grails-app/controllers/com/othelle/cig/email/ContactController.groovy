package com.othelle.cig.email

class ContactController {
    static scaffold = true


    def update() {
        super.update()
        def element = Contact.findById(params.id)

        def collections = element.collections
        def toRemove = new ArrayList<java.util.Collection>(collections)



        for (Object collection : toRemove) {
            element.removeFromCollections(collection)
        }

        def ids = params.collections

        if (ids) {
            for (String collectionId : Arrays.asList(ids)) {
                element.addToCollections(Collection.findById(Integer.parseInt(collectionId)))
            }
        }

        element.save()
    }

    def delete() {
        def map = params
        def element = Contact.findById(params.id)

        for (Object collection : element.collections) {
            element.removeFromCollections(collection)
        }

        element.save()
        element.delete()
        redirect(action: 'list')
    }
}
