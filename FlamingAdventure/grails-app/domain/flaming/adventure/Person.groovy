package flaming.adventure

class Person {


	private int posX;
	private int posY;
	private String name;
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

	String getName() {
		return this.name
	}

	void setName(String name) {
		this.name = name
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


}
