package controller;

import model.Field.Symbol;
import model.Board;
import model.Game;
import utils.Pair;
import view.View;
import view.ViewInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Controller implements ActionListener {
    private Game game;
    //private ViewInterface view;
    private View view;
    private int click = 1;
    private int[] coordinate  = new int[2];
    private int[] coordinate2 = new int[2];
    private ArrayList<IntPair> moves = new ArrayList<IntPair>();    
    
    public Controller() {
        this.game = new Game();
        this.view = new View();
        addActionListeners();
        setUp();
        
        
    }
    public void setUp(){
    	for(int y=0; y<7 ;y++){
			for(int x=0; x<7 ; x++) {
				view.updateBoard(game.getBoard().getFieldOwner(x, y),view.getButton(x, y));
			}
		}
    }
    
    private void addActionListeners() {
    	for(int y=0; y<7 ;y++){
			for(int x=0; x<7 ; x++){
				view.getButton(x,y).addActionListener(this);
			}
		}
    }
    
    public boolean canMoveRigth(Game game, int x, int y){
		return(x<5 && game.getFieldOwner(x+1, y).equals(Symbol.X) && game.getFieldOwner(x+2, y).equals(Symbol.O)) ? true : false;
	}
    public boolean canMoveLeft(Game game, int x, int y){
		return(x>1 && game.getFieldOwner(x-1, y).equals(Symbol.X) && game.getFieldOwner(x-2, y).equals(Symbol.O)) ? true : false;
	}
    public boolean canMoveUp(Game game, int x, int y){
		return(y>1 && game.getFieldOwner(x, y-1).equals(Symbol.X) && game.getFieldOwner(x, y-2).equals(Symbol.O)) ? true : false;
	}
    public boolean canMoveDown(Game game, int x, int y){
		return(x<5 && game.getFieldOwner(x, y+1).equals(Symbol.X) && game.getFieldOwner(1, y+2).equals(Symbol.O)) ? true : false;
	}
    
	public void setFieldFalse(Game game, int x, int y){
		if(x<5) game.setFieldAccessible(false, x+2, y);
    	if(x>1) game.setFieldAccessible(false, x-2, y);
    	if(y>1) game.setFieldAccessible(false, x, y-2);
    	if(y<5) game.setFieldAccessible(false, x, y+2);	
	}
    
	public void setFieldSymbolO(Game game, int x1, int y1, int x2, int y2){
		if(x2 > x1) game.setFieldOwner(Symbol.O, x1+1, y1);
    	if(x2 < x1) game.setFieldOwner(Symbol.O, x1-1, y1);
    	if(y2 < y1) game.setFieldOwner(Symbol.O, x1, y1-1);            		              
    	if(y2 > y1) game.setFieldOwner(Symbol.O, x1, y1+1);
	}
	
	
	public void setFieldClicked(Game game, int x1, int y1, int x2, int y2){
		game.setFieldAccessible(false, x1, y1);
    	game.setFieldOwner(Symbol.O, x1, y1);
    	game.setFieldOwner(Symbol.X, x2, y2);
	}
	
	public void setAvailableMoves(Game game, int x, int y){
		if(canMoveRigth(game, x, y)) game.setFieldAccessible(true, x+2, y);
        if(canMoveUp(game, x, y)) game.setFieldAccessible(true, x, y-2);
        if(canMoveLeft(game, x, y)) game.setFieldAccessible(true, x-2, y);
        if(canMoveDown(game, x, y)) game.setFieldAccessible(true, x, y+2);
	}
	
	public void setBoard(){
		for(int i=0; i<7; i++){
			for(int j=0; j<7; j++){
				game.board = game.boards.get(game.boards.size()-1);
			}
		}
	}
	
	public void saveMoves(int x, int y){
		IntPair pair = new IntPair(x,y);
		moves.add(pair);
	}
	
	public void printPair(IntPair pair){
		System.out.println(pair.x + " " + pair.y);
	}
	
	public void printMoves(){
		for(int i=0; i<moves.size(); i++)
    		printPair(moves.get(i));
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
            	//Save moves of the move
            	saveMoves(coordinate[0], coordinate[1]);
            	saveMoves(coordinate2[0], coordinate2[1]);
         
            	//Save the board before the move
            	game.saveBoard(game.getBoard());
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
            if (button == view.getButton(x,y)) {
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
        return game.isGameOver();//los prints de los tableros
    }
    public View getView(){
    	
    	return this.view;
    }
    public Game getGame(){
    	
    	return this.game;
    }
}