package ru.nsu.ccfit.gerasimov2.a.Model.GameObject;

import ru.nsu.ccfit.gerasimov2.a.Model.GameManager;

public abstract class DynamicGameObject extends GameObject {
    public abstract void act(GameManager gm);

    public DynamicGameObject(float x, float y) {
        super(x, y);
    }

    
}
