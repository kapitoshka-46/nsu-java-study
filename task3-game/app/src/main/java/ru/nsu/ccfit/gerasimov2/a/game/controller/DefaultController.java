package ru.nsu.ccfit.gerasimov2.a.game.controller;

import ru.nsu.ccfit.gerasimov2.a.game.model.Model;
import ru.nsu.ccfit.gerasimov2.a.game.model.Position;
import ru.nsu.ccfit.gerasimov2.a.game.view.View;

public class DefaultController extends Controller {
    private Position firstSelection;
    private Position secondSelection;
    private boolean isRunning = true;

    public DefaultController(Model model, View view) {
        super(model, view);
    }

    // DefaultController accept only 2 inputs!
    public void handleInput(Position userSelection) {
        if (userSelection == null) { return; }
        
        if (firstSelection == userSelection) {  // gets the same selection
            firstSelection = null;  // deselect
            return;
        }
        if (secondSelection == userSelection) {
            secondSelection = null; // deselect
            return;
        }
        
        if (firstSelection == null) {
            firstSelection = userSelection;
        } else if (secondSelection == null) {
            secondSelection = userSelection;
            boolean success = model.makeMove(firstSelection, secondSelection);
            if (!success) {
                view.message("Wrong move");
            }

            model.step(); // say model to process the move

            firstSelection = null;  // reset selection
            secondSelection = null;
        }
        else {
            throw new IllegalStateException("Both selections are set but no move was done");
        }

    }

    public void runGame() {
        model.step();
        while (isRunning) {
            handleInput(view.getSelection());  
        }
        
    }
}

