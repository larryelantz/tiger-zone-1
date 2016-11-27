package tiger_zone.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;

import tiger_zone.Board;
import tiger_zone.Game;
import tiger_zone.Player;
import tiger_zone.Tile;
import tiger_zone.ai.AiPlayer;
import tiger_zone.ai.BetterAiPlayer;

public class Main {
	
	
	public static void main(String[] args) {
		
		////
		//Protocol p = new Protocol(null,0,null,null,null); 
//		Protocol p = new Protocol("0.0.0.0",8000,"tpass","user","pass");
//		if(args.length > 0){
//			p = new Protocol(args[0], Integer.parseInt(args[1]),args[2], args[3], args[4]);
//		}
		
//		p.tournamentProtocol(); 
//		String tileStrings = p.getTiles();
//		String[] parseTiles = tileStrings.split("\\s+"); 
//		
//		for(int j = 0; j < parseTiles.length; j++){
//			System.out.println(".." + parseTiles[j] + "..");
//		}
		
		
		Stack<Tile> pile = Board.createDefaultStack();
//		Map tileMap = Board.getMap(); 
//		Tile t = (Tile)tileMap.get("tltj-");
//		System.out.println(t.getImagePath());
//	    
		Collections.shuffle(pile);
		Game game = new Game(pile);

		// Protocol p = new Protocol("10.138.7.222",8000,"tpass","user","pass");
		// p.tournamentProtocol();

		List<Player> players = new ArrayList<Player>(2);
		players.add(new Player(0, "p1"));
		players.add(new Player(1, "p2"));
		game.setPlayers(players);

		BetterAiPlayer skynet1 = new BetterAiPlayer(game, "s1");
		BetterAiPlayer skynet2 = new BetterAiPlayer(game, "s2");
		
		List<AiPlayer> ai = new ArrayList<AiPlayer>();
		ai.add(skynet1);
		ai.add(skynet2);
		game.setAiPlayers(ai);
		game.conductTurn();

		BoardFrame bf = new BoardFrame(game);
		bf.setSize(900, 900);
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}
}
