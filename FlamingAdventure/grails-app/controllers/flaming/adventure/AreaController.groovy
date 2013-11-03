package flaming.adventure

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AreaController {


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    Random random = new Random()
    def presentAreaGrid

    def index() {
        def hero = new Person(
            posX: 0, 
            posY: 0, 
            kind: "player",
            strength: 5
        )
        if (!hero.save(flush: true, failOnError: true)) {
            redirect controller: 'person', action: 'error'
        }


        def monster = new Person(
            posX: 5, 
            posY: 0, 
            kind: "monster",
            strength: 7
        )
        if (!monster.save(flush: true, failOnError: true)) {
            redirect controller: 'person', action: 'error'
        }


        redirect (controller: 'area', action: 'dungeon', params: [fileName: "terrain.txt"])

    }

    def colors() {
        def colorboard = new Area(this.class.classLoader.getResourceAsStream("colorboard.txt"))
    }

    def dungeon() {

        Area terrain = new Area(this.class.classLoader.getResourceAsStream(${params.fileName}))
        terrain.save(flush: true)
        presentAreaGrid = terrain.getAreaGrid()
        Person hero = Person.first()
        Person monster = Person.find { kind == "monster" }
        int playerX = hero.getPosX()
        int playerY = hero.getPosY()
        int monsterX = monster.getPosX()
        int monsterY = monster.getPosY()
        if (playerX == monsterX && playerY == monsterY) {
            render (view: "attack") 
        }
        [grid: terrain.areaGrid, rows: terrain.rows, columns: terrain.columns, playerX: playerX, playerY: playerY, monsterX: monsterX, monsterY: monsterY]
    }



    def moveDown() {
        // this is stupid
        Person hero = Person.first()
        Area terrain = Area.first()

        def x = hero.getPosX()
        def y = hero.getPosY()+1
        if (terrain.validMove(x, y) && (presentAreaGrid.get(y).get(x) == '1')) {
            hero.moveDown()
            if (hero.getPosX() == 6 && hero.getPosY() == 0) {
                hero.setPosX(6)
                hero.setPosY(6)
                hero.save(flush: true)
                redirect (controller: 'area', action: 'dungeon', params: [fileName: "terrainUp.txt"])
            }
            hero.save(flush: true) 
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random, terrain, presentAreaGrid)
            monster.save(flush: true)
        }
        
        redirect controller: 'area', action: 'dungeon'
    }

    def moveUp() {
        Person hero = Person.first()
        Area terrain = Area.first()
        def x = hero.getPosX()
        def y = hero.getPosY()-1
        if (terrain.validMove(x, y) && (presentAreaGrid.get(y).get(x) == '1')) {
            hero.moveUp()
            hero.save(flush: true)
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random, terrain, presentAreaGrid)
            monster.save(flush: true)
        }

        redirect controller: 'area', action: 'dungeon'
    }

    def moveRight() {
        Person hero = Person.first()
        Area terrain = Area.first()
        def x = hero.getPosX()+1
        def y = hero.getPosY()
        if (terrain.validMove(x, y) && (presentAreaGrid.get(y).get(x) == '1')) {
            hero.moveRight()
            hero.save(flush: true)
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random, terrain, presentAreaGrid)
            monster.save(flush: true)
        }

        redirect controller: 'area', action: 'dungeon'
    }

    def moveLeft() {
        Person hero = Person.first()
        Area terrain = Area.first()
        def x = hero.getPosX()-1
        def y = hero.getPosY()
        if (terrain.validMove(x, y) && (presentAreaGrid.get(y).get(x) == '1')) {
            hero.moveLeft()
            hero.save(flush: true)
            Person monster = Person.find { kind == "monster" }
            monster.moveMonster(random, terrain, presentAreaGrid)
            monster.save(flush: true)
        }

        redirect controller: 'area', action: 'dungeon'
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

    def roomUp



    @Transactional
    def save(Area areaInstance) {
        if (areaInstance == null) {
            notFound()
            return
        }

        if (areaInstance.hasErrors()) {
            respond areaInstance.errors, view:'create'
            return
        }

        areaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'areaInstance.label', default: 'Area'), areaInstance.id])
                redirect areaInstance
            }
            '*' { respond areaInstance, [status: CREATED] }
        }
    }

    def edit(Area areaInstance) {
        respond areaInstance
    }

    @Transactional
    def update(Area areaInstance) {
        if (areaInstance == null) {
            notFound()
            return
        }

        if (areaInstance.hasErrors()) {
            respond areaInstance.errors, view:'edit'
            return
        }

        areaInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Area.label', default: 'Area'), areaInstance.id])
                redirect areaInstance
            }
            '*'{ respond areaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Area areaInstance) {

        if (areaInstance == null) {
            notFound()
            return
        }

        areaInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Area.label', default: 'Area'), areaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'areaInstance.label', default: 'Area'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
