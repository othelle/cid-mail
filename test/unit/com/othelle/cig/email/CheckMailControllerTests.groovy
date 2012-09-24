package com.othelle.cig.email



import org.junit.*
import grails.test.mixin.*

@TestFor(CheckMailController)
@Mock(CheckMail)
class CheckMailControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/checkMail/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.checkMailInstanceList.size() == 0
        assert model.checkMailInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.checkMailInstance != null
    }

    void testSave() {
        controller.save()

        assert model.checkMailInstance != null
        assert view == '/checkMail/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/checkMail/show/1'
        assert controller.flash.message != null
        assert CheckMail.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/checkMail/list'


        populateValidParams(params)
        def checkMail = new CheckMail(params)

        assert checkMail.save() != null

        params.id = checkMail.id

        def model = controller.show()

        assert model.checkMailInstance == checkMail
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/checkMail/list'


        populateValidParams(params)
        def checkMail = new CheckMail(params)

        assert checkMail.save() != null

        params.id = checkMail.id

        def model = controller.edit()

        assert model.checkMailInstance == checkMail
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/checkMail/list'

        response.reset()


        populateValidParams(params)
        def checkMail = new CheckMail(params)

        assert checkMail.save() != null

        // test invalid parameters in update
        params.id = checkMail.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/checkMail/edit"
        assert model.checkMailInstance != null

        checkMail.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/checkMail/show/$checkMail.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        checkMail.clearErrors()

        populateValidParams(params)
        params.id = checkMail.id
        params.version = -1
        controller.update()

        assert view == "/checkMail/edit"
        assert model.checkMailInstance != null
        assert model.checkMailInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/checkMail/list'

        response.reset()

        populateValidParams(params)
        def checkMail = new CheckMail(params)

        assert checkMail.save() != null
        assert CheckMail.count() == 1

        params.id = checkMail.id

        controller.delete()

        assert CheckMail.count() == 0
        assert CheckMail.get(checkMail.id) == null
        assert response.redirectedUrl == '/checkMail/list'
    }
}
