package com.othelle.cig.email

class LocalMail {
    String id
    String description
    Date dateCreated
    Boolean flagSend
    Contact contact

    static belongsTo =[Contact]

    static constraints = {
        id(blank: false, size: 3..25, unique: true)
        description(blank: false, maxSize: 1000)
        dateCreated(nullable: true)
        flagSend(nullable: true)
        contact(nullable: true)
    }

    @Override
    String toString() {
        "${id} ${description}"
    }
}
