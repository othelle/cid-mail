package com.othelle.cig.email

class FileDetailsCommand implements Serializable {
    String file

    static constraints = {
        file(blank: false)
    }
}
class PreviewDetailsCommand implements Serializable {
    String firstName
    String lastName
    String email

    Boolean isBlank() {
        firstName.size() == 0 || lastName.size() == 0 || email.size() == 0

    }

    static constraints = {
        firstName(blank: false, size: 2..25)
        lastName(blank: false, size: 2..25)
        email(blank: false, size: 1..1024)
    }

    String toString() {
        "${firstName} ${lastName} (${email})"

    }
}
class PreviewDetailsListCommand implements Serializable {
    List<PreviewDetailsCommand> previewDetailsCommandList

    static constraints = {
        previewDetailsCommandList(blank: false)

    }

    String toString() {
        previewDetailsCommandList.each {previewDetails ->
            "${previewDetails.firstName} ${previewDetails.lastName} (${previewDetails.email})"
        }
    }
}

class ExchangeContactsController {
    def importFlow = {
        choiceFile {
            on("next") {
                FileDetailsCommand fc ->
                flow.fc = fc
                if (flow.fc.hasErrors()) {
                    return error()
                }
                else {
                    String importFile = grailsApplication.config.grails.exchangeContact.importFile
                    File importDir = new File(java.lang.System.getProperty("user.home"), grailsApplication.config.grails.exchangeContact.locationsImport)

                    flow.previewDetailsList = new ArrayList<PreviewDetailsCommand>()
                    def f = request.getFile('file')

                    importDir.mkdirs()
                    def fileD = new File(importDir, importFile)
                    f.transferTo(fileD)
                    int j = 0
                    flow.error = "Импорт прошел успешно. "
                    def tok2 = ""
                    def tok3 = ""
                    fileD.getText('windows-1251').eachCsvLine {tokens ->
                        if (j != 0) {
                            def previewDetailsCommand = new PreviewDetailsCommand()
                            previewDetailsCommand.firstName = tokens[1]
                            previewDetailsCommand.lastName = tokens[3]
                            if (tokens[85].size() != 0) tok2 = ", " + tokens[85]
                            if (tokens[88].size() != 0) tok3 = ", " + tokens[88]
                            previewDetailsCommand.email = tokens[82] + tok2 + tok3
                            if (!previewDetailsCommand.isBlank()) {
                                flow.previewDetailsList.add(previewDetailsCommand)
                            }
                            else {
                                log.error("Импорт прошел с ошибками. ")
                                flow.error = "Импорт прошел с ошибками. Одно из полей пусто."
                            }
                        }
                        j++
                    }
                }
            }.to("preview")
        }
        preview {
            on("next") {
                Collection collCurent = null
                if (params.collection.get("id")!="null") {
                    collCurent = Collection.findAllById(params.collection.get("id")).first()
                }
                flow.previewDetailsList.each {pc ->
                    try {
                        if (collCurent == null) {
                            new Contact(firstName: pc.firstName, lastName: pc.lastName, email: pc.email).save().save(failOnError: true)
                        }
                        else {
                            Contact conCurent = new Contact(firstName: pc.firstName, lastName: pc.lastName, email: pc.email).save().save(failOnError: true)
                            collCurent.addToContacts(conCurent).save(failOnError: true).save()
                        }

                    }
                    catch (Exception e) {
                        log.error("Error save ", e)
                        flash.message = "Error save: " + e.message
                        flow.error = "Error save: " + e.message
                        return error()
                    }

                }
            }.to("importComplete")
            on("back").to("choiceFile")
            on("cancel").to("finish")
        }
        importComplete {
            on("ok") {
            }.to("finish")
        }
        finish {
            redirect(uri: "/../../../")
        }
    }

