package model;

import model.Field.Symbol;

//Logica del juego
public class Game {
    private Board board;
    
    //private int turnsCounter;
    private Symbol userSymbol;
    private boolean didSomeoneWin; 
    
    public Game() {
        board = new Board();
        
        //turnsCounter = 0;
        userSymbol = Symbol.NONE;
        didSomeoneWin = false;
    }

    public void setFieldOwner(Symbol userSymbol, int x, int y) {
        board.setFieldOwner(userSymbol, x, y);
    }

    //Solo para imprimir en consola
    public Symbol getFieldOwner(int x, int y) {
        return board.getFieldOwner(x, y);
    }
    //Imprime en cosola
    public void printField() {
        System.out.println("---PRINTING FIELD---");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(getFieldOwner(i, j) + " ");
            }
            System.out.println();
        }
    }
    //Evalua si el juego ya temina
    public boolean isGameOver() {
        int score = board.evaluateBoard();
        printField();
        
        if(score == 1){
        	didSomeoneWin = true;
            return true;
        }else return false;
    }
    
    /*public void incrementTurnsCounter() {
        turnsCounter++;
    }
    
        public int getTurnsCounter() {
        return turnsCounter;
    }
    
       	public void incTurnCounterAndSetUserSymbol() {
        setUserSymbol();
    }
    */
   
    
    public void setUserSymbol() {
    	if (userSymbol.equals(Symbol.X)) userSymbol = Symbol.O;
    	else userSymbol = Symbol.X;
        }
    
    public Symbol getUserSymbol() {
        return userSymbol;
    }
    
    public boolean getDidSomeoneWin() {
        return didSomeoneWin;
    }
}