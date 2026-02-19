package ru.nsu.ccfit.gerasimov2.a.game.view;

import ru.nsu.ccfit.gerasimov2.a.game.Observer;
import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;

public abstract class View implements Observer {
    public abstract void displayGemField(Model model);
    public abstract void displayScore(int score);
    public abstract void update(Object obj);
    public abstract void message(String string);
    public abstract void displayUserSelection(Position pos);
    
}