    def export = {
        File exportDir = new File(java.lang.System.getProperty("user.home") + File.separator + grailsApplication.config.grails.exchangeContact.locationsExport)
        def file = new File(exportDir, grailsApplication.config.grails.exchangeContact.exportFile)
        if (file) {
            file.withWriter('WINDOWS-1251') {
                it.writeLine '"Обращение","Имя","Отчество","Фамилия","Суффикс","Организация","Отдел","Должность","Улица (раб. адрес)","Улица 2 (раб. адрес)","Улица 3 (раб. адрес)","Город (раб. адрес)","Область (раб. адрес)","Индекс (раб. адрес)","Страна или регион (раб. адрес)","Улица (дом. адрес)","Улица 2 (дом. адрес)","Улица 3 (дом. адрес)","Город (дом. адрес)","Область (дом. адрес)","Почтовый код (дом.)","Страна или регион (дом. адрес)","Улица (другой адрес)","Улица  2 (другой адрес)","Улица  3 (другой адрес)","Город  (другой адрес)","Область  (другой адрес)","Индекс  (другой адрес)","Страна или регион  (другой адрес)","Телефон помощника","Рабочий факс","Рабочий телефон","Телефон раб. 2","Обратный вызов","Телефон в машине","Основной телефон организации","Домашний факс","Домашний телефон","Телефон дом. 2","ISDN","Телефон переносной","Другой факс","Другой телефон","Пейджер","Основной телефон","Радиотелефон","Телетайп/телефон с титрами","Телекс","Важность","Веб-страница","Годовщина","День рождения","Дети","Заметки","Имя помощника","Инициалы","Категории","Ключевые слова","Код организации","Личный код","Отложено","Пол","Пользователь 1","Пользователь 2","Пользователь 3","Пользователь 4","Пометка","Почтовый ящик (дом. адрес)","Почтовый ящик (другой адрес)","Почтовый ящик (раб. адрес)","Профессия","Расположение","Расположение комнаты","Расстояние","Руководитель","Сведения о доступности в Интернете","Сервер каталогов","Супруг(а)","Счет","Счета","Хобби","Частное","Адрес эл. почты","Тип эл. почты","Краткое имя эл. почты","Адрес 2 эл. почты","Тип 2 эл. почты","Краткое 2 имя эл. почты","Адрес 3 эл. почты","Тип 3 эл. почты","Краткое 3 имя эл. почты","Язык"'
                Contact.list().each {contact ->
                    it.writeLine('"","' + contact.firstName + '","","' + contact.lastName + '","","","","",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"Обычная",,"0.0.00","0.0.00",,"')
                    def emails = Utilities.emailParse(contact.email)
                    def tok1, tok2, tok3
                    def line = '",,"И.О.Ф.",,"",,,,"Не определен",,,,,"Обычная",,,,,"",,,,,,,"",,,"Ложь",'
                    switch (emails.size()) {
                        case 0: line = line + ',,,,,,,,,""'
                            break
                        case 1: line = line + '"' + emails[0] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[0] + ')",' + ',,,,,,""'
                            break
                        case 2: line = line + '"' + emails[0] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[0] + ')",' + '"' + emails[1] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[1] + ')",' + ',,,""'
                            break
                        case 3: line = line + '"' + emails[0] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[0] + ')",' + '"' + emails[1] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[1] + ')",' + '"' + emails[2] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[2] + ')",' + '""'
                            break
                        default: line = line + ',,,,,,,,,""'
                            break
                    }
                    it.writeLine(line)
                }
            }

        }
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "attachment;filename=${file.getName()}")
        response.outputStream << file.newInputStream()
    }

