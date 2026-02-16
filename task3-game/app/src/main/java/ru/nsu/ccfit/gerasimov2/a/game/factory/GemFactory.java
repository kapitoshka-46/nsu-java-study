package ru.nsu.ccfit.gerasimov2.a.game.factory;

import ru.nsu.ccfit.gerasimov2.a.game.gem.DefaultGem;
import ru.nsu.ccfit.gerasimov2.a.game.gem.Gem;

public class GemFactory {
    private int maxColor;

    public GemFactory(int maxColor) {
        this.maxColor = maxColor;
    }

    public Gem newGem() {
        int color = (int) (Math.random() * maxColor) + 1;
        return new DefaultGem(color);
    }

}