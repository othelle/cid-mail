package com.othelle.cig.email

import grails.validation.ValidationException
import javax.mail.Session
import javax.mail.Store
import javax.mail.Folder
import javax.mail.Message
import javax.mail.URLName



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
    def checkedEmail = {
        Authenticator auth = new MyAuthenticator(grailsApplication.config.grails.mail.username, grailsApplication.config.grails.mail.password)

        String POP_AUTH_USER = grailsApplication.config.grails.mail.username
        String POP_AUTH_PWD = grailsApplication.config.grails.mail.password

        String FOLDER_INDOX = "INBOX"; // имя папки "Входящие"
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties pop3Props = new Properties();
        pop3Props.setProperty("mail.transport.protocol", "pop");
        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.auth", "true");
        pop3Props.setProperty("mail.pop3.ssl", "true");
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

        URLName url = new URLName("pop3", "pop.gmail.com", 955, "", POP_AUTH_USER, POP_AUTH_PWD);

        Session session = Session.getInstance(pop3Props, auth);
        Store store = session.getStore(url);
        store.connect();

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        // Получить каталог
        Message[] message = folder.getMessage()
        //folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        //folder.getMessage()
        for (Message messageCur in message) {
            System.out.println(": "
                    + messageCur.getFrom()
                    + "\t" + messageCur.getSubject())
            System.out.println(messageCur.getContent())
            //CheckMail checkMail=new CheckMail(subject: messageCur.subject, body: messageCur.description, dateSend: messageCur.sentDate, flagNew: "true").save()
        }

        // Закрыть соединение

        folder.close(false);
        store.close();


    }
    def grailsApplication
    def shift = {
        List<CheckMail> checkMails = CheckMail.findAllByFlagNew(true)
        List<Contact> contacts = Contact.findAll()
        log.info("Found ${checkMails.size()} mails to send")
        for (CheckMail checkMail in checkMails) {
            for (Contact contact in contacts) {
                try {
                    LocalMail localMail = new LocalMail(flagSend: "true", description: checkMail.body).save(failOnError: true)
                    contact.addToLocalMail(localMail).save(failOnError: true)
                    log.info("Move ${checkMail.id} to ${localMail.id}")
                }
                catch (ValidationException e) {
                    log.error("Unable to move checkMail", e)
                }
            }
        }

    }

    String[] emailParse(String s) {
        String[] tokens = s.split(", \n\r");
        return tokens
    }
}

