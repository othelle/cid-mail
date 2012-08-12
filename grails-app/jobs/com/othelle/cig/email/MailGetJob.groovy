package com.othelle.cig.email

import java.util.concurrent.atomic.AtomicBoolean

/**
 * User: Vasily Vlasov
 * Date: 13.07.12
 */
class MailGetJob {
    def MailDigestService mailDigestService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 5 seconds
        cron name: 'Mail Getting Job', startDelay: 5000, cronExpression: '0 0/5 * * * ?'
    }

    static AtomicBoolean monitor = new AtomicBoolean(false)

    def execute() {
        if (monitor.compareAndSet(false, true)) {
            try {
                log.info("Trying to get emails")
                mailDigestService.checkNewEmails()
            } finally {
                monitor.set(false)
            }
        }
        else {
            log.info("Unable to check emails - another job is checking.")
        }
    }
}