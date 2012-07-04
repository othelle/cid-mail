package com.othelle.cig.email

import javax.mail.Message
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import grails.validation.ValidationException

class MailDigestService {

    // boolean transactional = false
    Closure sendMail
    def d1 = new Date()


    def sen = {
        List<LocalMail> localMails = LocalMail.findAllByFlagSend(true)
        for (LocalMail localMail in localMails) {
            if (localMail.getFlagSend() == true) {
                try {
                    List<Contact> contacts = Contact.findAllByCollection(localMail.getContact().getCollection())

                    for (Contact contact in contacts) {
                        sendMail {
                            to contact.getEmail()
                            from localMail.getContact().getEmail()
                            subject "Service creative-email date: " + localMail.getDateCreated()
                            body localMail.getDescription()
                        }
                    }
                    localMail.flagSend = Boolean.FALSE


                    localMail.save(failOnError: true)
                }
                catch (ValidationException e) {

                  //  println "error validate " + e.message
                }
            }
        }
    }

    //  def grailsApplication
}

