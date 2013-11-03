package flaming.adventure

class Area {

    static constraints = {
    }

    private final int GRID_ROWS = 4
    private final int GRID_COLUMNS = 4
    enum terrainType {
    	WATER(0),
    	GRASS(1)
    }

    private List<List> areaGrid = new ArrayList()

    Area(InputStream terrainFile) {
    	terrainFile.eachLine { line ->
   			List row = new ArrayList() 
    		line.each { tile -> 
    			row.add(tile)
    		}
    		areaGrid.add(row)
    	}
           /*for (int i = 0; i < GRID_ROWS; i++) {
               List columns = new ArrayList()
               for (int j = 0; j < GRID_COLUMNS; j++) {
                   	columns.add(terrainFile.readLine())
               }
               areaGrid.add(columns);
           }*/
       }
}
