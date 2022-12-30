package main.Model;

public class Player {
    private String name;
    private final CellStatus color;

    //    private final Model.CellCollection cellCollection;
    private int numberOfCells;

    public Player(String name, String color) {
        this.name = name;
        this.color = getStatus(color);
//        cellCollection = new Model.CellCollection(grid, color);
//        this.numberOfCells = cellCollection.getCellNumber();
        /** number of cells should be a field of a player instance and get changed with each instance's life cycle,
         *  do not need to add it to the parameter of constructor
         *  the exact number should be decided by the initial cell pattern and set in Game class (by setter)
         * */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private CellStatus getStatus(String color){
        switch (color){
            case "R" -> {
                return CellStatus.RED;
            }
            case "B" -> {
                return CellStatus.BLUE;
            }
        }
        return null;
    }

    public CellStatus getColor(){
        return color;
    }

//    public int getNumberOfCells() {
//        return cellCollection.getCellNumber();
//    }

}
