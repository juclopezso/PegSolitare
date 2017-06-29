package model;

import java.util.Iterator;

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
                if((i<2 && j<2) || ((i>4 && i<7) && j<2) || ((j>4 && j<7) && i<2) || (i>4 && j>4))
                	gameGrid[i][j].setAcessible(false);
            }
        }
    }
    
    public int evaluateBoard() {
        int score = 0;
        for (int i = 0; i < BOARD_SIDE_LENGTH; i++){
        	for (int j = 0; j < BOARD_SIDE_LENGTH; j++){
        		if(gameGrid[i][j].getAccesible()==true && gameGrid[i][j].getOwner()==Symbol.X)
        			score++;
        	}
        }
        return score;
    }
    
    /**
     * Sets the symbol of a specific field.
     * 
     * @param x     the value for the x-coordinate of the Field
     * @param y     the value for the y-coordinate of the Field
     */
    public void setFieldOwner(Symbol owner, int x, int y) {
        gameGrid[x][y].setOwner(owner);
    }

    /**
     * Returns the owner of a specific field.
     * 
     * @param x     the value for the x-coordinate of the Field
     * @param y     the value for the y-coordinate of the Field
     */
    public Symbol getFieldOwner(int x, int y) {
        return gameGrid[x][y].getOwner();
    }
    
    /**
     * Prints the field.
     * Note: I'm using this just for testing.
     */
    public void printField() {
        for (int i = 0; i < BOARD_SIDE_LENGTH; i++) {
            for (int j = 0; j < BOARD_SIDE_LENGTH; j++) {
                System.out.print(gameGrid[i][j].getOwner() + " ");
            }
            System.out.println();
        }
    }
}