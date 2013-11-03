package flaming.adventure

class Person {


	public int posX;
	public int posY;
	public String kind;
	public int strength;
	//private char status = 'x';

	//Person(int x, int y, String name) {
	//	this.posX = x;
	//	this.posY = y;
	//	this.name = name;
	//}

	void moveUp() {
		posY -= 1;
	}

	void moveDown() {
		posY += 1;
	}

	void moveRight() {
		posX += 1;
	}

	void moveLeft() {
		posX -= 1;
	}

	String getKind() {
		return this.kind
	}

	void setKind (String kind) {
		this.kind = kind
	}

	int getPosX() {
		return this.posX
	}

	void setPosX(int posX) {
		this.posX = posX
	}

	int getPosY() {
		return this.posY
	}

	void setPosY(int posY) {
		this.posY = posY
	}

	void setStrength(int strength) {
		this.strength = strength
	}

	int getStrength() {
		return this.strength
	}



	void moveMonster(Random random, Area terrain, List<List> presentAreaGrid) {
        int direction = random.nextInt(5)
        int x = this.getPosX()
        int y = this.getPosY()
        switch (direction) {
            case 0:
				if (terrain.validMove(x, y-1) && (presentAreaGrid.get(y-1).get(x) == '1')) {
                    this.moveUp()
                    break
                }
            case 1:
                if (terrain.validMove(x+1, y) && (presentAreaGrid.get(y).get(x+1) == '1')) {
                    this.moveRight()
                    break
                }
            case 2:
                if (terrain.validMove(x, y+1) && (presentAreaGrid.get(y+1).get(x) == '1')) {
                    this.moveDown()
                    break
                }
            case 3:
                if (terrain.validMove(x-1, y) && (presentAreaGrid.get(y).get(x-1) == '1')) {
                    this.moveLeft()
                    break
                }
            default:
            	// stay right where you are
            	this.setPosX(x)
            	this.setPosY(y)

        }
    }

}
