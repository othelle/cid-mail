package com.othelle.cig.email

/**
 * User: Vasily Vlasov
 * Date: 13.07.12
 */
class MailGetJob {
    def MailDigestService mailDigestService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'Mail Getting Job', startDelay: 5000, cronExpression: '0 0/1 * * * ?'

    }

    def execute() {
        log.info("Trying to get emails")
        mailDigestService.checkNewEmails()
    }
}