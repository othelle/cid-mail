package com.othelle.cig.email

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 11.09.12
 * Time: 9:43
 * To change this template use File | Settings | File Templates.
 */
class Attachment {
    byte[] fileByte
    String name

    static belongsTo = [CheckMail, LocalMail]

    static constraints = {
        //maxSize: 15Mb
        fileByte(blank: false, maxSize: 15*1024*1024)
        name(blank: false, maxSize: 30)

    }

    @Override
    String toString() {
        "${name}"
    }
}
