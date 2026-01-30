package ru.nsu.ccfit.gerasimov2.a.Model.Field;

import java.util.List;
import java.util.Vector;

import ru.nsu.ccfit.gerasimov2.a.Model.GameObject.GameObject;

public class Cell {

    private List<GameObject> objectsInCell = new Vector<GameObject>();
    
    List<GameObject> getGameObjectsInCell() {
        return objectsInCell;
    }
}
