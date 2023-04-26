package figures;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import gui.MapOfFields;
import simulation.Game;
import simulation.Main;

public class GhostFigure extends Thread {

	File icon = new File("." + File.separator + "in" + File.separator + "Diamond.png");
	ArrayList<Integer> oldDiamonds = new ArrayList<>();
	JLabel picLabel = new JLabel();
	Boolean HasOldDiamonds = false;

	public GhostFigure() {
	}

	public void run() {
		Logger.getLogger(GhostFigure.class.getName()).addHandler(Main.handler);
		try {
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException ex) {

			Logger.getLogger(GhostFigure.class.getName()).log(java.util.logging.Level.SEVERE,
					ex.fillInStackTrace().toString());
		}
		Integer matrixSize = Game.sizeOfMatrix;
		while (Game.gameStarted) {

			if (this.HasOldDiamonds == true) {
				this.HasOldDiamonds = false;
				this.removeOldDiamonds();
			}
			Integer random_int = (int) Math.floor(Math.random() * (matrixSize - 2 + 1) + 2);
			for (int i = 0; i < random_int; i++) {

				Integer new_random = (int) Math.floor(Math.random() * ((Game.stringPath.length - 1) - 1 + 1) + 1);
				Integer position = Integer.parseInt(Game.stringPath[new_random]);
				if (MapOfFields.map.get(position).getIsTaken() == false) {
					BufferedImage myPicture;

					try {
						myPicture = ImageIO.read(icon);
						Icon pic = new ImageIcon(myPicture);
						picLabel.setIcon(pic);
						picLabel.setBounds(1, 1, 39, 39);
						picLabel.setVisible(true);
					} catch (IOException e) {
						Logger.getLogger(GhostFigure.class.getName()).log(java.util.logging.Level.SEVERE,
								e.fillInStackTrace().toString());
					}
					this.oldDiamonds.add(position);
					MapOfFields.map.get(position).add(picLabel);
					MapOfFields.map.get(position).repaint();
					MapOfFields.map.get(position).setHasDiamond(true);
				}
			}
			this.HasOldDiamonds = true;
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
				Logger.getLogger(GhostFigure.class.getName()).log(java.util.logging.Level.SEVERE,
						e.fillInStackTrace().toString());
			}

		}

	}

	public void removeOldDiamonds() {

		for (int i = 0; i < this.oldDiamonds.size(); i++) {
			Integer position = this.oldDiamonds.get(i);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					picLabel.setIcon(null);
					picLabel.repaint();
					picLabel.revalidate();
					MapOfFields.map.get(position).add(picLabel);
					MapOfFields.map.get(position).setBackground(Color.white);
					MapOfFields.map.get(position).revalidate();
					MapOfFields.map.get(position).repaint();
					MapOfFields.map.get(position).setHasDiamond(false);
				}
			});
			this.oldDiamonds.remove(i);
		}

	}

}
