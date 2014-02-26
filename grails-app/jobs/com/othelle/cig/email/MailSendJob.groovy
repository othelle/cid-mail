package com.othelle.cig.email

import java.util.concurrent.atomic.AtomicBoolean

import com.othelle.cig.email.LogSenderService;

class MailSendJob {
    def MailDigestService mailDigestService
	def LogSenderService logSenderService

    static triggers = {
        // simple repeatInterval: 5000l // execute job once in 1 seconds
        cron name: 'Mail Service Job', startDelay: 0, cronExpression: '0 0/10 * * * ?' //10

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
			logSenderService.sendLog("(MailSendJob) Unable to check emails - another job is checking. <br />")
            log.info("Unable to send emails, another job is sending")
        }
    }
}