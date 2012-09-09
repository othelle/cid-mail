package com.othelle.cig.email

class CheckMail {
    String uid
    String emailFrom
    String subject
    String body
    Date dateSend
    Boolean flagNew
    Collection collection

    static belongsTo = [Collection]

    static constraints = {
        uid(blank: false)
        emailFrom (nullable: true)
        subject(blank: false, maxSize: 100)
        body(maxSize: 3000)
        dateSend(nullable: true)
        flagNew(nullable: true)

    }

    @Override
    String toString() {
        "${collection} ${uid} ${subject} ${body} ${dateSend} ${flagNew}"
    }
}

