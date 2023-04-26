package simulation;

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
import cards.Card;
import cards.RegularCard;
import cards.SpecialCard;
import figures.Figure;
import figures.FloatingFigure;
import figures.SuperFastFigure;
import gui.GUIControll;
import gui.GamePlayersNamesWindow;
import gui.MapOfFields;
import gui.Window;
import player.Player;
import time.DescriptionOfMove;
import time.Timer;

public class Game extends Thread {

	File path = new File("." + File.separator + "in" + File.separator + "path.txt");
	String numberOfGames = "." + File.separator + "in" + File.separator + "numberOfPlayedGames.txt";
	static Boolean windowCreated = false;
	public static int sizeOfMatrix;
	public static int numberOfPlayers;
	public static Integer numberOfPlayedGames;
	public static String[] stringPath;
	public static String[] playersNames;
	public static ArrayList<Player> listOfPlayers = new ArrayList<Player>();
	public static MapOfFields mappp;
	public static Boolean gameStarted = false;
	public static Boolean gamePaused = false;
	public static Integer finalField;
	public static ArrayList<Player> listOfPlayersCopy = new ArrayList<Player>();
	public static Window frame;
	

	public Game() {
	}

	public static void generatePlayers() {

		GUIControll.getPlayersNames();
		if (Game.numberOfPlayers == 2) {
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfBluePlayer, Color.blue));
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfGreenPlayer, Color.green));
		} else if (Game.numberOfPlayers == 3) {
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfBluePlayer, Color.blue));
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfGreenPlayer, Color.green));
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfRedPlayer, Color.red));
		} else if (Game.numberOfPlayers == 4) {
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfBluePlayer, Color.blue));
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfGreenPlayer, Color.green));
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfRedPlayer, Color.red));
			Game.listOfPlayers.add(new Player(GamePlayersNamesWindow.nameOfYellowPlayer, Color.yellow));
		}
	}

	public static void drawGui() {
		Logger.getLogger(Game.class.getName()).addHandler(Main.handler);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Window(sizeOfMatrix);
					frame.setVisible(true);
					addComponents();
				} catch (Exception e) {
					Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
				}
			}
		});
	}

	public static void addComponents() {
		Logger.getLogger(Game.class.getName()).addHandler(Main.handler);
		GameControll.setFigureName();
		mappp = new MapOfFields(sizeOfMatrix);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Logger.getLogger(FloatingFigure.class.getName()).addHandler(Main.handler);
		}
		frame.addFigures();
		for (int k = 0; k < numberOfPlayers; k++) {
			frame.addGamer(listOfPlayers.get(k).name, k + 1, listOfPlayers.get(k).colorOfFigures);
		}
		if ((sizeOfMatrix < 7 || sizeOfMatrix > 10) || (numberOfPlayers > 4 || numberOfPlayers < 2)) {
			Window.btnPokreniZaustavi.setEnabled(false);
		}
	}

	public Card printCard() {
		Logger.getLogger(Game.class.getName()).addHandler(Main.handler);
		int random_int = (int) Math.floor(Math.random() * (52 - 1 + 1) + 1); // prepraviti na 52, sa rupama karte
		int k = random_int;
		JLabel picLabel = new JLabel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BufferedImage myPicture;
				try {
					myPicture = ImageIO.read(new File("." + File.separator + "in" + File.separator + "cards"
							+ File.separator + "slika" + k + ".jpg"));
					Icon pic = new ImageIcon(myPicture);
					picLabel.setIcon(pic);
					picLabel.setBounds(20, 30, 250, 300);
					picLabel.setVisible(true);
				} catch (IOException e) {
					Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
				}
				frame.panelForCard.add(picLabel);
				frame.panelForCard.repaint();
			}
		});
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
		}
		picLabel.setIcon(null);
		picLabel.revalidate();
		picLabel.repaint();
		frame.panelForCard.add(picLabel);
		frame.panelForCard.revalidate();
		frame.panelForCard.repaint();
		if (random_int <= 40) {
			if (random_int % 4 == 0)
				return new RegularCard(4);
			else {
				Integer m = random_int % 4;
				return new RegularCard(m);
			}
		} else {
			return new SpecialCard();
		}
	}

	public void moveFigure(Figure figure, Integer startPosition, Integer step, Integer movement) {

		int diamond = figure.getNumberOfColectedDiamonds();
		if (startPosition + step + movement + diamond >= stringPath.length - 1) {
			this.moveFigureToFinalField(figure, startPosition);
		} else {
			Integer pos = startPosition + step + movement + diamond;
			if (MapOfFields.map.get(pos).getHasDiamond()) {
				figure.setNumberOfColectedDiamonds();
			}
			figure.colorField(pos);
			figure.removeFigure(startPosition);
			figure.setPassedFields(Integer.parseInt(Game.stringPath[startPosition ]));
			figure.setNumberOfPassedFields(pos);
			MapOfFields.map.get(Integer.parseInt(Game.stringPath[startPosition + step + movement + diamond]))
					.setFigureOnField(figure);
		}
	}

	public void moveFigureToFinalField(Figure figure, Integer start) {
		Logger.getLogger(Game.class.getName()).addHandler(Main.handler);
		figure.colorField(stringPath.length - 1);
		figure.removeFigure(start);
		figure.setGotToFinalField(true);
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
		}
		figure.removeFigure(stringPath.length - 1);
		figure.setNumberOfPassedFields(Game.stringPath.length - 1);
		figure.setPassedFields(Integer.parseInt(Game.stringPath[Game.stringPath.length - 1]));
		GameControll.removeFigureFromArray(figure.getName());
		MapOfFields.map.get(start).setIsTaken(false);
	}

	@Override
	public void run() {
		Logger.getLogger(Game.class.getName()).addHandler(Main.handler);
		Timer.startTime = System.currentTimeMillis();
		gameStarted = true;
		Main.tim.start();
		GameControll.getPath(path);
		numberOfPlayedGames = GameControll.getNofGames(numberOfGames);
		Window.txtpnTrenutniBrojOdigranih.setText("Trenutni broj odigranih igara: " + numberOfPlayedGames);
		Window.txtpnTrenutniBrojOdigranih.repaint();
		Main.gf.start();
		numberOfPlayedGames +=1;
		GameControll.setNofGames(numberOfGames);
		int counter = 0;
		while (!listOfPlayers.isEmpty()) {
			for (int j = 0; j < 4; j++) {
				while (GameControll.playersHaveFigure(j)) {
					for (int i = 0; i < listOfPlayers.size(); i++) {
						if (Main.signForWait == false) {
							if (!listOfPlayers.get(i).figures.isEmpty()) {
								Figure tempFigure = listOfPlayers.get(i).figures.get(j);
								Integer numOfPassedFields = tempFigure.getNumberOfPassedFields();
								if (numOfPassedFields == 1 && MapOfFields.map.get(numOfPassedFields).getIsTaken()) {
									GUIControll.removeStartFigure(numOfPassedFields);
								}
								tempFigure.colorField(numOfPassedFields);
								Card tmp = this.printCard();
								DescriptionOfMove.describeMove(tmp);
								if (tmp instanceof RegularCard) {
									Integer tmppp = ((RegularCard) tmp).NumberOfFields;

									if (tempFigure instanceof SuperFastFigure) {
										tmppp = tmppp * 2;
									}
									Integer diammond = tempFigure.getNumberOfColectedDiamonds();
									int movement = 1;
									if (MapOfFields.map.get(numOfPassedFields + tmppp + diammond).getIsTaken()) {
										while (MapOfFields.map.get(numOfPassedFields + tmppp + movement + diammond)
												.getIsTaken()) {
											movement++;
										}
										this.moveFigure(tempFigure, numOfPassedFields, tmppp, movement);
									} else {
										this.moveFigure(tempFigure, numOfPassedFields, tmppp, 0);
									}
									try { 
										sleep(1000);
										counter++;
										synchronized (Main.gf) {
											if (counter % 5 == 0) {
												Main.gf.notify();
												counter = 0;
											}
										}
									} catch (InterruptedException e) {
										Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
									}
								} else if (tmp instanceof SpecialCard) {
									mappp.createHoles((SpecialCard) tmp);
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
									}
									mappp.removeAllHoles();
								}
							} else {
								listOfPlayers.remove(i);
							}
						} else {
							try {
								synchronized (this) {
									wait();
								}
							} catch (InterruptedException e) { 
								Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
							}
						}
					}
				}
			}
		}
	}
}
