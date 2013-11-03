package flaming.adventure

class Area {

    static constraints = {
    }

    private final int GRID_ROWS = 5;
    private final int GRID_COLUMNS = 5;

    private List<List<Integer>> areaGrid = new ArrayList<>();

    def Area() {
           for (int i = 0; i < GRID_ROWS; i++) {
               List columns = new ArrayList<Integer>();
               for (int j = 0; j < GRID_COLUMNS; j++) {
                   columns.add(0);
               }
               areaGrid.add(columns);
           }
       }
}

def test = new Area();
println test.areaGrid.size() 

i = 0
test.areaGrid.each { row ->
        i++
        println "Row $i\n"
}

