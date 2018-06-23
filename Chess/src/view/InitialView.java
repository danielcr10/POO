package view;

import java.io.IOException;
import java.io.File;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JButton;

public class InitialView extends JPanel {

	private static final String imagesPath = "images";
	private static Image backgroundImage;

	static {
		try {
			backgroundImage = ImageIO.read(new File(imagesPath + File.separator + "backgroundChess.jpeg"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public InitialView(ActionListener listener) {
		setSize(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
		final JButton newGameButton = new JButton("New Game");
		final JButton loadGameButton = new JButton("Load Game");
		layoutButtons(newGameButton, loadGameButton);
		newGameButton.addActionListener(listener);
		// Enquanto o Daneil acaba de fazer a leitura do jogo salvo.
		loadGameButton.setEnabled(false);
		add(newGameButton);
		add(loadGameButton);
		setLayout(null);
		setVisible(true);
	}

	private void layoutButtons(JButton b1, JButton b2) {
		final int padding = 10;
		final int panelWidth = getSize().width;
		final int panelHeight = getSize().height;
		final Dimension b1Size = b1.getPreferredSize();
		final Dimension b2Size = b2.getPreferredSize();
		final int b1Width = b1Size.width;
		final int b1Height = b1Size.height;
		final int b2Width = b2Size.width;
		final int b2Height = b2Size.height;

		b1.setBounds(panelWidth / 2 + padding / 2, panelHeight / 2 - b2Height / 2, b2Width, b2Height);
		b2.setBounds(panelWidth / 2 - b1Width - padding / 2, panelHeight / 2 - b1Height / 2, b1Width, b1Height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImage, 0, 0, null);
	}

}
