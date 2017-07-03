package model;

import java.util.ArrayList;

import model.Field.Symbol;

//Logica del juego
public class Game {
    public Board board;
    private Symbol userSymbol;
    private boolean didSomeoneWin; 
    public ArrayList<Board> boards = new ArrayList<Board>();
    
    public Game() {
        board = new Board();
        board.cruz();
        userSymbol = Symbol.NONE;
        didSomeoneWin = false;
    }
    
    public ArrayList<Board> saveBoard(Board board){
    	boards.add(board);
    	return boards;
    }
    
    //Print the field Symbol
    public void printField() {
         for(int y=0; y<7 ;y++){
			for(int x=0; x<7 ; x++) {
                System.out.print(getFieldOwner(x, y) + " ");
            }
            System.out.println();
        }
    }
    
    public void printAccess() {
    	 for(int y=0; y<7 ;y++){
 			for(int x=0; x<7 ; x++) {
 				System.out.print(getFieldAccessible(x, y) + " ");
            }
            System.out.println();
        }
    }
    //Condition to finish the game
    public boolean getDidSomeoneWin() {
        return didSomeoneWin;
    }
    
    //Condition to game´s end
    public boolean isGameOver() {
        int score = board.evaluateBoard();
        //printAccess();
        //sprintField();
        
        
        if(score == 1){
        	didSomeoneWin = true;
            return true;
        }else return false;
    }
    
    //Set symbol of that field
    public void setFieldOwner(Symbol userSymbol, int x, int y) {
        board.setFieldOwner(userSymbol, x, y);
    }

    //Get Symbol of that field
    public Symbol getFieldOwner(int x, int y) {
        return board.getFieldOwner(x, y);
    }
    
    public void setFieldAccessible(boolean access, int x, int y){
    	board.setFieldAccessible(access, x, y);
    }
    
    public boolean getFieldAccessible(int x, int y) {
        return board.getFieldAccessible(x, y);
    }
    
    public void setUserSymbol(int x, int y) {
    	if(getFieldOwner(x, y).equals(Symbol.O))
    		userSymbol = Symbol.X;
        else userSymbol = Symbol.O;
    }
    
    public Symbol getUserSymbol() {
        return userSymbol;
    }
    public Board getBoard(){
    	return this.board;
    }
}