package com.othelle.cig.email

import grails.validation.ValidationException

import javax.mail.*
import com.sun.mail.pop3.POP3Folder
import javax.mail.internet.MimeBodyPart

class MailDigestService {

    // boolean transactional = false
    Closure sendMail
    def grailsApplication

    def synchronized sendEmails = {
        List<LocalMail> localMails = LocalMail.findAllByFlagSend(true)
        log.info("Found ${localMails.size()} mails to send")
        for (LocalMail localMail in localMails) {
            def contact = localMail.contact
            log.info("Sending ${localMail.id} to ${contact.email}")
            try {
                sendMail {
                    to Utilities.emailParse(contact.email)
                    from grailsApplication.config.grails.mail.username
                    subject localMail.subject
                    body localMail.description

                }
                localMail.flagSend = Boolean.FALSE
                localMail.dateSent = new Date();
                localMail.save(failOnError: true)
                log.info("Mail has been sant ${localMail}")
            }
            catch (Exception e) {
                log.error("Unable to send email", e)
            }
        }
    }


    def synchronized checkNewEmails = {
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
            log.info("Checking email for group ${collection}")
            javax.mail.Authenticator auth = new MyAuthenticator(collection.email, collection.password)

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
                    log.info("Connected to store " + collection.email)
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
                    if (CheckMail.findAllByCollectionAndUid(collection, uid.toString()).empty.booleanValue()) {
                        log.info("Got new message: "
                                + "\nfrom:" + messageCur.from
                                + "\nsubject:" + messageCur.subject)
                        def fromMassager = Utilities.emailEval(messageCur.from)
                        def subject = messageCur.subject
                        if (subject.equals(null)) {
                            log.info("Subject is null.")
                            subject = "Запрос на коммерческое предложение"
                        }
                        try {
                            log.info("Utilities.emailEval(messageCur.from)=" + fromMassager)
                            CheckMail checkMail = new CheckMail(uid: uid, emailFrom: fromMassager, subject: subject, body: getText(messageCur), dateSend: messageCur.sentDate, flagNew: true, collection: collection).save(failOnError: true)
                            log.info("Adding email to the queue: ${checkMail.subject}")
                            copyCheckedEmailsToContacts(checkMail)
                            checkMail.save()
                        }
                        catch (ValidationException ne) {
                            log.error("Unable save checked mail ${uid} - ValidationException ", ne)
                        }
                        catch (Exception ve) {
                            log.error("Unable save checked mail ${uid} - Exception ", ve)
                        }


                    }
                    else {
                        log.debug("Message already exists.")
                    }
                }
                // Закрыть соединение
                folder.close(false);
                store.close();
            } catch (Exception e) {
                log.error("Unable to send email ", e)
            }
        }
    }
/**
 * Return the primary text content of the message.
 */
    private String getText(Part p) throws MessagingException, IOException {
        log.info("Type content=" + p.contentType)
        if (p.isMimeType("text/*")) {
            def s = (String) p.getContent();
            return s;
        }
        if (p.isMimeType("application/*")) {
            log.info("application/*")
            def s = "\n" + p.fileName
            return s
        }
        if (p.isMimeType("image/*")) {
            log.info("image/*")
            def s = "\n" + p.fileName
            return s
        }
        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    /* if (text != null)
                   return text;*/

                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = html2Text(getText(bp));
                    if (s != null) {
                        //   return s;
                        text = s;
                    }
                } else {
                    log.info "ELSE1"
                    return getText(bp);
                }
            }
            return text;
        }
        else if (p.isMimeType("multipart/*")) {
            log.info "MULTIPAT/*"
            String s = "";
            Multipart mp = (Multipart) p.getContent();
            log.info("N=" + mp.getCount())
            for (int i = 0; i < mp.getCount(); i++) {
                s = s + getText(mp.getBodyPart(i));
            }
            if (s != null)
                return s;
        }
        else {
            log.info("Ignor type=" + p.contentType)
        }

        return null;
    }

    public String html2Text(String html) {
        return html
                .replaceAll("\\<([bB][rR]|[dD][lL])[ ]*[/]*[ ]*\\>", "\n")
                .replaceAll("\\</([pP]|[hH]5|[dD][tT]|[dD][dD]|[dD][iI][vV])[ ]*\\>", "\n")
                .replaceAll("\\<[lL][iI][ ]*[/]*[ ]*\\>", "\n* ")
                .replaceAll("\\<[dD][dD][ ]*[/]*[ ]*\\>", " - ")
                .replaceAll("\\<.*?\\>", "");
    }


    def copyCheckedEmailsToContacts(CheckMail checkedMail) {
        //not the best way to copy checkmails to localmail
        if (checkedMail) {
            log.info("checkMail " + checkedMail)
            for (Contact contact : checkedMail.collection.contacts) {
                try {
                    LocalMail localMail = new LocalMail(flagSend: true, subject: checkedMail.subject,
                            description: checkedMail.body, contact: contact).save(failOnError: true)
                    localMail.save(failOnError: true)
                }
                catch (ValidationException e) {
                    log.error("Unable to copyCheckedEmailsToContacts checkMail", e)
                }
            }
        }
        checkedMail.flagNew = Boolean.FALSE
    }
}


