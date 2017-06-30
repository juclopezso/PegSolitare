package model;

import model.Field.Symbol;

//Logica del juego
public class Game {
    private Board board;
    private Symbol userSymbol;
    private boolean didSomeoneWin; 
    
    public Game() {
        board = new Board();
        board.cruz();
        userSymbol = Symbol.NONE;
        didSomeoneWin = false;
    }
    
    //Print the field Symbol
    public void printField() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(getFieldOwner(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    public void printAccess() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(getFieldAccessible(i, j) + " ");
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
        printAccess();
        printField();
        
        
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
    
    public void setUserSymbol(int i, int j) {
    	if(getFieldOwner(i, j).equals(Symbol.O))
    		userSymbol = Symbol.X;
        else userSymbol = Symbol.O;
    }
    
    public Symbol getUserSymbol() {
        return userSymbol;
    }
  
}