package tiger_zone.ai;

import java.util.List;
import java.util.Map;

import tiger_zone.Game;
import tiger_zone.Player;
import tiger_zone.Position;
import tiger_zone.Tiger;
import tiger_zone.Tile;
import tiger_zone.action.Action;
import tiger_zone.action.PassAction;
import tiger_zone.action.TigerPlacementAction;
import tiger_zone.action.TilePlacementAction;

/**
 * A pretty bad AI player which simply makes the first possible move found. This exists simply to demonstrate how an AI
 * player should function.
 */
public class PoorAiPlayer extends AiPlayer {
	/**
	 * Create a new instance of <code>PoorAiPlayer</code>.
	 *
	 * @param game The game instance.
	 */
	public PoorAiPlayer(Game game) {
		super(game);
	}
	
	/**
	 * Have this AI player place a tile on the board. To maximize points, AI places its tigers on the first available
	 * den or unique animal tile.
	 */
	public final Action makeMove() {
		final Tile current = this.game.getCurrentTile();
		
		final Map<Position, List<Integer>> validTilePlacements = this.game.getBoard().getValidTilePlacements(current);

		// no possible move; simply pass our turn (not what's actually supposed to happen)
		if (validTilePlacements.isEmpty()) {
			return new PassAction();
		}
		
		// Get first valid tile placement
		Position p = validTilePlacements.keySet().iterator().next();

		
		final int rotation = validTilePlacements.get(p).get(0);

		// Rotate tile until we get desired rotation
		while (current.getRotation() != rotation) {
			current.rotate();
		}
		
		game.getBoard().addTile(p, current);
		
		final Player currentPlayer = this.getPlayer();
		
		int i = -1;
		
		// If this tile has a den, place a tiger on it if we have one available.
		if (current.hasDen() && currentPlayer.getTigers().size() > 0) {
			Tiger tiger = currentPlayer.getTigers().pop();
			current.addTiger(5, tiger);
			i = 5;
		}
		else if(current.hasAnimal()) {
			for (i = 1; i < 10; i++) {
				if (current.getZone(i) == 'l') {
					boolean isValid = this.game.getBoard().validTigerPlacement(p, i, false);
					if (isValid && currentPlayer.getTigers().size() > 0) {
						Tiger tiger = currentPlayer.getTigers().pop();
						current.addTiger(i, tiger);
						break;
					}
				}
			}
			i = (i == 10) ? -1 : i;
		}
		
		if (i >= 1 && i <= 9) {
			return new TigerPlacementAction(p, current, i);
		}
		return new TilePlacementAction(p, current);
	}
}
