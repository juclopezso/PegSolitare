package controller;

import model.Field.Symbol;
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
    private int click = 1;
    private Pair coordinate;
    private Pair coordinate2;
    
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

            int indexOfViewButton = getJButtonIndex((JButton) e.getSource());
            Pair coordinates = convertToCoordinates(indexOfViewButton);

            if(game.getFieldOwner(coordinates.first, coordinates.second).equals(Symbol.X) && click==1 ){
            	coordinate = coordinates;
               if(coordinate.first<6 && game.getFieldOwner(coordinate.first+1, coordinate.second).equals(Symbol.X) 
        			   && game.getFieldOwner(coordinate.first+2, coordinate.second).equals(Symbol.O))
            					game.setFieldAccessible(true, coordinate.first+2, coordinate.second);
                
               if(coordinate.second>1 && game.getFieldOwner(coordinate.first, coordinate.second-1).equals(Symbol.X) 
          				&& game.getFieldOwner(coordinate.first, coordinate.second-2).equals(Symbol.O))
               					game.setFieldAccessible(true, coordinate.first, coordinate.second-2);
   
                
              	if(coordinate.first>1 && game.getFieldOwner(coordinate.first-1, coordinate.second).equals(Symbol.X) 
          				&& game.getFieldOwner(coordinate.first-2, coordinate.second).equals(Symbol.O))
              					game.setFieldAccessible(true, coordinate.first-2, coordinate.second);
              	
               if(coordinate.second<6 && game.getFieldOwner(coordinate.first, coordinate.second+1).equals(Symbol.X) 
          				&& game.getFieldOwner(coordinate.first, coordinate.second+2).equals(Symbol.O))
               					game.setFieldAccessible(true, coordinate.first, coordinate.second+2);
               	
               	click=2;
            	}
            
            if(game.getFieldAccessible(coordinates.first, coordinates.second) && click==2 ){
            	coordinate2=coordinates;
            	
            	game.setFieldAccessible(false, coordinate.first, coordinate.second);
            	game.setFieldOwner(Symbol.O, coordinate.first, coordinate.second);
            	game.setFieldOwner(Symbol.X, coordinate2.first, coordinate2.second);
            	//Set accessible to false
            	if(coordinate.first<5) game.setFieldAccessible(false, coordinate.first+2, coordinate.second);
            	if(coordinate.first>1) game.setFieldAccessible(false, coordinate.first-2, coordinate.second);
            	if(coordinate.second>1) game.setFieldAccessible(false, coordinate.first, coordinate.second-2);
            	if(coordinate.second<5) game.setFieldAccessible(false, coordinate.first, coordinate.second+2);	
            	//Set Symbol to O
            	if(coordinate2.first > coordinate.first) game.setFieldOwner(Symbol.O, coordinate.first+1, coordinate.second);
            	if(coordinate2.first < coordinate.first) game.setFieldOwner(Symbol.O, coordinate.first-1, coordinate.second);
            	if(coordinate2.second < coordinate.second) game.setFieldOwner(Symbol.O, coordinate.first, coordinate.second-1);            		              
            	if(coordinate2.second > coordinate.second) game.setFieldOwner(Symbol.O, coordinate.first, coordinate.second+1);
            	
            	click=1;
            }
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