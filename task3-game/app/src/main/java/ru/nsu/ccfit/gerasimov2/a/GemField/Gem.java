package ru.nsu.ccfit.gerasimov2.a.GemField;


/* idea: create gems that should be destroyed multiple times! */
public abstract class Gem {
    public final int color;

    public abstract void destroy();
    public abstract boolean isDestroyed();
    public Gem(int color) {
        this.color = color;
    } 
    public Gem(Gem other) {
        this.color = other.color;
    };
}
