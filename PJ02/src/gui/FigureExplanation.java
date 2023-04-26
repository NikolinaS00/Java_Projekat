package gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import simulation.Game;

public class FigureExplanation extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public HashMap<Integer, JPanel> fields = new HashMap<Integer, JPanel>();

	public FigureExplanation(String title) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle(title);
		this.setResizable(false);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBounds(6, 6, 300, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Panel matrix = new Panel();
		contentPane.add(matrix);
		contentPane.repaint();
		
		int size = Game.sizeOfMatrix;
		int brojac = 1;
		for (int j = 1; j <= Game.sizeOfMatrix; j++) {
			for (int i = brojac; i <= size; i++) {
				int k = i % Game.sizeOfMatrix;
				if (k == 0)
					k = Game.sizeOfMatrix;
				JPanel pane = new JPanel();
				pane.setBackground(Color.white);
				pane.setBounds((k - 1) * 40 + 1, (j - 1) * 40 + 1, 39, 39);
				matrix.add(pane);
				fields.put(i, pane);
				matrix.repaint();
			}
			brojac = brojac + Game.sizeOfMatrix;
			size = size + Game.sizeOfMatrix;
		}
		int n = Window.numberOfPassedFieldsBasedOnName.get(title);
			for (int i = 1; i <= n; i++) {
				fields.get(Integer.parseInt(Game.stringPath[i])).setBackground(Color.darkGray);
			}
		contentPane.repaint();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Window.numberOfOpening = 0;
				e.getWindow().dispose();
			}
		});

	}

}
