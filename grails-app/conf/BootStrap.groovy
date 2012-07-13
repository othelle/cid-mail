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
            println "Creating test objects dates"
            def col1 = new Collection(name: "asg-test01 ", email: "asg-test01@asg-ts.ru", password: String.format("Qwerty77", (int) (100 * Math.random())), code: "code1").save(failOnError: true)
            def col2 = new Collection(name: "asg-test02 ", email: "asg-test02@asg-ts.ru", password: String.format("Qwerty77", (int) (100 * Math.random())), code: "code2").save(failOnError: true)
            def col3 = new Collection(name: "asg-test03 ", email: "asg-test03@asg-ts.ru", password: String.format("Qwerty77", (int) (100 * Math.random())), code: "code3").save(failOnError: true)
            def col4 = new Collection(name: "asg-test04 ", email: "asg-test04@asg-ts.ru", password: String.format("Qwerty77", (int) (100 * Math.random())), code: "code4").save(failOnError: true)
            def col5 = new Collection(name: "asg-test05 ", email: "asg-test05@asg-ts.ru", password: String.format("Qwerty77", (int) (100 * Math.random())), code: "code5").save(failOnError: true)


//            createContact("FirstName1", "LastName1", "kdi1991@gmail.com, zgbi100@mail.ru", col1, col3, col5)
//            createContact("FirstName2", "LastName2", "kdi1992@gmail.com, zgbi102@mail.ru", col4, col3, col5)
            /*c1.addToCollections(col1).addToCollections(col3).addToCollections(col4).save();


            Contact cont = new Contact(firstName: "FirstName4", lastName: "LastName4", email: "kdi1991@gmail.com, zgbi100@mail.ru").save().addToLocalMail(description: "description4", flagSend: false).save(failOnError: true);
            Contact cont1 = new Contact(firstName: "FirstName1", lastName: "LastName1", email: "kdi1979@mail.ru").save().addToLocalMail(description: "description1", flagSend: true).save(failOnError: true);
            Contact cont2 = new Contact(firstName: "FirstName2", lastName: "LastName2", email: "kdi1980@mail.ru, zgbi100@mail.ru").save()addToLocalMail(description: "description2", flagSend: false).save(failOnError: true)
            Contact cont3 = new Contact(firstName: "FirstName3", lastName: "LastName3", email: "zgbi100@mail.ru").save().addToLocalMail(description: "description3", flagSend: false).save(failOnError: true)
            Contact cont4 = new Contact(firstName: "FirstName6", lastName: "LastName6", email: "kdi1990@mail.ru").save(failOnError: true)
            Contact cont5=new Contact(firstName: "FirstName51", lastName: "LastName51", email: "zgbi100@mail.ru").save(failOnError: true)
            new Collection(name: "asg-test01 ", email: "asg-test01@asg-ts.ru", password: "Qwerty77", code: "code1").addToContacts(cont).addToContacts(cont1).save(failOnError: true)   .save()
            new Collection(name: "asg-test02 ", email: "asg-test02@asg-ts.ru", password: "Qwerty77", code: "code2").addToContacts(cont).addToContacts(cont2).save(failOnError: true) .save()
            new Collection(name: "asg-test03 ", email: "asg-test03@asg-ts.ru", password: "Qwerty77", code: "code3").addToContacts(cont).addToContacts(cont3).addToContacts(cont4).save(failOnError: true)  .save()
            new Collection(name: "asg-test04 ", email: "asg-test04@asg-ts.ru", password: "Qwerty77", code: "code4").addToContacts(cont).save(failOnError: true)              .save()
            new Collection(name: "asg-test05 ", email: "asg-test05@asg-ts.ru", password: "Qwerty77", code: "code5").addToContacts(cont).addToContacts(cont5).save(failOnError: true) .save()


*/
        }
        else {
            println "Existing data, skipping creation"
        }

    }

    private void createContact(String fname, String lname, String email, Collection... cols) {
        def c1 = new Contact(firstName: fname, lastName: lname, email: email)
        for (Collection col : cols) {
            c1.addToCollections(col)
        }
        c1.save()
    }
}