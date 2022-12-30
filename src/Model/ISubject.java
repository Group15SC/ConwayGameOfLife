package Model;

import View.IObeserver;

public interface ISubject {

    void registerObserver(IObeserver o);
    void notifyObserver();

}
