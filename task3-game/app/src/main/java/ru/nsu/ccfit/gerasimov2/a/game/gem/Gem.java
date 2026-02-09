package ru.nsu.ccfit.gerasimov2.a.game.gem;

import ru.nsu.ccfit.gerasimov2.a.game.GemField;
import ru.nsu.ccfit.gerasimov2.a.game.Position;

public abstract class Gem {
    public final int color;
    
    public abstract void destroyEfffect(GemField gemField, Position pos);
    public abstract boolean isDestroyed();

    public Gem(int color) {
        this.color = color;
    }
    public abstract boolean isGoingToBeDestroyed();
}
