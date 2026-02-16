package ru.nsu.ccfit.gerasimov2.a.game.view;

import ru.nsu.ccfit.gerasimov2.a.game.model.GemField;

public interface View {
    void drawGemField(GemField gemField);

    void drawScore(int score);
}
