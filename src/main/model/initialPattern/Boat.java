package main.model.initialPattern;

public class Boat extends InitialPattern {

    /**
     * construct a stable boat pattern (10,15), (10,16), (11,15), (11,17), (12,16)
     */
    public Boat() {
        super(new int[]{0, 0, 1, 1, 2},
                new int[] {0, 1, 0, 2, 1},
                10, 15, 40);
    }

}
