package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Board;
import model.Game;
import model.Field.Symbol;


public class GameTest {

	@Test
	public void newGame(){
		Game game = new Game();
		int count=0;
		for(int i=0; i<49; i++)
			if(!game.getUserSymbol().equals(Symbol.NONE))
				count++;
		assertEquals(0, count);
	}
	@Test
	public void testSave(){
		Game game = new Game();
		Board board1 = new Board();
		Board board2 = new Board();
		Board board3 = new Board();
		
		board1.setFieldOwner(Symbol.X, 0, 0);
		game.saveBoard(board1);
		board2.setFieldOwner(Symbol.X, 1, 0);
		game.saveBoard(board2);
		board3.setFieldOwner(Symbol.X, 1, 1);
		game.saveBoard(board3);
		
		assertEquals(board2, game.boards.get(1));
	}
	
	@Test 
	public void didWin(){
		Game game = new Game();
		assertEquals(false, game.getDidSomeoneWin());
	}
	
	@Test
	public void setGetSymbol(){
		Game game = new Game();
		game.setFieldOwner(Symbol.X , 0, 0);
		assertEquals(Symbol.X, game.getFieldOwner(0, 0));
	}
	@Test
	public void setGetAccess(){
		Game game = new Game();
		game.setFieldAccessible(true, 0, 0);
		
		assertEquals(true, game.getFieldAccessible(0, 0));
	}
}

