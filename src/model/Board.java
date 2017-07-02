package model;

import model.Field.Symbol;

public class Board {
    private Field[][] gameGrid;
    private static final int BOARDLEN = 7;
    
    public Board() {
        gameGrid = new Field[BOARDLEN][BOARDLEN];
        
        // initializes the board with Symbol.None
        for(int y=0; y<BOARDLEN ;y++){
			for(int x=0; x<BOARDLEN ; x++) {
                gameGrid[x][y] = Field.getDefault();
                gameGrid[x][y].setAccessible(false);
                if(x<2){
					if(y<2||y>4){
						gameGrid[x][y].setAccessible(false);
	                	gameGrid[x][y].setOwner(Symbol.O);
					} 	
				}
				if(x>4){
					if(y<2||y>4){
						gameGrid[x][y].setAccessible(false);
	                	gameGrid[x][y].setOwner(Symbol.O);
					} 	
				}	
                	
                }
            }
        }
    
    
    public void cruz(){
    	gameGrid[2][2].setOwner(Symbol.X);
    	gameGrid[3][1].setOwner(Symbol.X);
    	gameGrid[3][2].setOwner(Symbol.X);
    	gameGrid[3][3].setOwner(Symbol.X);
    	gameGrid[3][4].setOwner(Symbol.X);
    	gameGrid[4][2].setOwner(Symbol.X);
    	
    }
    
    public void cruzL(){
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
        for(int y=0; y<BOARDLEN ;y++){
			for(int x=0; x<BOARDLEN ; x++) {
        		if(gameGrid[x][y].getOwner()==Symbol.X)
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