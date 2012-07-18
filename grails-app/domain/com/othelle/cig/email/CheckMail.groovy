package com.othelle.cig.email

class CheckMail {
    String uid
    String subject
    String body
    Date dateSend
    Boolean flagNew
    Collection collection

    static belongsTo = [Collection]

    static constraints = {
        uid(blank: false)
        subject(blank: false, maxSize: 100)
        body(blank: false, maxSize: 1000)
        dateSend(nullable: true)
        flagNew(nullable: true)

    }

    @Override
    String toString() {
        "${collection} ${uid} ${subject} ${body} ${dateSend} ${flagNew}"
    }
}

