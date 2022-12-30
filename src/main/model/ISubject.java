package main.model;

import main.view.IObeserver;

public interface ISubject {

    void registerObserver(IObeserver o);
    void notifyObserver();

}
