package flaming.adventure

class Area {

    static constraints = {
    }

    private final int GRID_ROWS = 4;
    private final int GRID_COLUMNS = 4;
    enum terrainType {
    	WATER(0),
    	GRASS(1)
    }

    private List<List> areaGrid = new ArrayList();

    Area() {
    	new File("../../../lib/terrain.txt")
           for (int i = 0; i < GRID_ROWS; i++) {
               List columns = new ArrayList();
               for (int j = 0; j < GRID_COLUMNS; j++) {
                   	columns.add(terrainFile.readline());
               }
               areaGrid.add(columns);
           }
       }
}
