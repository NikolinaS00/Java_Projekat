package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Point;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import simulation.Game;
import simulation.Main;

public class Window extends JFrame implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	public static JLabel lblvrijemeTrajanjaSet;
	public static Panel matrica = new Panel();
	public static JTextPane txtpnTrenutniBrojOdigranih;
	JPanel panelPlayers;
	JButton btnPrikazRez;
	public static JButton btnPokreniZaustavi;
	public JPanel panelForCard;
	public static String[] arrayOfFigures = new String[Game.numberOfPlayers * 4];
	@SuppressWarnings("rawtypes")
	public JList listOfFigures;
	public JTextPane textPaneFigures;
	public static JTextPane textPaneOpisZnacenjaKarte;
	public static int numberOfOpening = 0;
	public static HashMap<String, Integer> numberOfPassedFieldsBasedOnName = new HashMap<String, Integer>();
	public int numberOfClicks = 0;
	public static String resultFolder = "out";

	public Window(int size) {

		Panel.size_of_matrix = size;
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		this.setTitle("Diamond Circle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 817);
		this.setResizable(false);

		JLabel lblNewLabel = new JLabel(
				"___________________________________________________________________________________________________________________________________________________________________________________________________________");

		txtpnTrenutniBrojOdigranih = new JTextPane();
		txtpnTrenutniBrojOdigranih.setBackground(Color.WHITE);
		txtpnTrenutniBrojOdigranih.setEditable(false);
		txtpnTrenutniBrojOdigranih.setForeground(SystemColor.textHighlight);
		txtpnTrenutniBrojOdigranih.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtpnTrenutniBrojOdigranih.setText("Trenutni broj odigranih igara: ");

		JTextPane txtpnDiamond = new JTextPane();
		txtpnDiamond.setEditable(false);
		txtpnDiamond.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(240, 230, 140), new Color(240, 230, 140),
				new Color(240, 230, 140), new Color(240, 230, 140)));
		txtpnDiamond.setBackground(new Color(255, 250, 205));
		txtpnDiamond.setForeground(new Color(220, 20, 60));
		txtpnDiamond.setBounds(new Rectangle(0, 5, 0, 0));
		txtpnDiamond.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtpnDiamond.setLocation(new Point(0, 3));
		txtpnDiamond.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		txtpnDiamond.setText("  DiamondCircle");

		btnPokreniZaustavi = new JButton("Pokreni/Zaustavi");
		btnPokreniZaustavi.setBackground(Color.WHITE);
		btnPokreniZaustavi.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		btnPokreniZaustavi.setForeground(SystemColor.textHighlight);
		btnPokreniZaustavi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnPokreniZaustavi.addActionListener(this);

		textPaneFigures = new JTextPane();
		textPaneFigures.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneFigures.setEditable(false);

		JPanel panelWithButton = new JPanel();
		panelWithButton.setBorder(new LineBorder(Color.BLACK));
		panelWithButton.setBackground(Color.WHITE);
		panelWithButton.setLayout(null);
		panelWithButton.repaint();

		panelForCard = new JPanel();
		panelForCard.setBorder(new LineBorder(Color.BLACK));
		panelForCard.setBackground(Color.WHITE);
		panelForCard.setLayout(null);

		JPanel panelTrajanjeIgre = new JPanel();
		panelTrajanjeIgre.setBackground(new Color(255, 250, 205));
		panelTrajanjeIgre.setBorder(new LineBorder(new Color(240, 230, 140)));
		panelTrajanjeIgre.setLayout(null);

		JPanel panelOpisZnacenjaKarte = new JPanel();
		panelOpisZnacenjaKarte.setLayout(null);
		panelOpisZnacenjaKarte.setBorder(new LineBorder(Color.BLACK));
		panelOpisZnacenjaKarte.setBackground(Color.WHITE);

		panelPlayers = new JPanel();
		panelPlayers.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPlayers.setBackground(Color.WHITE);
		panelPlayers.setLayout(null);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addComponent(txtpnTrenutniBrojOdigranih, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
				.addGap(192).addComponent(txtpnDiamond, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
				.addGap(181)
				.addComponent(btnPokreniZaustavi, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
				.addGap(183))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(24)
								.addComponent(textPaneFigures, GroupLayout.PREFERRED_SIZE, 187,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panelOpisZnacenjaKarte, GroupLayout.PREFERRED_SIZE, 497,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
														panelWithButton, GroupLayout.PREFERRED_SIZE, 257,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addGap(16)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(panelTrajanjeIgre, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(panelForCard, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panelPlayers,
								GroupLayout.PREFERRED_SIZE, 953, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(174, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnTrenutniBrojOdigranih, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPokreniZaustavi, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnDiamond, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
				.addGap(28).addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panelPlayers, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(14)
						.addComponent(panelForCard, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(panelTrajanjeIgre, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelWithButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(panelOpisZnacenjaKarte, GroupLayout.PREFERRED_SIZE, 181,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addGap(22).addComponent(textPaneFigures,
								GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)))
				.addGap(18)));

		JLabel lblOpisZnacenjaKarte = new JLabel("Opis znacenja karte:");
		lblOpisZnacenjaKarte.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblOpisZnacenjaKarte.setBounds(23, 10, 166, 21);
		panelOpisZnacenjaKarte.add(lblOpisZnacenjaKarte);

		textPaneOpisZnacenjaKarte = new JTextPane();
		textPaneOpisZnacenjaKarte.setEditable(false);
		textPaneOpisZnacenjaKarte.setBounds(72, 41, 292, 102);
		textPaneOpisZnacenjaKarte.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panelOpisZnacenjaKarte.add(textPaneOpisZnacenjaKarte);

		lblvrijemeTrajanjaSet = new JLabel(""); // dodati vrijeme trajanja
		lblvrijemeTrajanjaSet.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblvrijemeTrajanjaSet.setBounds(163, 17, 62, 13);
		panelTrajanjeIgre.add(lblvrijemeTrajanjaSet);

		JLabel lblNewLabel_1 = new JLabel("Vrijeme trajanja igre:");
		lblNewLabel_1.setBounds(0, 10, 160, 27);
		panelTrajanjeIgre.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		JTextPane txtpnTrenutnaKarta = new JTextPane();
		txtpnTrenutnaKarta.setEditable(false);
		txtpnTrenutnaKarta.setBorder(new LineBorder(new Color(30, 144, 255)));
		txtpnTrenutnaKarta.setBackground(new Color(135, 206, 250));
		txtpnTrenutnaKarta.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtpnTrenutnaKarta.setForeground(new Color(0, 0, 0));
		txtpnTrenutnaKarta.setText("Trenutna karta");
		txtpnTrenutnaKarta.setBounds(43, 10, 118, 26);
		panelForCard.add(txtpnTrenutnaKarta);

		btnPrikazRez = new JButton("Prikaz liste fajlova sa rezultatima");
		btnPrikazRez.setBounds(10, 51, 241, 51);
		panelWithButton.add(btnPrikazRez);
		btnPrikazRez.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		btnPrikazRez.setForeground(SystemColor.textHighlight);
		btnPrikazRez.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnPrikazRez.setBackground(Color.WHITE);
		btnPrikazRez.addActionListener(this);
		getContentPane().setLayout(groupLayout);

		getContentPane().add(matrica);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addFigures() {

		listOfFigures = new JList(arrayOfFigures);
		listOfFigures.setBounds(4, 4, 100, 500);
		listOfFigures.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		listOfFigures.addListSelectionListener(this);
		textPaneFigures.add(listOfFigures);
		textPaneFigures.repaint();
	}

	public void addGamer(String s, int n, Color c) {

		JLabel lbl = new JLabel();
		lbl.setBounds(n * 180, 10, 80, 20);
		lbl.setText(s);
		lbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panelPlayers.add(lbl);
		panelPlayers.repaint();
		lbl.setForeground(c);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Logger.getLogger(Window.class.getName()).addHandler(Main.handler);
		if (e.getSource() == btnPrikazRez) {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + resultFolder);
			} catch (IOException ex) {
				Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE,
						ex.fillInStackTrace().toString());
			}
		} else if (e.getSource() == btnPokreniZaustavi) {
			if (numberOfClicks == 0) {
				Main.game.start();
			} else if (numberOfClicks % 2 == 1) {
				Main.signForWait = true;
			} else if (numberOfClicks % 2 == 0) {
				Main.signForWait = false;
				synchronized (Main.game) {
					Main.game.notify();
					synchronized (Main.tim) {
						Main.tim.notify();
					}
				}
			}
			numberOfClicks += 1;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Logger.getLogger(Window.class.getName()).addHandler(Main.handler);
		if (e.getSource() == listOfFigures) {
			if (numberOfOpening < 1)
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FigureExplanation frame = new FigureExplanation((String) listOfFigures.getSelectedValue());
							frame.setVisible(true);
							frame.repaint();
							frame.revalidate();
							listOfFigures.clearSelection();
							numberOfOpening++;
						} catch (Exception e) {
							listOfFigures.clearSelection();
							if (numberOfOpening < 1)
								JOptionPane.showMessageDialog(new JFrame(), "Figura nije zapocela kretanje!", "Dialog",
										JOptionPane.ERROR_MESSAGE);
							Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE,
									e.fillInStackTrace().toString());
						}
					}
				});
		}
	}
}
