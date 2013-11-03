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

    public List<List> areaGrid

    int getRows () {
      return this.rows
    }

    void setRows (int rows) {
        this.rows = rows
    }

    int getColumns() {
      return this.columns
    }

    void setColumns(int columns) {
      this.columns = columns
    }

    def getAreaGrid() {
      return this.areaGrid
    }

    void setAreaGrid(List<List> areaGrid) {
      this.areaGrid = areaGrid
    }

    Area(InputStream terrainFile) {
      this.areaGrid = new ArrayList()
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

    boolean validMove(int x, int y) {
      //return (x >= 0 && y >= 0 && x < columns && y < rows && areaGrid.get(y).get(x) == '1')
      //return (x >= 0 && y >= 0 && x < columns && y < rows && answer)
      if (x < 0 || y < 0) {
        return false
      } else if (x >= columns || y >= rows) {
        return false
      } else {
        return true
      }
      
    }

}