//    def exportFlow = {
//        choiceExportFile {
//            on("next") {
//                File exportDir = new File(java.lang.System.getProperty("user.home") + File.separator + grailsApplication.config.grails.exchangeContact.locationsExport)
//                if (!exportDir.mkdirs()) {
//                    log.error("Unable to create dirs: " + exportDir)
//                    return
//                }
//                flow.file = new File(exportDir, grailsApplication.config.grails.exchangeContact.exportFile)
//                if (flow.file) {
//                    flow.file.withWriter('WINDOWS-1251') {
//                        it.writeLine '"Обращение","Имя","Отчество","Фамилия","Суффикс","Организация","Отдел","Должность","Улица (раб. адрес)","Улица 2 (раб. адрес)","Улица 3 (раб. адрес)","Город (раб. адрес)","Область (раб. адрес)","Индекс (раб. адрес)","Страна или регион (раб. адрес)","Улица (дом. адрес)","Улица 2 (дом. адрес)","Улица 3 (дом. адрес)","Город (дом. адрес)","Область (дом. адрес)","Почтовый код (дом.)","Страна или регион (дом. адрес)","Улица (другой адрес)","Улица  2 (другой адрес)","Улица  3 (другой адрес)","Город  (другой адрес)","Область  (другой адрес)","Индекс  (другой адрес)","Страна или регион  (другой адрес)","Телефон помощника","Рабочий факс","Рабочий телефон","Телефон раб. 2","Обратный вызов","Телефон в машине","Основной телефон организации","Домашний факс","Домашний телефон","Телефон дом. 2","ISDN","Телефон переносной","Другой факс","Другой телефон","Пейджер","Основной телефон","Радиотелефон","Телетайп/телефон с титрами","Телекс","Важность","Веб-страница","Годовщина","День рождения","Дети","Заметки","Имя помощника","Инициалы","Категории","Ключевые слова","Код организации","Личный код","Отложено","Пол","Пользователь 1","Пользователь 2","Пользователь 3","Пользователь 4","Пометка","Почтовый ящик (дом. адрес)","Почтовый ящик (другой адрес)","Почтовый ящик (раб. адрес)","Профессия","Расположение","Расположение комнаты","Расстояние","Руководитель","Сведения о доступности в Интернете","Сервер каталогов","Супруг(а)","Счет","Счета","Хобби","Частное","Адрес эл. почты","Тип эл. почты","Краткое имя эл. почты","Адрес 2 эл. почты","Тип 2 эл. почты","Краткое 2 имя эл. почты","Адрес 3 эл. почты","Тип 3 эл. почты","Краткое 3 имя эл. почты","Язык"'
//                        Contact.list().each {contact ->
//                            it.writeLine('"","' + contact.firstName + '","","' + contact.lastName + '","","","","",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,"Обычная",,"0.0.00","0.0.00",,"')
//                            def emails = Utilities.emailParse(contact.email)
//                            def tok1, tok2, tok3
//                            def line = '",,"И.О.Ф.",,"",,,,"Не определен",,,,,"Обычная",,,,,"",,,,,,,"",,,"Ложь",'
//                            switch (emails.size()) {
//                                case 0: line = line + ',,,,,,,,,""'
//                                    break
//                                case 1: line = line + '"' + emails[0] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[0] + ')",' + ',,,,,,""'
//                                    break
//                                case 2: line = line + '"' + emails[0] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[0] + ')",' + '"' + emails[1] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[1] + ')",' + ',,,""'
//                                    break
//                                case 3: line = line + '"' + emails[0] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[0] + ')",' + '"' + emails[1] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[1] + ')",' + '"' + emails[2] + '","SMTP","' + contact.firstName + ' ' + contact.lastName + '(' + emails[2] + ')",' + '""'
//                                    break
//                                default: line = line + ',,,,,,,,,""'
//                                    break
//                            }
//                            it.writeLine(line)
//                        }
//                    }
//                    flow.fileContents = Utilities.getFileContents(flow.file)
//                }
//                else {
//                    flow.error = "Error export."
//                }
//            }.to("importExportComplete")
//        }
//        importExportComplete {
//            on("ok") {
//            }.to("finishExport")
//        }
//        finishExport {
//            redirect(uri: "/../../../")
//        }
//
//    }

}

