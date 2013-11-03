package flaming.adventure



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import java.util.Random

@Transactional(readOnly = true)
class PersonController {

    int maxRight = 4
    int maxDown = 4
    Random random = new Random()

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def world() {
        Person hero = Person.first()
        Person monster = Person.find { kind == "monster" }

        List<List> list = new ArrayList<List>();
        for (int i = 0; i < 5; i ++) {
            def column = []
            for (int k = 0; k < 5; k++) {
                if (hero.getPosX() == monster.getPosX() &&
                    hero.getPosY() == monster.getPosY()) {
                    render (view: "attack") 
                } else if (hero.getPosX() == k && hero.getPosY() == i) {
                    column.add('*')
                } else if (monster.getPosX() == k && monster.getPosY() == i) {
                    column.add('M')
                } else {
                    column.add('__')
                }
            }
            list[i] = column
        }
        
        [posX: hero.getPosX(), posY: hero.getPosY(), list: list, name: hero.getKind(), count: Person.count()]
    }

    def moveDown() {
        // this is stupid
        Person hero = Person.first()
        if (hero.getPosY() != 4) {
            hero.moveDown()
            hero.save(flush: true) 
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random)
            monster.save(flush: true)
        }
        
        redirect controller: 'person', action: 'world'
    }

    def moveUp() {
        Person hero = Person.first()
        if (hero.getPosY() != 0) {
            hero.moveUp()
            hero.save(flush: true)
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random)
            monster.save(flush: true)
        }

        redirect controller: 'person', action: 'world'
    }

    def moveRight() {
        Person hero = Person.first()
        if (hero.getPosX() != maxRight) {
            hero.moveRight()
            hero.save(flush: true)
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random)
            monster.save(flush: true)
        }

        redirect controller: 'person', action: 'world'
    }

    def moveLeft() {
        Person hero = Person.first()
        if (hero.getPosX() != 0) {
            hero.moveLeft()
            hero.save(flush: true)
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random)
            monster.save(flush: true)
        }

        redirect controller: 'person', action: 'world'
    }

    def error() {
        [name: "Error"]
    }

    def index() {
        def hero = new Person(
            posX: 1, 
            posY: 1, 
            kind: "player",
            strength: 5
        )
        if (!hero.save(flush: true, failOnError: true)) {
            redirect controller: 'person', action: 'error'
        }

        def monster = new Person(
            posX: 4, 
            posY: 4, 
            kind: "monster",
            strength: 7
        )
        if (!monster.save(flush: true, failOnError: true)) {
            redirect controller: 'person', action: 'error'
        }

        redirect controller: 'person', action: 'world'
    }



    def attackResult() {
        Person hero = Person.first()
        Person monster = Person.find { kind == "monster" }
        def result
        if (hero.getStrength() >= monster.getStrength()) {
            result = true
        } else {
            result = false
        }

        [result: result]

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
