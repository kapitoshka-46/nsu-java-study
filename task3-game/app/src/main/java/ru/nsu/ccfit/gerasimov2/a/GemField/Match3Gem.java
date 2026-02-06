package ru.nsu.ccfit.gerasimov2.a.GemField;

// Default gem that destroyes when stayin in line of 3 with gems with the same color
public class Match3Gem extends Gem {

    boolean isDestroyed;
    public Match3Gem(int color) { super(color); }

    @Override
    public void destroy() { isDestroyed = true; }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;    
    }
  
}
