package com.othelle.cig.email

class LocalMail {
    String subject
    String description
    Boolean flagSend
    Contact contact
    Date dateCreated
    Date dateSent

	static searchable = true
	static belongsTo = [Contact, Attachment]
    static hasMany = [attachment: Attachment]

    static constraints = {
        description(maxSize: 2147483647)
        dateCreated(nullable: true)
        flagSend(nullable: true)
        contact(nullable: true)
        dateSent(nullable: true)
    }

    static mapping = {
        sort flagSend: "desc"
    }

    @Override
    String toString() {
        "${description} ${dateCreated} ${flagSend}"
    }
}
