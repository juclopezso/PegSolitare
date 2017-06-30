package model;

import model.Field.Symbol;

public class Board {
    private Field[][] gameGrid;
    private static final int BOARD_SIDE_LENGTH = 7;
    
    public Board() {
        gameGrid = new Field[BOARD_SIDE_LENGTH][BOARD_SIDE_LENGTH];
        
        // initializes the board with Symbol.None
        for (int i = 0; i < BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < BOARD_SIDE_LENGTH; j++) {
                gameGrid[i][j] = Field.getDefault();
                gameGrid[i][j].setAccessible(false);
                if((i<2 && j<2) || ((i>4 && i<7) && j<2) || ((j>4 && j<7) && i<2) || (i>4 && j>4)){
                	gameGrid[i][j].setAccessible(false);
                	gameGrid[i][j].setOwner(Symbol.O);
                }
            }
        }
    }
    
    public void cruz(){
    	gameGrid[1][3].setOwner(Symbol.X);
    	gameGrid[2][3].setOwner(Symbol.X);
    	gameGrid[3][3].setOwner(Symbol.X);
    	gameGrid[4][3].setOwner(Symbol.X);
    	gameGrid[2][2].setOwner(Symbol.X);
    	gameGrid[2][4].setOwner(Symbol.X);
    }
    
    public void cruzLong(){
    	gameGrid[0][3].setOwner(Symbol.X);
    	gameGrid[1][3].setOwner(Symbol.X);
    	gameGrid[2][3].setOwner(Symbol.X);
    	gameGrid[3][3].setOwner(Symbol.X);
    	gameGrid[4][3].setOwner(Symbol.X);
    	gameGrid[2][2].setOwner(Symbol.X);
    	gameGrid[2][4].setOwner(Symbol.X);
    	gameGrid[2][5].setOwner(Symbol.X);
    	gameGrid[2][1].setOwner(Symbol.X);
    	gameGrid[5][3].setOwner(Symbol.X);
    }
    
    public int evaluateBoard() {
        int score = 0;
        for (int i = 0; i < BOARD_SIDE_LENGTH; i++){
        	for (int j = 0; j < BOARD_SIDE_LENGTH; j++){
        		if(gameGrid[i][j].getOwner()==Symbol.X)
        			score++;
        	}
        }
        return score;
    }
    
    //Get Field Symbol
    public Symbol getFieldOwner(int x, int y) {
        return gameGrid[x][y].getOwner();
    }
    //Set Field Symbol
    public void setFieldOwner(Symbol owner, int x, int y) {
        gameGrid[x][y].setOwner(owner);
    }
    
    //Get Field Access 
    public boolean getFieldAccessible(int x, int y) {
        return gameGrid[x][y].getAccessible();
    }
    //Set Field Access
    public void setFieldAccessible(boolean access, int x, int y) {
        gameGrid[x][y].setAccessible(access);
    }
    
}