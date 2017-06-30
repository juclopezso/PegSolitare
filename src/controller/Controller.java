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
    
    public boolean canMoveRigth(Game game, int i, int j){
		return(i<5 && game.getFieldOwner(i+1, j).equals(Symbol.X) && game.getFieldOwner(i+2, j).equals(Symbol.O)) ? true : false;
	}
    public boolean canMoveLeft(Game game, int i, int j){
		return(i>1 && game.getFieldOwner(i-1, j).equals(Symbol.X) && game.getFieldOwner(i-2, j).equals(Symbol.O)) ? true : false;
	}
    public boolean canMoveUp(Game game, int i, int j){
		return(j>1 && game.getFieldOwner(i, j-1).equals(Symbol.X) && game.getFieldOwner(i, j-2).equals(Symbol.O)) ? true : false;
	}
    public boolean canMoveDown(Game game, int i, int j){
		return(i<5 && game.getFieldOwner(i, j+1).equals(Symbol.X) && game.getFieldOwner(1, j+2).equals(Symbol.O)) ? true : false;
	}
    
	public void setFieldFalse(Game game, int i, int j){
		if(i<5) game.setFieldAccessible(false, i+2, j);
    	if(i>1) game.setFieldAccessible(false, i-2, j);
    	if(j>1) game.setFieldAccessible(false, i, j-2);
    	if(j<5) game.setFieldAccessible(false, i, j+2);	
	}
    
	public void setFieldSymbolO(Game game, int i1, int j1, int i2, int j2){
		if(i2 > i1) game.setFieldOwner(Symbol.O, i1+1, j1);
    	if(i2 < i1) game.setFieldOwner(Symbol.O, i1-1, j1);
    	if(j2 < j1) game.setFieldOwner(Symbol.O, i1, j1-1);            		              
    	if(j2 > j1) game.setFieldOwner(Symbol.O, i1, j1+1);
	}
	
	
	public void setFieldClicked(Game game, int i1, int j1, int i2, int j2){
		game.setFieldAccessible(false, i1, j1);
    	game.setFieldOwner(Symbol.O, i1, j1);
    	game.setFieldOwner(Symbol.X, i2, j2);
	}
	
	public void setAvailableMoves(Game game, int i, int j){
		if(canMoveRigth(game, i, j)) game.setFieldAccessible(true, i+2, j);
        if(canMoveUp(game, i, j)) game.setFieldAccessible(true, i, j-2);
        if(canMoveLeft(game, i, j)) game.setFieldAccessible(true, i-2, j);
        if(canMoveDown(game, i, j)) game.setFieldAccessible(true, i, j+2);
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isGameOver()) {
            int indexOfViewButton = getJButtonIndex((JButton) e.getSource());
            Pair coordinates = convertToCoordinates(indexOfViewButton);
            game.setUserSymbol(coordinates.first, coordinates.second);
            
            if(game.getFieldOwner(coordinates.first, coordinates.second).equals(Symbol.X) && click==1 ){
            	coordinate = coordinates;
            	//Set access true to available moves
            	setAvailableMoves(game, coordinate.first, coordinate.second);
               	click=2;
            	}
            
            if(game.getFieldAccessible(coordinates.first, coordinates.second) && click==2 ){
            	coordinate2=coordinates;
            	//Set field clicked
            	setFieldClicked(game, coordinate.first, coordinate.second, coordinate2.first, coordinate2.second);
                //Set accessible to false
            	setFieldFalse(game, coordinate.first, coordinate.second);
            	//Set Symbol to O
            	setFieldSymbolO(game, coordinate.first, coordinate.second, coordinate2.first, coordinate2.second);
            	click=1;
            }
            if(game.getFieldOwner(coordinates.first, coordinates.second).equals(Symbol.O))
            	click=1;
            view.updateBoard(game.getFieldOwner(coordinates.first, coordinates.second), (JButton) e.getSource());
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
        int first = 0, second = 0; 
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