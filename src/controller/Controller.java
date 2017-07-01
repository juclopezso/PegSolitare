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
    private int[] coordinate  = new int[2];
    private int[] coordinate2 = new int[2] ;
    
    public Controller() {
        this.game = new Game();
        this.view = new View();
        addActionListeners();
    }

    private void addActionListeners() {
    	for(int y=0; y<7 ;y++){
			for(int x=0; x<7 ; x++){
            ((View)view).getButton(x,y).addActionListener(this);
			}
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
        	
            int[] indexOfViewButton = getJButtonIndex((JButton) e.getSource());
           
            game.setUserSymbol(indexOfViewButton[0], indexOfViewButton[1]);
            
            if(game.getFieldOwner(indexOfViewButton[0], indexOfViewButton[1]).equals(Symbol.X) && click==1 ){
            	coordinate = indexOfViewButton;
            	//Set access true to available moves
            	setAvailableMoves(game, coordinate[0], coordinate[1]);
               	click=2;
            	}
            
            if(game.getFieldAccessible(indexOfViewButton[0], indexOfViewButton[1]) && click==2 ){
            	coordinate2=indexOfViewButton;
            	//Set field clicked
            	setFieldClicked(game, coordinate[0], coordinate[1], coordinate2[0], coordinate2[1]);
                //Set accessible to false
            	setFieldFalse  (game, coordinate[0], coordinate[1]);
            	//Set Symbol to O
            	setFieldSymbolO(game, coordinate[0], coordinate[1], coordinate2[0], coordinate2[1]);
            	click=1;
            }
            if(game.getFieldOwner(indexOfViewButton[0], indexOfViewButton[1]).equals(Symbol.O))
            	click=1;
            view.updateBoard(game.getFieldOwner(indexOfViewButton[0], indexOfViewButton[1]), (JButton) e.getSource());
        }
    }

    private int[] getJButtonIndex(JButton button) {
    	
        int butIndX = 0;
        int butIndY = 0;
        for(int y=0; y<7 ;y++){
			for(int x=0; x<7 ; x++) {
            if (button == ((View)view).getButton(x,y)) {
                butIndX = x;
                butIndY = y;
                }
            }
        }
        
        return  new int[] {butIndX,butIndY};
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