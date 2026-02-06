package ru.nsu.ccfit.gerasimov2.a;

import ru.nsu.ccfit.gerasimov2.a.GemField.GemField;

public abstract class Match3Model {
    GemField gemField;

    public Match3Model(int rows, int cols) {
        gemField = new GemField(rows, cols);
    }   
}
