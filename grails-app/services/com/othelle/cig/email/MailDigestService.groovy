package com.othelle.cig.email

import grails.validation.ValidationException



class MailDigestService {

    // boolean transactional = false
    Closure sendMail

    def sen = {
        List<LocalMail> localMails = LocalMail.findAllByFlagSend(true)
        for (LocalMail localMail in localMails) {

            for (Collection col in localMail.contact.collections.toArray()) {
                List<Contact> contacts = Contact.findAll(Collection: col)
                for (Contact contact in contacts) {

                    try {
                        sendMail {
                            to emailParse(contact.email)
                            from grailsApplication.config.grails.mail.username
                            subject "Service creative-email from: " + localMail.contact.email
                            body localMail.description

                        }
                        localMail.flagSend = Boolean.FALSE

                        localMail.save(failOnError: true)
                    }
                    catch (ValidationException e) {

                        println "error validate " + e.message
                    }
                }
            }
        }
    }
    def grailsApplication

    String[] emailParse(String s) {
        String[] tokens = s.split(", \n\r");

        return tokens
    }

    //  def grailsApplication
}

