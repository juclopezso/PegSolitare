package controller;

import model.Game;
import utils.Pair;
import view.View;
import view.ViewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Controller implements ActionListener {
    private Game game;
    private ViewInterface view;
    
    public Controller() {
        this.game = new Game();
        this.view = new View();
        addActionListeners();
    }

    private void addActionListeners() {
        for (int i = 0; i < ((View)view).getNumberOfButtons(); i++) {
            ((View)view).getButton(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isGameOver()) {
            game.setUserSymbol();

            int indexOfViewButton = getJButtonIndex((JButton) e.getSource());

            Pair coordinates = convertToCoordinates(indexOfViewButton);
            game.setFieldOwner(game.getUserSymbol(),
                               coordinates.first,
                               coordinates.second);

            view.updateBoard(game.getUserSymbol(), (JButton) e.getSource());
        }
    }

    private int getJButtonIndex(JButton button) {
    	
        int buttonIndex = 0;
        for (int i = 0; i < 49; i++) {
            if (button == ((View)view).getButton(i)) {
                buttonIndex = i;
            }
        }
        return buttonIndex;
    }

    private Pair convertToCoordinates(int index) {
        int first = 0, second = 0; // forced initialization
        for(int i=0; i<49; i++){
        	if(index==i){
        		first=i/7;
        		second=i%7;
        	}
        }

        return Pair.create(first, second);
    }

    public void informOutcome() {
        if (game.getDidSomeoneWin()) {
            view.informWin(game.getUserSymbol());
        } else {
            view.informTie();
        }
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }
}