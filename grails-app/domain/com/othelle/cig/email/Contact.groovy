package com.othelle.cig.email

class Contact {
    String firstName
    String lastName
    String email

    static belongsTo = Collection
    static hasMany = [localMail: LocalMail, collections: Collection]

    static constraints = {
        firstName(blank: false, size: 2..25)
        lastName(blank: false, size: 2..25)
        email(blank: false, size: 1..1024)
        collections()
    }

    @Override
    String toString() {
        def string = "${firstName} ${lastName} (${email})"

        if (string.length() > 40) {
            return string.substring(0, 39) + "...)"
        }

        else
            return string
    }
}
