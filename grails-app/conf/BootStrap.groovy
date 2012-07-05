import grails.util.Environment
import com.othelle.cig.email.Collection
import com.othelle.cig.email.Contact
import sun.security.util.Password
import grails.validation.ValidationException


class BootStrap {

    def init = { servletContext ->
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                creatData()
                break;
            case Environment.PRODUCTION:
                println "PRODUCTION"
                break;
        }
    }

    def destroy = {
    }

    void creatData() {
        /*  def col1 = Collection.findAll().first()
        def col2 = Collection.findAll().last()
        println "col1=" + col1.toString()
        println "col2=" + col2.toString()
        def contacts = Contact.findAll()
        int n = 0
        for (cur in contacts) {
            n++
            println "cur=" + cur.toString()
            if (n % 2 == 1) {
                cur.setCollection(col1)
            }
            else
                cur.setCollection(col2)
            cur.save()
            println "cur=" + cur.toString()
        }*/


        if (Collection.list().size() == 0) {
            println "Create dates"
            Contact cont = new Contact(firstName: "FirstName4", lastName: "LastName4", email: "Klishin.Dmitry@gmail.com, zgbi100@mail.ru").addToLocalMail(description: "description4", flagSend: false).save(failOnError: true);
            Contact cont1 = new Contact(firstName: "FirstName1", lastName: "LastName1", email: "kdi1979@mail.ru").addToLocalMail(description: "description1", flagSend: true).save(failOnError: true);
            Contact cont2 = new Contact(firstName: "FirstName2", lastName: "LastName2", email: "kdi1979@mail.ru, zgbi100@mail.ru").addToLocalMail(description: "description2", flagSend: false).save(failOnError: true)
            Contact cont3 = new Contact(firstName: "FirstName3", lastName: "LastName3", email: "zgbi100@mail.ru").addToLocalMail(description: "description3", flagSend: false).save(failOnError: true)
            Contact cont4 = new Contact(firstName: "FirstName6", lastName: "LastName6", email: "kdi1979@mail.ru").save(failOnError: true)
            Contact cont5=new Contact(firstName: "FirstName51", lastName: "LastName51", email: "zgbi100@mail.ru").save(failOnError: true)
            new Collection(name: "asg-test01 ", email: "asg-test01@asg-ts.ru", password: "Qwerty77", code: "code1").addToContacts(cont).addToContacts(cont1).save(failOnError: true)
            new Collection(name: "asg-test02 ", email: "asg-test02@asg-ts.ru", password: "Qwerty77", code: "code2").addToContacts(cont).addToContacts(cont2).save(failOnError: true)
            new Collection(name: "asg-test03 ", email: "asg-test03@asg-ts.ru", password: "Qwerty77", code: "code3").addToContacts(cont).addToContacts(cont3).addToContacts(cont4).save(failOnError: true)
            new Collection(name: "asg-test04 ", email: "asg-test04@asg-ts.ru", password: "Qwerty77", code: "code4").addToContacts(cont).save(failOnError: true)
            new Collection(name: "asg-test05 ", email: "asg-test05@asg-ts.ru", password: "Qwerty77", code: "code5").addToContacts(cont).addToContacts(cont5).save(failOnError: true)

        }
        else {
            println "Existing data, skipping creation"
        }

    }
}