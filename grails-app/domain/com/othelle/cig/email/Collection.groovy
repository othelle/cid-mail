package com.othelle.cig.email

class Collection implements Serializable{
    String name
    String email
    String password
    String code
	
	static searchable = true
    
	static hasMany = [contacts: Contact, checkMail: CheckMail]

    static constraints = {
        name(blank: false, size: 3..25, unique: true)
        email(blank: false, email: true, unique: true)
        password(blank: false)
        contacts()
    }
	
	static mapping = {
		sort name: "asc"
	}
	
    @Override
    String toString() {
        "${name} : ${email}"
    }
}
