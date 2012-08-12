package com.othelle.cig.email

import java.util.concurrent.atomic.AtomicBoolean

class MailSendJob {
    def MailDigestService mailDigestService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'Mail Service Job', startDelay: 60000, cronExpression: '0 0/5 * * * ?'

    }

    static AtomicBoolean monitor = new AtomicBoolean(false)

    def execute() {
        if (monitor.compareAndSet(false, true)) {
            try {
                log.info("Executing mail sending job")
                mailDigestService.sendEmails()
            } finally {
                monitor.set(false)
            }
        }
        else {
            log.info("Unable to send emails, another job is sending")
        }
    }
}