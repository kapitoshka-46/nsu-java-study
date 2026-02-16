package ru.nsu.ccfit.gerasimov2.a.game.model;

import java.util.List;

public interface DestroyAlgorithm {
    List<Position> getPositionsToDestroy(GemField gemField);

    boolean isDestroyable(GemField gemField);
}
