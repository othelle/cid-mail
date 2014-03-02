package com.othelle.cig.email

class CheckMail {
    String uid
    String emailFrom
    String subject
    String body
    Date dateSend
    Boolean flagNew
    Collection collection

	static searchable = true
	static belongsTo = [Collection, Attachment]
    static hasMany = [attachment: Attachment]

    static constraints = {
        uid(blank: false)
        emailFrom(email: true, nullable: true)
        subject(blank: false, maxSize: 100)
        body(maxSize: 2147483647)
        dateSend(nullable: true)
        flagNew(nullable: true)

    }
	
	static mapping = {
		sort flagNew: "desc"
	}
	
    @Override
    String toString() {
        "${collection} ${uid} ${subject} ${body} ${dateSend} ${flagNew}"
    }

}

