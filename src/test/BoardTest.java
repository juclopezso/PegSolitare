package test;
import static org.junit.Assert.*;

import org.junit.Test;

import model.Board;
import model.Field.Symbol;

public class BoardTest {

	@Test
	public void newBoard(){
		Board board = new Board();
		int score = board.evaluateBoard();
		
			assertEquals(0, score);
	}
	
	@Test
	public void winningBoard(){
		Board board = new Board();
		
		board.setFieldOwner(Symbol.X, 0, 0);
		board.setFieldOwner(Symbol.O, 2, 2);
		board.setFieldOwner(Symbol.O, 0, 2);
		board.setFieldOwner(Symbol.O, 0, 1);
		board.setFieldOwner(Symbol.O, 2, 0);
		board.setFieldOwner(Symbol.O, 1, 1);
		board.setFieldOwner(Symbol.O, 1, 0);
		
		int score = board.evaluateBoard();
		assertEquals(1, score);
	}
	
	@Test
	public void notFinishedBoard(){
		Board board = new Board();
		
		board.setFieldOwner(Symbol.X, 0, 0);
		board.setFieldOwner(Symbol.O, 1, 0);
		board.setFieldOwner(Symbol.X, 2, 0);
		board.setFieldOwner(Symbol.O, 0, 1);
		board.setFieldOwner(Symbol.X, 2, 1);
		board.setFieldOwner(Symbol.O, 1, 1);
		board.setFieldOwner(Symbol.X, 0, 2);
		board.setFieldOwner(Symbol.X, 1, 2);
		board.setFieldOwner(Symbol.O, 2, 2);
		
		int score = board.evaluateBoard();	
		
		assertEquals(5, score);
	}
	
}
