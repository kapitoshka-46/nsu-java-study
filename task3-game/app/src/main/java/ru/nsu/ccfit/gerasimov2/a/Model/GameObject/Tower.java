package ru.nsu.ccfit.gerasimov2.a.Model.GameObject;

import java.time.Duration;

import ru.nsu.ccfit.gerasimov2.a.Model.GameManager;

public class Tower extends DynamicGameObject{
    private Cooldown cooldown;

    public Tower(float x, float y, Duration cooldown) {
        super(x, y);
        this.cooldown = new Cooldown(cooldown);
    }

    private void shoot(GameObject target) {
        
    }

    @Override
    public void act(GameManager gm) {
        // want to call some of these methods
        //gm.getNearestEnemy()
        //gm.getRandomEnemy()
        //gm.getBiggestEnemy()
        
        if (!cooldown.IsReady()) {
            return;
        }

        // spawn a bullet?
        // gm.SpawnObject(new Bellet(x, y))
        System.out.println("shoot");

        cooldown.restart();
        
    }
    
}
