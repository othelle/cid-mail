package com.othelle.cig.email

class CheckMail {
    String subject
    String body
    Date dateSend
    Boolean flagNew


    static constraints = {
        subject(blank: false, maxSize: 100)
        body(blank: false, maxSize: 1000)
        dateSend(nullable: true)
        flagNew(nullable: true)
    }

    @Override
    String toString() {
        "${subject} ${body} ${dateSend} ${flagNew}"
    }
}

