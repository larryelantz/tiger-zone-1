package tiger_zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest {
	private static Stack<Tile> pile;
	private static Board board;

	@BeforeClass
	public static void setup() {
		pile = Board.createDefaultStack();
		board = new Board(pile);
	}

	@Test
	public void initialTileTest() {
		assertTrue(board.getTile(new Position(0, 0)) == null);
	}
	
	@Test
	public void addGetTileTest() {
		char[] sides = {'t','j','t','j'};
		Tile t = new Tile(sides, 't', "./src/resources/tile5.png");
		board.addTileWithNoValidation(new Position(0, -1), t);
		assertSame(t, board.getTile(new Position(0, -1)));
		assertFalse(board.addTile(new Position(0, -1), t));
	}

	@Test
	public void getPileTest() {
		assertSame(pile, board.getPile());
	}

	@Test
	public void createDefaultStackTest() {
		assertEquals(76, Board.createDefaultStack().size());
	}
}
