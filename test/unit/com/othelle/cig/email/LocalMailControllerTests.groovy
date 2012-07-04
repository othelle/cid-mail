package com.othelle.cig.email



import org.junit.*
import grails.test.mixin.*

@TestFor(LocalMailController)
@Mock(LocalMail)
class LocalMailControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/localMail/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.localMailInstanceList.size() == 0
        assert model.localMailInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.localMailInstance != null
    }

    void testSave() {
        controller.save()

        assert model.localMailInstance != null
        assert view == '/localMail/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/localMail/show/1'
        assert controller.flash.message != null
        assert LocalMail.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/localMail/list'


        populateValidParams(params)
        def localMail = new LocalMail(params)

        assert localMail.save() != null

        params.id = localMail.id

        def model = controller.show()

        assert model.localMailInstance == localMail
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/localMail/list'


        populateValidParams(params)
        def localMail = new LocalMail(params)

        assert localMail.save() != null

        params.id = localMail.id

        def model = controller.edit()

        assert model.localMailInstance == localMail
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/localMail/list'

        response.reset()


        populateValidParams(params)
        def localMail = new LocalMail(params)

        assert localMail.save() != null

        // test invalid parameters in update
        params.id = localMail.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/localMail/edit"
        assert model.localMailInstance != null

        localMail.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/localMail/show/$localMail.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        localMail.clearErrors()

        populateValidParams(params)
        params.id = localMail.id
        params.version = -1
        controller.update()

        assert view == "/localMail/edit"
        assert model.localMailInstance != null
        assert model.localMailInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/localMail/list'

        response.reset()

        populateValidParams(params)
        def localMail = new LocalMail(params)

        assert localMail.save() != null
        assert LocalMail.count() == 1

        params.id = localMail.id

        controller.delete()

        assert LocalMail.count() == 0
        assert LocalMail.get(localMail.id) == null
        assert response.redirectedUrl == '/localMail/list'
    }
}
