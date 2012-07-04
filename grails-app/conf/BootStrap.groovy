import grails.util.Environment
import com.othelle.cig.email.Collection
import com.othelle.cig.email.Contact
import sun.security.util.Password


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
            def contact1 = new Contact(firstName: "Dmitry1", lastName: "Klishin1", email: "kdi1979@mail.ru").save()

            contact1.addToLocalMail(description: "description1", flagSend: true)
            def contact2 = new Contact(firstName: "Dmitry2", lastName: "Klishin2", email: "Klishin.dmitry@gmail.com").save()
            contact2.addToLocalMail(description: "description2", flagSend: true)
            def contact3 = new Contact(firstName: "Dmitry2", lastName: "Klishin2", email: "zgbi100@mail.ru").save()
            contact3.addToLocalMail(description: "description3", flagSend: false)
            //def collections = new Collection(name: "name1", email: "kdi1979@mail.ru", password: "password1", contacts: [contact1, contact2,contact3]).save()

            // def collections1 = new Collection(name: "name11", email: "kdi19@mail.ru", password: pas2.encodeAsBase64()).save()
            //def contact11 = new Contact(firstName: "Dmitry11", lastName: "Klishin11", email: "kdi11@mail.ru", Collection: collections1).save()
            //contact11.addToLocalMail(description: "description11", flagSend: true)

        }
        else {
            println "Existing data, skipping creation"
        }

    }
}