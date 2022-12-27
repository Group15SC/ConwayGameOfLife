package main.game;

public interface ISubject {

        public void registerObserver(IObserver o);
        public void notifyObserver();

}
