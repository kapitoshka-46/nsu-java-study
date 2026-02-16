package ru.nsu.ccfit.gerasimov2.a.game.model.match3;

import java.util.List;

import ru.nsu.ccfit.gerasimov2.a.game.model.DestroyAlgorithm;
import ru.nsu.ccfit.gerasimov2.a.game.model.GemField;
import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.model.factory.GemFactory;

public class Match3Model implements Model {
    private GemField gemField;
    private DestroyAlgorithm destroyAlgo;

    public Match3Model() {
        gemField = new GemField(4, 4, new GemFactory(4));
        destroyAlgo = new Match3DestroyAlgorithm();
    }

    public List<Position> getPositionsToDestroy() {
        return destroyAlgo.getPositionsToDestroy(gemField);
    }

    @Override
    public boolean swapGems(Position firstSelectedGem, Position secondSelectedGem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swapGems'");
    }

    @Override
    public boolean isDestroyable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDestroyable'");
    }

    @Override
    public GemField getGemField() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGemField'");
    }
}
