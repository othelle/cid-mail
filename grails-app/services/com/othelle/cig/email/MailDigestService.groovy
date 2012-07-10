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
    def  checkedEmail = {
         Authenticator auth = new MyAuthenticator(grailsApplication.config.grails.mail.username, grailsApplication.config.grails.mail.password)

        String POP_AUTH_USER = grailsApplication.config.grails.mail.username
        String POP_AUTH_PWD = grailsApplication.config.grails.mail.password

        String FOLDER_INDOX = "INBOX"; // имя папки "Входящие"
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties pop3Props = new Properties();
        pop3Props.setProperty("mail.transport.protocol", "pop3");
        pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
        pop3Props.setProperty("mail.pop3.port", "995");
        pop3Props.setProperty("mail.pop3.socketFactory.port", "995");

        URLName url = new URLName("pop3", "pop.gmail.com", 955, "", POP_AUTH_USER, POP_AUTH_PWD);

        Session session = Session.getInstance(pop3Props, auth);
        Store store = session.getStore(url);
        store.connect();


        System.out.println "session.properties " + session.properties
        System.out.println "session.providers " + session.providers
        System.out.println "session.getStore() " + session.getStore()
        System.out.println "session.getProvider(pop3) " + session.getProvider("pop3")
        System.out.println "session.getProviders() " + session.getProviders()
        System.out.println "session.getTransport() " + session.getTransport()



        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);

        // Получить каталог
        Message[] message = folder.getMessage()
        //folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
        //folder.getMessage()
        for (Message messageCur in message) {
            System.out.println(": "
                    + messageCur.getFrom()[0]
                    + "\t" + messageCur.getSubject())
            System.out.println(messageCur.getContent())
          //  CheckMail checkMail=new CheckMail(subject: messageCur.subject, body: messageCur.description, dateSend: messageCur.sentDate)
        }

        // Закрыть соединение

        folder.close(false);
        store.close();


    }
    def grailsApplication

    String[] emailParse(String s) {
        String[] tokens = s.split(", \n\r");
        return tokens
    }
}

