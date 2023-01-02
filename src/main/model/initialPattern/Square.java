package main.model.initialPattern;

import main.model.initialPattern.InitialPattern;

public class Square extends InitialPattern {

    /**
     * construct a stable square pattern (14,19), (14,20), (15,19), (15,20)
     */
    public Square() {
        super(new int[] {0, 0, 1, 1},
                new int[] {0, 1, 0, 1},
                14, 19, 40);
    }
}
