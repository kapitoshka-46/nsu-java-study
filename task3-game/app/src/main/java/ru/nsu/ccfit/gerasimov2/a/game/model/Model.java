package ru.nsu.ccfit.gerasimov2.a.game.model;

import java.util.List;

public interface Model {

    List<Position> getPositionsToDestroy();

    boolean swapGems(Position firstSelectedGem, Position secondSelectedGem);

    boolean isDestroyable();

    GemField getGemField();
    
}
