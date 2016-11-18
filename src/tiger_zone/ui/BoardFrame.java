package tiger_zone.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import tiger_zone.Tile;

/**
 * @BoardFrame The GUI representation of the Board class.
 * Generates a 2d array of JPanels which can be clicked and their icon
 * set to that of the next tile
 *
 * @Params
 * Mouseadapter ma - Listens for the click of a JPanel
 * panelBoard - 2d array of JPanels
 * preview - JFrame window holding image of next tile on stack
 * previewLabel - Will hold the actual icon of next tile
 */
class BoardFrame extends JFrame {
	private static final long serialVersionUID = 575149023846295616L;
	public static JFrame preview = new JFrame();
	public static JLabel previewLabel = new JLabel();

	public BoardFrame() {
		final int rows = 10;
		final int cols = 10;

		MouseAdapter ma = new OnBoardClick();

		this.setLayout(new GridLayout(rows, cols));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// By default, all panels are "blank" with a transparent image
				TilePanel panel = new TilePanel(new ImageIcon(new BufferedImage(64, 64,
						BufferedImage.TYPE_INT_ARGB)), 0);

				Tile current = Main.board.getTile(i, j);

				// A tile exists at the position, so get its image and rotation and apply them to this panel
				if (current != null) {
					panel.setImg(new ImageIcon(current.getImagePath()));
					panel.setRotation(current.getRotation());
				}

				panel.addMouseListener(ma);
				panel.setBorder(new LineBorder(Color.BLACK));
				this.add(panel);
			}
		}

		Tile nextTile = Main.board.getPile().peek();
		ImageIcon previewImg = new ImageIcon(nextTile.getImagePath());
		previewLabel.setIcon(previewImg);
		preview.add(previewLabel);
		preview.setSize(200, 200);
		preview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		preview.setVisible(true);
	}
}
