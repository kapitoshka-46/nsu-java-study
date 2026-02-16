package ru.nsu.ccfit.gerasimov2.a.game;

import java.time.Duration;

import ru.nsu.ccfit.gerasimov2.a.game.gem.Gem;

public class ConsoleView {
    private void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void drawGemField(GemField gemField) {
        System.out.print("    ");
        for (int i = 0; i < gemField.getCols(); i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        System.out.println("---------------------");

        for (int i = 0; i < gemField.getRows(); i++) {
            System.out.printf("%d | ", i);
            for (int j = 0; j < gemField.getCols(); j++) {
                Gem gem = gemField.at(i, j);
                System.out.print(gem.isDestroyed() ? 'x' : String.valueOf(gem.color));
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("-------------------");

        sleep(Duration.ofSeconds(1)); /* sleep after drawing */

    }

    public void drawScore() {
        System.err.println("no scoree support");
        return;
    }
}
