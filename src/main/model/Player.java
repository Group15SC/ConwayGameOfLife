package main.model;

public class Player {
    private String name;
    private final CellStatus color;

    public Player(String name, String color) {
        this.name = name;
        this.color = getStatus(color);
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

}
