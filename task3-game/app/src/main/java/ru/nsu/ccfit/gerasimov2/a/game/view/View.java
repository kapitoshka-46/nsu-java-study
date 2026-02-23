package ru.nsu.ccfit.gerasimov2.a.game.view;

import ru.nsu.ccfit.gerasimov2.a.game.Observer;
import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;

public abstract class View implements Observer {
    protected Model model;

    public View(Model model) {
        this.model = model;
    }

    public abstract void update();
    public abstract void message(String string);
    public abstract void setUserSelection(Position pos);
    public abstract void unsetAllUserSelections(Position pos);
    public abstract void unsetUserSelections(Position pos);
    public abstract void displayMessage(String string);
    
}
