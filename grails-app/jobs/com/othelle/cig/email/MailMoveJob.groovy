package com.othelle.cig.email



class MailMoveJob {
    def MailDigestService mailDigestService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'Mail Move Job', startDelay: 6000, cronExpression: '0 0/1 * * * ?'

    }
    def execute() {
        log.info("Trying to move emails")
        mailDigestService.move()
    }
}


