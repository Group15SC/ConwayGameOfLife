package main.model.initialPattern;

import main.model.initialPattern.InitialPattern;

public class Ship extends InitialPattern {

    /**
     * construct a stable ship pattern (10,22), (10,23), (11,22), (11,24), (12,23), (12,24)
     */
    public Ship(){
        super(new int[] {0, 0, 1, 1, 2, 2},
                new int[] {0, 1, 0, 2, 1, 2},
                10, 22, 40);
    }

}
