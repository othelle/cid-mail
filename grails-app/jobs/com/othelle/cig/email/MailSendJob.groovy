package com.othelle.cig.email

class MailSendJob {
    def MailDigestService mailDigestService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'Mail Service Job', startDelay: 60000, cronExpression: '0 0/5 * * * ?'

    }

    def execute() {
        log.info("Executing mail sending job")
        mailDigestService.sendEmails()
    }
}