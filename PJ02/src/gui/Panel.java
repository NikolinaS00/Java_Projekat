package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {

	/**
	 * @author Nikolina
	 */
	private static final long serialVersionUID = 1L;
	public static final int unit_size = 40;
	public static int size_of_matrix;

	public Panel() {
		this.setLayout(null);
		this.setBounds(250, 180, 410, 410);
		this.setBackground(Color.WHITE);
		this.setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawMap(g);
	}

	public void drawMap(Graphics grafika) {
		grafika.setColor(Color.BLACK);
		for (int i = 0; i < size_of_matrix + 1; i++) {
			grafika.drawLine(i * unit_size, 0, i * unit_size, size_of_matrix * unit_size);
			grafika.drawLine(0, i * unit_size, size_of_matrix * unit_size, i * unit_size);

		}
	}

}
