package com.othelle.cig.email

class ReportController {

    def index = {
        redirect(action: search)
    }

    def search = {
    }

    def results = {
        def checkMails = CheckMail.withCriteria {
            "${params.queryType}" {
                between('dateSend', new Date().parse("dd/MM/yyyy", params.dateOne_value), new Date().parse("dd/MM/yyyy", params.dateSecond_value))
                ilike('emailFrom', "%" + params.emailFrom + "%")
                collection {
                    ilike('name', "%" + params.groupTo + "%")
                }
                collection {
                    contacts {
                        ilike('firstName', "%" + params.contactTo + "%")
                    }
                }
            }
        }
        [checkMails: checkMails.unique()]
    }
}
