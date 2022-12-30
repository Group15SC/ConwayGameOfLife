package main.model.initialPattern;

public class Boat extends InitialPattern {
    public Boat() {
        super(new int[]{0, 0, 1, 1, 2},
                new int[] {0, 1, 0, 2, 1},
                10, 15, 40);
    }

}
