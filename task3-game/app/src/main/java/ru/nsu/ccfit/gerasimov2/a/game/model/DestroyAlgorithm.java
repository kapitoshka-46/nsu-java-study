package ru.nsu.ccfit.gerasimov2.a.game.destroyalgo;

import java.util.List;

import ru.nsu.ccfit.gerasimov2.a.game.GemField;
import ru.nsu.ccfit.gerasimov2.a.game.Position;

public interface DestroyAlgorithm {
    List<Position> getPositionsToDestroy(GemField gemField);

    boolean isDestroyable(GemField gemField);
}
