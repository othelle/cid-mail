package com.othelle.cig.email

class Contact implements Serializable{
    String firstName
    String lastName
    String email
    String organization

    static belongsTo = Collection
    static hasMany = [localMail: LocalMail, collections: Collection]

    static constraints = {
        firstName(blank: false, size: 2..25)
        lastName(blank: false, size: 2..25)
        email(blank: false, size: 1..1024)
        organization(nullable: true, size: 2..25)
        collections()
    }

    @Override
    String toString() {
        def string = "${firstName} ${lastName} (${email})"

        if (string.length() > 40) {
            return string.substring(0, 39) + "...)"
        }

        else
            return string
    }
}
