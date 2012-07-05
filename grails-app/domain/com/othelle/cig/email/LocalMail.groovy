package com.othelle.cig.email

class LocalMail {
    String description
    Date dateCreated
    Boolean flagSend
    Contact contact

    static belongsTo = [Contact]

    static constraints = {
        description(blank: false, maxSize: 1000)
        dateCreated(nullable: true)
        flagSend(nullable: true)
        contact(nullable: true)
    }

    @Override
    String toString() {
        "${description} ${dateCreated} ${flagSend}"
    }
}
