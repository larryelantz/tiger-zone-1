package tiger_zone.ai;

import tiger_zone.Game;
import tiger_zone.PossibleMovesRule;
import tiger_zone.TigerTrailRule;

import tiger_zone.Tile;
import tiger_zone.UnionFind;

/**
 * A pretty bad AI player which simply makes the first possible move found. This exists simply to demonstrate how an AI
 * player should function.
 */
public class PoorAiPlayer extends AiPlayer {
	private int move_num = 1;
	private String pid;
	private String currentPlayer;

	/**
	 * Create a new instance of <code>PoorAiPlayer</code>.
	 *
	 * @param game The game instance.
	 */
	public PoorAiPlayer(Game game, String pid){
		super(game, pid);
	}

	/**
	 * Have this AI player place a tile on the board.
	 */
	public final void makeMove() {
		currentPlayer = getPid();

		long millis = System.currentTimeMillis();
		Tile current = this.game.getCurrentTile();
		PossibleMovesRule pmr = new PossibleMovesRule(this.game.getBoard(), 0, 0, current, false);

		// no possible move; simply pass our turn (not what's actually supposed to happen)
		if (!pmr.evaluate()) {
			return;
		}

		// x = move[0]
		// y = move[1]
		// rotation = move[2]
		int[] move = pmr.getFirstPossibleMove();
		while (current.getRotation() != move[2]) {
			current.rotate();
		}

		game.getBoard().addTile(move[0], move[1], current);

		if (current.hasDen()) {
			System.out.println("Player " + currentPlayer + " has placed a tiger!");
			current.addTiger(5, currentPlayer);
		}

		

		/*TigerLakeRule tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 2);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 3);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 4);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 5);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 6);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 7);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 8);
		tlr.evaluate();
		tlr = new TigerLakeRule(this.game.getBoard(), move[0], move[1], current, 9);
		tlr.evaluate();*/
		
		


		millis = System.currentTimeMillis() - millis;
		System.out.println("Move: " + move_num++ + " \tCoor: (" + move[0] +"," + move[1] +") \ttile: "
				+ current.getSide(0)+current.getSide(1)+current.getSide(2)+current.getSide(3)
				+ "\tTiger Locations: "  + (-1)
				+ "\tElapsed Time: " + millis);

		UnionFind uf = new UnionFind(current);
		TigerTrailRule tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 2);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 3);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 4);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 5);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 6);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 7);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 8);
		tlr.evaluate();
		tlr = new TigerTrailRule(this.game.getBoard(), move[0], move[1], current, 9);
		tlr.evaluate();


	}
}
