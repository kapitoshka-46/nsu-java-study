package ru.nsu.ccfit.gerasimov2.a.game.view;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import ru.nsu.ccfit.gerasimov2.a.game.model.GemField;
import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.model.gem.Gem;

public class ConsoleView extends View {
    List<String> messages = new ArrayList<>();
    
    private void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void displayGemField(Model model) {
        GemField gemField = model.getGemField();
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

        System.out.println("Messages:");
        showMessagesAndDelete();        
        sleep(Duration.ofSeconds(1)); /* sleep after drawing */

    }
    private void showMessagesAndDelete() {
        for (String msg: messages) {
            System.out.println(msg);
        }
    }

    @Override
    public void displayScore(int score) {
        throw new UnsupportedOperationException("Unimplemented method 'drawScore'");
    }

    @Override
    public void update(Object obj) {
        displayGemField((Model)obj);
    }

    @Override
    public void message(String msg) {
        messages.add(msg);
    }

    @Override
    public void displayUserSelection(Position pos) {
        messages.add("Selected " + pos.toString());        
    }

    
}
