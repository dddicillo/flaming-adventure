package flaming.adventure



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def title() {
        def hero = new Person(0, 0, "Josh")
        List<List> list = new ArrayList<List>();
        int c = 0
        for (int i = 0; i < 5; i ++) {
            def column = []
            for (int k = 0; k < 5; k++) {
                column.add(0)
            }
            list[i] = column
        }
        [name: hero.name, list: list]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Person.list(params), model:[personInstanceCount: Person.count()]
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
