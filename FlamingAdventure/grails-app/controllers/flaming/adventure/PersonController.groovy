package flaming.adventure



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def world() {
        Person hero = Person.first()
        List<List> list = new ArrayList<List>();
        for (int i = 0; i < 5; i ++) {
            def column = []
            for (int k = 0; k < 5; k++) {
                if (hero.posX == k && hero.posY == i) {
                    column.add(' ')
                }
                else {
                    column.add('*')
                }
            }
            list[i] = column
        }
        def count = Person.count()
        [posX: hero.posX, posY: hero.posY, list: list, count: count, name: hero.getName()]
    }

    def moveDown() {
        // this is stupid
        Person hero = Person.first()
        hero.moveDown()
        hero.save(flush: true)
        
        redirect controller: 'person', action: 'world'
    }

    def moveUp() {
        Person hero = Person.first()
        hero.moveUp()
        hero.save(flush: true)

        redirect controller: 'person', action: 'world'
    }

    def moveRight() {
        Person hero = Person.first()
        hero.moveRight()
        hero.save(flush: true)

        redirect controller: 'person', action: 'world'
    }

    def moveLeft() {
        Person hero = Person.first()
        hero.moveLeft()
        hero.save(flush: true)

        redirect controller: 'person', action: 'world'
    }

    def error() {
        [name: "Error"]
    }

    def index() {
        def hero = new Person(posX: 1, posY: 1, name: "Derek")
        if (!hero.save(flush: true, failOnError: true)) {
            redirect controller: 'person', action: 'error'
        }
        redirect controller: 'person', action: 'world'
    }

    def show(Person personInstance) {
        respond personInstance
    }

    def create() {
        respond new Person(params)
    }

    @Transactional
    def save(Person personInstance) {
        if (personInstance == null) {
            notFound()
            return
        }

        if (personInstance.hasErrors()) {
            respond personInstance.errors, view:'create'
            return
        }

        personInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'personInstance.label', default: 'Person'), personInstance.id])
                redirect personInstance
            }
            '*' { respond personInstance, [status: CREATED] }
        }
    }

    def edit(Person personInstance) {
        respond personInstance
    }

    @Transactional
    def update(Person personInstance) {
        if (personInstance == null) {
            notFound()
            return
        }

        if (personInstance.hasErrors()) {
            respond personInstance.errors, view:'edit'
            return
        }

        personInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Person.label', default: 'Person'), personInstance.id])
                redirect personInstance
            }
            '*'{ respond personInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Person personInstance) {

        if (personInstance == null) {
            notFound()
            return
        }

        personInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Person.label', default: 'Person'), personInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'personInstance.label', default: 'Person'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
