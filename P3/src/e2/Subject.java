package e2;

public interface Subject {
    void registerObserver(Observador o);
    void removeObserver(Observador o);
    void notifyObservers();
}
