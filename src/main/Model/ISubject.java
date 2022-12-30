package main.Model;

import main.View.IObeserver;

public interface ISubject {

    void registerObserver(IObeserver o);
    void notifyObserver();

}
