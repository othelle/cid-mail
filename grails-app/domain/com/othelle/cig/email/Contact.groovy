package com.othelle.cig.email

class Contact {
    String firstName
    String lastName
    String email

    static belongsTo = Collection
    static hasMany = [localMail: LocalMail, collections: Collection]

    static constraints = {
        firstName(blank: false, size: 5..25)
        lastName(blank: false, size: 5..25)
        email(blank: false, size: 1..1024)
        collections()
    }

    @Override
    String toString() {
        "${firstName} ${lastName} (${email})"
    }
}
