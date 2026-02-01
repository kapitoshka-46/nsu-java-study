package ru.nsu.ccfit.gerasimov2.a;

public abstract class Gem {
    public abstract boolean checkMatch(GemField gf);
    public final int color;
    int row;
    int col;

    public Gem(int color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }
    
}
