package com.othelle.cig.email

class MailSendJob {

    def d1 = new Date()
    def MailDigestService mailDigestService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'Mail Service Job', startDelay: 60000, cronExpression: '0 0/10 * * * ?'

    }


    def execute() {
        mailDigestService.sen()





    }
}