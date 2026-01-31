package ru.nsu.ccfit.gerasimov2.a.Model.GameObject;

import java.time.Instant;
import java.time.Duration;

public class Cooldown {
    private Instant prev;
    private final Duration cooldown;

    public Cooldown(Duration cooldown) {
        this.cooldown = cooldown;
        this.prev = Instant.now();
    }

    public void restart() {
        this.prev = Instant.now();
    }

    public boolean IsReady() {
        Instant curr = Instant.now();
        Duration diff = Duration.between(prev, curr );
        return diff.compareTo(cooldown) >= 0;   // diff >= cooldown
    }
}
