import org.apache.log4j.RollingFileAppender
import org.apache.log4j.ConsoleAppender
import org.apache.log4j.PatternLayout
import org.apache.log4j.DailyRollingFileAppender

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
def logDirectory = '/var/log/cid-mail/'
//def logDirectory = 'log/'
def exportDirectory = '.cid/export/'
def importDirectory = '.cid/import/'
def attachmentDirectory='.cid/attachments/'
//def attachmentDirectory = 'attachments/'
def infoLog = 'info.log'
def errorLog = 'error.log'
def fatalLog = 'fatal.log'
def exportFileName = 'export.csv'
def importFileName = 'import.csv'

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "creetive.email@gmail.com"
        password = "yjdsq0511uf"
        props = ["mail.smtp.auth": "true",
                "mail.smtp.socketFactory.port": "465",
                "mail.smtp.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
                "mail.smtp.socketFactory.fallback": "false"]

    }

    fileViewer {
        locations = [logDirectory]
        linesCount = 300
        areDoubleDotsAllowedInFilePath = false
    }
    exchangeContact {
        locationsExport = exportDirectory
        locationsImport = importDirectory
        exportFile = exportFileName
        importFile = importFileName
    }
    attachment {
        attachments = attachmentDirectory
    }

}
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [html: ['text/html', 'application/xhtml+xml'],
        xml: ['text/xml', 'application/xml'],
        text: 'text/plain',
        js: 'text/javascript',
        rss: 'application/rss+xml',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        all: '*/*',
        json: ['application/json', 'text/json'],
        form: 'application/x-www-form-urlencoded',
        multipartForm: 'multipart/form-data'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// set per-environment serverURL stem for creating absolute links
environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        //        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration

log4j = {
    //  def logLayoutPattern = new PatternLayout("%d{ISO8601} [%-5p][%-16.16t][%32.32c] - %m%n")
    def logLayoutPattern = new PatternLayout("%d [%t] %-5p %c %x - %m%n")
    appenders {
        appender new ConsoleAppender(name: "console",
                threshold: log4jConsoleLogLevel,
                layout: logLayoutPattern
        )
        appender new DailyRollingFileAppender(name: "appFileError",
                threshold: org.apache.log4j.Level.ERROR,
                file: logDirectory + errorLog,
                datePattern: "'.'yyyy-MM-dd",
                layout: logLayoutPattern
        )
        appender new DailyRollingFileAppender(name: "appFileInfo",
                threshold: org.apache.log4j.Level.INFO,
                file: logDirectory + infoLog,
                datePattern: "'.'yyyy-MM-dd",
                layout: logLayoutPattern
        )

        appender new DailyRollingFileAppender(name: "appFileFatal",
                threshold: org.apache.log4j.Level.FATAL,
                file: logDirectory + fatalLog,
                datePattern: "'.'yyyy-MM-dd",
                layout: logLayoutPattern
        )

    }

    root {
        info 'stdout', 'appFileFatal', 'appFileError', 'appFileInfo'
        additivity = true
    }


}



