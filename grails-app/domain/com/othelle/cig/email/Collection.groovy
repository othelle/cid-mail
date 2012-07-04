package com.othelle.cig.email

class Collection {
    String name
    String email
    String password
    String code

    static hasMany = [contacts: Contact]

    static constraints = {
        name(blank: false, size: 3..25, unique: true)
        email(blank: false, email: true, unique: true)
        password(blank: false)
        contacts()
    }

    @Override
    String toString() {
        "${name} : ${email}"
    }
}
