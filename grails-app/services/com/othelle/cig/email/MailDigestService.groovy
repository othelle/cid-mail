package com.othelle.cig.email

import grails.validation.ValidationException



class MailDigestService {

    // boolean transactional = false
    Closure sendMail
    def d1 = new Date()


    def sen = {
        List<LocalMail> localMails = LocalMail.findAllByFlagSend(true)
        println "22222222222"
        for (LocalMail localMail in localMails) {
            if (localMail.flagSend == Boolean.TRUE) {
                List<Contact> contacts = Contact.findAllByCollections(localMail.contact.collections)
                //TODO Ошибка
                /* Error 2012-07-05 08:27:00,030 [quartzScheduler_Worker-3] ERROR util.JDBCExceptionReporter  - Параметр "#1" не установлен
                                Parameter "#1" is not set; SQL statement:
                                select this_.id as id0_0_, this_.version as version0_0_, this_.email as email0_0_, this_.first_name as first4_0_0_, this_.last_name as last5_0_0_ from contact this_ where this_.id=? [90012-164]
                                | Error 2012-07-05 08:27:00,046 [quartzScheduler_Worker-3] ERROR listeners.ExceptionPrinterJobListener  - Exception occurred in job: GRAILS_JOBS.com.othelle.cig.email.MailSendJob
                                Message: org.springframework.jdbc.UncategorizedSQLException: Hibernate operation: could not execute query; uncategorized SQLException for SQL [select this_.id as id0_0_, this_.version as version0_0_, this_.email as email0_0_, this_.first_name as first4_0_0_, this_.last_name as last5_0_0_ from contact this_ where this_.id=?]; SQL state [90012]; error code [90012]; Параметр "#1" не установлен
                                Parameter "#1" is not set; SQL statement:
                                select this_.id as id0_0_, this_.version as version0_0_, this_.email as email0_0_, this_.first_name as first4_0_0_, this_.last_name as last5_0_0_ from contact this_ where this_.id=? [90012-164]; nested exception is org.h2.jdbc.JdbcSQLException: Параметр "#1" не установлен
                                Parameter "#1" is not set; SQL statement:
                                select this_.id as id0_0_, this_.version as version0_0_, this_.email as email0_0_, this_.first_name as first4_0_0_, this_.last_name as last5_0_0_ from contact this_ where this_.id=? [90012-164]
                */

                for (Contact contact in contacts) {
                    /*try {
                        sendMail {
                            to contact.email
                            from localMail.contact.email
                            subject "Service creative-email date: " + localMail.dateCreated
                            body localMail.description

                        }
                        localMail.flagSend = Boolean.FALSE


                        localMail.save(failOnError: true)
                    }
                    catch (ValidationException e) {

                       println "error validate " + e.message
                    }*/
                }
            }
        }
    }

    //  def grailsApplication
}

