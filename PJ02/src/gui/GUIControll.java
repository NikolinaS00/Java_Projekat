package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.logging.Logger;
import exceptions.IncorrectDatasException;
import simulation.Game;
import simulation.Main;

public class GUIControll {
	
	

	public static void enableFields(int n) {

		if (n == 2) {
			GamePlayersNamesWindow.BluePlayertextField.setEnabled(true);
			GamePlayersNamesWindow.GreenPlayertextField.setEnabled(true);
		} else if (n == 3) {
			GamePlayersNamesWindow.BluePlayertextField.setEnabled(true);
			GamePlayersNamesWindow.GreenPlayertextField.setEnabled(true);
			GamePlayersNamesWindow.RedPlayertextField.setEnabled(true);
		} else if (n == 4) {
			GamePlayersNamesWindow.BluePlayertextField.setEnabled(true);
			GamePlayersNamesWindow.GreenPlayertextField.setEnabled(true);
			GamePlayersNamesWindow.RedPlayertextField.setEnabled(true);
			GamePlayersNamesWindow.yellowPlayertextField.setEnabled(true);
		}
	}

	public static void checkNames(ArrayList<String> list) throws IncorrectDatasException {

		for (int i = 0; i < Game.numberOfPlayers; i++) {

			for (int j = 0; j < Game.numberOfPlayers; j++) {
				if(list.get(i).equals(list.get(j)) && i!=j) {
					throw new IncorrectDatasException("Imena igraca nisu jedinstvena");
				}
			}
		}
	}

	public static void getPlayersNames() {

		Logger.getLogger(GUIControll.class.getName()).addHandler(Main.handler);
		ArrayList<String> names = new ArrayList<String>();
		GamePlayersNamesWindow.nameOfBluePlayer = GamePlayersNamesWindow.BluePlayertextField.getText();
		names.add(GamePlayersNamesWindow.nameOfBluePlayer);
		GamePlayersNamesWindow.nameOfGreenPlayer = GamePlayersNamesWindow.GreenPlayertextField.getText();
		names.add(GamePlayersNamesWindow.nameOfGreenPlayer);
		GamePlayersNamesWindow.nameOfRedPlayer = GamePlayersNamesWindow.RedPlayertextField.getText();
		names.add(GamePlayersNamesWindow.nameOfRedPlayer);
		GamePlayersNamesWindow.nameOfYellowPlayer = GamePlayersNamesWindow.yellowPlayertextField.getText();
		names.add(GamePlayersNamesWindow.nameOfYellowPlayer);
		try {
			checkNames(names);
		} catch (IncorrectDatasException e) {
			Logger.getLogger(GUIControll.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
		}
	}

	public static void removeStartFigure(Integer position) {

		Integer positionInString = Integer.parseInt(Game.stringPath[position]);
		MapOfFields.map.get(positionInString).picLabel.setIcon(null);
		MapOfFields.map.get(positionInString).picLabel.repaint();
		MapOfFields.map.get(positionInString).picLabel.revalidate();
		MapOfFields.map.get(positionInString).add(MapOfFields.map.get(positionInString).picLabel);
		MapOfFields.map.get(positionInString).setBackground(Color.white);
		MapOfFields.map.get(positionInString).revalidate();
		MapOfFields.map.get(positionInString).repaint();
	}

	public static void drawStartingWindow() {

		Logger.getLogger(GUIControll.class.getName()).addHandler(Main.handler);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePlayersNamesWindow frame = new GamePlayersNamesWindow();
					frame.setVisible(true);
					GUIControll.enableFields(Game.numberOfPlayers);
				} catch (Exception e) {
					Logger.getLogger(GUIControll.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
				}
			}
		});
	}
}
