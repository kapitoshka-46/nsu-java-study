package ru.nsu.ccfit.gerasimov2.a.Model.Field;

import java.util.List;
import java.util.Vector;

import ru.nsu.ccfit.gerasimov2.a.Model.GameObject.StaticGameObject;

public class Cell {

    private List<StaticGameObject> staticObjectsInCell = new Vector<StaticGameObject>();
    
    List<StaticGameObject> getGameObjectsInCell() {
        return staticObjectsInCell;
    }
}
