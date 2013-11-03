package flaming.adventure

class Area {

    static constraints = {
    }

    enum terrainType {
    	WATER(0),
    	GRASS(1)
    }
    public int rows
    public int columns

    private List<List> areaGrid = new ArrayList()

    Area(InputStream terrainFile) {
    	terrainFile.eachLine { line ->
   			List row = new ArrayList()
        int currentColumns = 0
    		line.each { tile -> 
    			row.add(tile)
    		}
    		areaGrid.add(row)
        this.rows++
    	}
      columns = setColumns()
    }

    // Get max column count among all rows
    int setColumns() {
      columns = 0
      this.areaGrid.each { row ->
        def currentColumns = 0
        row.each {
          currentColumns++
        }
        if (currentColumns > columns) {
          columns = currentColumns
        }
      }
      this.columns = columns
    }
}
