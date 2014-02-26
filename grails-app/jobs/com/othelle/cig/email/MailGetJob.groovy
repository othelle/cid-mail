package com.othelle.cig.email

import java.util.concurrent.atomic.AtomicBoolean

import com.othelle.cig.email.LogSenderService;


class MailGetJob {
    def MailDigestService mailDigestService
	def LogSenderService logSenderService
    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 1 min
        cron name: 'Mail Getting Job', startDelay: 5000, cronExpression: '0 0/15 * * * ?'//15
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
			logSenderService.sendLog("(MailGetJob) Unable to check emails - another job is checking. <br />")
            log.info("Unable to check emails - another job is checking.")
        }
    }
}