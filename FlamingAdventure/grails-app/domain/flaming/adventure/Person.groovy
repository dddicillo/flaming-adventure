package flaming.adventure

class Person {


	private int posX;
	private int posY;
	private String name;
	private char status = 'x';

	Person(int x, int y, String name) {
		this.posX = x;
		this.posY = y;
		this.name = name;
	}

	void moveUp() {
		posY += 1;
	}

	void moveDown() {
		posY -= 1;
	}

	void moveRight() {
		posX += 1;
	}

	void moveLeft() {
		posX -= 1;
	}




}
