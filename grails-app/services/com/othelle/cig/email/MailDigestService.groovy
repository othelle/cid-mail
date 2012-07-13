package com.othelle.cig.email

import grails.validation.ValidationException

import javax.mail.*

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
            Store store = session.getStore("pop3");
            try {
                store.connect();

                Folder folder = store.getFolder("INBOX");
                folder.open(Folder.READ_ONLY);

                // Получить каталог
                Message[] message = folder.getMessage()
                //folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
                //folder.getMessage()
                for (Message messageCur in message) {
                    log.info(": "
                            + messageCur.from
                            + "\t" + messageCur.subject)
                    log.info(messageCur.content)
                    //CheckMail checkMail=new CheckMail(subject: messageCur.subject, body: messageCur.description, dateSend: messageCur.sentDate, flagNew: "true").save()
                }

                // Закрыть соединение

                folder.close(false);
                store.close();
            } catch (Exception e) {
                log.error("Unable to fetch emails", e)
            }
        }
    }

    def grailsApplication
    def shift = {
        List<CheckMail> checkMails = CheckMail.findAllByFlagNew(true)
        List<Contact> contacts = Contact.findAll()
        log.info("Found ${checkMails.size()} mails to send")
        for (CheckMail checkMail in checkMails) {

            /* Pattern regex=Pattern.compile("\\w+@[a-zA-Z_]+?\\.[a-zA-z]{2,6}")
                        Matcher regexMatcher=regex.matcher(checkMail.subject)
                        while (regexMatcher.find())
                        {
                            contacts=Contact.findAllByEmail(regexMatcher)
                            if (contacts.size()!=0)
                            {
                                try {
                                    LocalMail localMail = new LocalMail(flagSend: "true", description: checkMail.body).save(failOnError: true)
                                    contacts[0].addToLocalMail(localMail).save(failOnError: true)
                                    log.info("Move ${checkMail.id} to ${localMail.id}")
                                    checkMail.flagNew = Boolean.FALSE
                                    checkMail.save(failOnError: true)
                                }
                                catch (ValidationException e) {
                                    log.error("Unable to move checkMail", e)
                                }
                            }
                        }
            */

            for (Contact contact in contacts) {
                try {
                    LocalMail localMail = new LocalMail(flagSend: "true", description: checkMail.body).save(failOnError: true)
                    contact.addToLocalMail(localMail).save(failOnError: true)
                    log.info("Move ${checkMail.id} to ${localMail.id}")
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
        String[] tokens = s.split(", \n\r");
        return tokens
    }
}

