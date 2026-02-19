package ru.nsu.ccfit.gerasimov2.a.game.model;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.ccfit.gerasimov2.a.game.Observable;
import ru.nsu.ccfit.gerasimov2.a.game.Observer;
import ru.nsu.ccfit.gerasimov2.a.game.model.gem.Gem;

public abstract class Model implements Observable {

    List<Observer> observers;

    public Model() {
        observers = new ArrayList<>();
    }


    // ============= Model interface ================== //
    abstract public List<Position> getPositionsToDestroy();

    abstract public boolean isDestroyable();

    abstract public GemField getGemField();

    abstract public Gem gemAt(Position pos);


    // =============== Observer pattern methods ================= //
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) { o.update(this); }
    }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    public void notifyView() { notifyObservers(); }


    public abstract boolean makeMove(Position p1, Position p2);


    public abstract void step();
}
