package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.Controller;
import model.Game;
import model.Field.Symbol;

public class ControllerTest {
	
	Controller cont = new Controller();
	Game game = new Game();
	
	@Test
	public void canMoveRigth(){
		
		game.setFieldOwner(Symbol.X, 0, 0);
		game.setFieldOwner(Symbol.X, 1, 0);
		game.setFieldOwner(Symbol.O, 2, 0);
		
		assertEquals(true, cont.canMoveRigth(game, 0, 0));
	}
	@Test
	public void canMoveLeft(){
		
		game.setFieldOwner(Symbol.X, 2, 0);
		game.setFieldOwner(Symbol.X, 1, 0);
		game.setFieldOwner(Symbol.O, 0, 0);
		
		assertEquals(true, cont.canMoveLeft(game, 2, 0));
	}
	@Test
	public void canMoveUp(){
		
		game.setFieldOwner(Symbol.X, 0, 2);
		game.setFieldOwner(Symbol.X, 0, 1);
		game.setFieldOwner(Symbol.O, 0, 0);
		
		assertEquals(true, cont.canMoveUp(game, 0, 2));
		
	}
	@Test
	public void canMoveDown(){
		
		game.setFieldOwner(Symbol.X, 0, 0);
		game.setFieldOwner(Symbol.X, 0, 1);
		game.setFieldOwner(Symbol.O, 0, 2);
		
		assertEquals(true, cont.canMoveDown(game, 0, 0));
	}
	
	@Test
	public void cantMoveRigth(){
		
		game.setFieldOwner(Symbol.X, 0, 0);
		game.setFieldOwner(Symbol.X, 1, 0);
		game.setFieldOwner(Symbol.O, 5, 0);
		
		assertEquals(false, cont.canMoveRigth(game, 5, 0));
	}
	@Test
	public void cantMoveLeft(){
		
		game.setFieldOwner(Symbol.X, 2, 0);
		game.setFieldOwner(Symbol.X, 1, 0);
		game.setFieldOwner(Symbol.O, 5, 0);
		
		assertEquals(false, cont.canMoveLeft(game, 1, 0));
	}
	@Test
	public void cantMoveUp(){
		
		game.setFieldOwner(Symbol.X, 0, 2);
		game.setFieldOwner(Symbol.X, 4, 1);
		game.setFieldOwner(Symbol.O, 0, 0);
		
		assertEquals(false, cont.canMoveUp(game, 0, 2));
		
	}
	@Test
	public void cantMoveDown(){
		
		game.setFieldOwner(Symbol.X, 3, 0);
		game.setFieldOwner(Symbol.X, 0, 1);
		game.setFieldOwner(Symbol.O, 5, 2);
		
		assertEquals(false, cont.canMoveDown(game, 0, 1));
	}
	
	@Test
	public void setClicked(){
		cont.setFieldClicked(game, 0, 0, 1, 1);
		
		assertEquals(Symbol.O, game.getFieldOwner(0, 0));
	}
	
	@Test
	public void gameOver(){
		assertEquals(false, cont.isGameOver());
	}
	
	@Test
	public void availableMoves(){
		
		game.setFieldOwner(Symbol.X, 0, 0);
		game.setFieldOwner(Symbol.X, 1, 0);
		game.setFieldOwner(Symbol.O, 2, 0);
		
		cont.setAvailableMoves(game, 0, 0);
		
		assertEquals(true, game.getFieldAccessible(2, 0));
	}
	@Test
	public void fieldSymbolO(){
		
		game.setFieldOwner(Symbol.X, 0, 0);
		game.setFieldOwner(Symbol.X, 1, 0);
		game.setFieldOwner(Symbol.O, 2, 0);
		
		cont.setFieldSymbolO(game, 0,0,1,1);
		
		assertEquals(Symbol.O, game.getFieldOwner(1, 0));
	}
	@Test
	public void fieldFalse(){
		cont.setFieldFalse(game, 0, 0);
		assertEquals(false, game.getFieldAccessible(2, 0));
	}
}
