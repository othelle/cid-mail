package com.othelle.cig.email

import grails.validation.ValidationException



class MailDigestService {

    // boolean transactional = false
    Closure sendMail

    def sen = {
        List<LocalMail> localMails = LocalMail.findAllByFlagSend(true)
        log.info("Found ${localMails.size()} mails to send")
        for (LocalMail localMail in localMails) {
            def contact = localMail.contact
            log.info("Sending ${localMail.id} to ${contact.email}")
            try {
                sendMail {
                    to emailParse(contact.email)
                    from grailsApplication.config.grails.mail.username
                    subject "Service creative-email from: " + localMail.contact.email
                    body localMail.description

                }
                localMail.flagSend = Boolean.FALSE

                localMail.save(failOnError: true)
                log.info("Mail has been sant ${localMail}")
            }
            catch (ValidationException e) {
                log.error("Unable to send email", e)
            }
        }
    }
    def grailsApplication

    String[] emailParse(String s) {
        String[] tokens = s.split(", \n\r");
        return tokens
    }
}

