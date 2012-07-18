package com.othelle.cig.email

import grails.validation.ValidationException

import javax.mail.*
import com.sun.mail.pop3.POP3Folder

class MailDigestService {

    // boolean transactional = false
    Closure sendMail
    def grailsApplication

    def sen = {
        List<LocalMail> localMails = LocalMail.findAllByFlagSend(true)
        log.info("Found ${localMails.size()} mails to send")
        for (LocalMail localMail in localMails) {
            def contact = localMail.contact
            log.info("Sending ${localMail.id} to ${contact.email}")
            log.info("emailParse(contact.email) 111111111  "+emailParse(contact.email))
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


    def checkedEmail = {

        def groups = Collection.list();

        /**
         * 16:28:57пока 5 шт сделал:
         16:29:00asg-test01
         16:29:10asg-test02
         asg-test03
         asg-test04
         asg-test05
         16:29:21пароли: Qwerty77
         16:29:36pop/smtp: mail.nic.ru
         16:30:05пользователь: asg-test01@asg-ts.ru и т.д.
         16:30:21авторизация при отправке: аналогично pop3
         */

        for (Collection collection : groups) {
            javax.mail.Authenticator auth = new MyAuthenticator(grailsApplication.config.grails.mail.username, grailsApplication.config.grails.mail.password)

            String POP_AUTH_USER = collection.email
            String POP_AUTH_PWD = collection.password

            Properties pop3Props = new Properties();
            pop3Props.setProperty("mail.transport.protocol", "pop");
            pop3Props.setProperty("mail.pop3.auth", "true");

            URLName url = new URLName("pop3", "mail.asg-ts.ru", 110, "", POP_AUTH_USER, POP_AUTH_PWD);
            Session session = Session.getInstance(pop3Props, auth);
            Store store = session.getStore(url);
            try {
                if (!store.isConnected()) {
                    store.connect();
                    log.info("Connected to store " + POP_AUTH_USER)

                }
                else {
                    log.info("Already connected to store")
                }
                // Folder folder = store.getFolder("INBOX");
                POP3Folder folder = (POP3Folder) store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);

                // Получить каталог
                Message[] message = folder.getMessages()

                for (Message messageCur in message) {
                    StringBuilder uid = new StringBuilder(folder.getUID(messageCur).toString())
                    if (CheckMail.findAllByCollectionAndUid(collection, uid).empty.booleanValue()) {
                        log.info(": "
                                + messageCur.from
                                + "\t" + messageCur.subject)
                        log.info(messageCur.content)
                        try {
                            CheckMail checkMail = new CheckMail(uid: uid, subject: messageCur.subject, body: messageCur.content, dateSend: messageCur.sentDate, flagNew: "true", collection: collection).save(failOnError: true)
                            log.info("Add checkMail")
                        }
                        catch (ValidationException ve) {
                            log.error("Unable save checkMail ", ve.message)
                        }
                    }
                    else {
                        log.info("Message already exists.")
                    }
                }
                // Закрыть соединение
                folder.close(false);
                store.close();
            } catch (ValidationException e) {
                log.error("Unable to send email ", e.printStackTrace())
            }
        }
    }


    def move = {
        List<CheckMail> checkMails = CheckMail.findAllByFlagNew(true)
        for (CheckMail checkMail in checkMails) {
            log.info("checkMail " + checkMail)
            for(Contact contact: checkMail.collection.contacts){
            /*//TODO неверно
            List<Contact> contacts = Contact.findAll(Collection: checkMail.collection)
            log.info("CONTACT.size= " + contacts.size())*/

            //for (Contact contact : contacts) {
                try {
                    LocalMail localMail = new LocalMail(flagSend: "true", description: checkMail.body, contact: contact).save(failOnError: true)
                    log.info("Move ${checkMail} to ${localMail}")
                    checkMail.flagNew = Boolean.FALSE
                    checkMail.save(failOnError: true)
                }
                catch (ValidationException e) {
                    log.error("Unable to move checkMail", e)
                }
            }
        }
    }

    String[] emailParse(String s) {
        String[] tokens = s.split(", ");
        return tokens
    }

}


