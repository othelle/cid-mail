package com.othelle.cig.email

class CheckMail {
    String uid
    String emailFrom
    String subject
    String body
    Date dateSend
    Boolean flagNew
    Collection collection

    static belongsTo = [Collection, Attachment]
    static hasMany = [attachment: Attachment]

    static constraints = {
        uid(blank: false)
        emailFrom(nullable: true)
        subject(blank: false, maxSize: 100)
        body(maxSize: 5000)
        dateSend(nullable: true)
        flagNew(nullable: true)

    }

    @Override
    String toString() {
        "${collection} ${uid} ${subject} ${body} ${dateSend} ${flagNew}"
    }

}

