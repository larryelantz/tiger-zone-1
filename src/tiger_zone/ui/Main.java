package tiger_zone.ui;

import java.util.Collections;
import java.util.Stack;

import javax.swing.JFrame;

import tiger_zone.Board;
import tiger_zone.Tile;

public class Main {
	public static Board board;

	public static void main(String[] args) {
		Stack<Tile> pile = Board.createDefaultStack();
		Collections.shuffle(pile);
		board = new Board(pile);

		char[] Gsides = {'j','r','j','j','j','j','j','r','j','j','r','j'};

		board.addTile(4, 5,new Tile(Gsides, 'e', "./src/resources/tile7.png"));

		char[] Dsides = {'j','r','j','j','r','j','j','r','j','j','r','j'};
		board.addTile(4, 4, new Tile(Dsides, 'r', "./src/resources/tile4.png"));

		BoardFrame bf = new BoardFrame();
		bf.setSize(600, 600);
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}
}
