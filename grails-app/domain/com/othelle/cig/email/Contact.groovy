package com.othelle.cig.email

import java.util.regex.Pattern

class Contact implements Serializable{
	String firstName
	String lastName
	String email
	String organization

	static searchable = true
	static belongsTo = Collection
	static hasMany = [localMail: LocalMail, collections: Collection]

	static constraints = {
		firstName(blank: false, size: 2..50)
		lastName(blank: false, size: 2..50)
		email(blank: false, size: 1..1024, validator: { val ->
			def EMAILS_PATTERN =/[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,4}/
			Pattern pattern=Pattern.compile(EMAILS_PATTERN);
			String[] ary = val.split(",");
			for(def arr:ary) {
				def arrtrim=arr.replaceAll("^\\s+", "");
				//log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+arrtrim);
				if (!pattern.matcher(arrtrim).matches()){
					//log.info("false");
					return false;
				}
			}
			return true;
		}
		)
		organization(nullable: true, size: 2..50)
		collections()
	}

	static mapping = { sort organization: "asc" }

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
