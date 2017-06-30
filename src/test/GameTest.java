package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
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

