package simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import exceptions.IncorrectDatasException;
import figures.Figure;
import gui.Window;
import player.Player;

public class GameControll {

	public static void getInputDatas(String input) {
		Logger.getLogger(GameControll.class.getName()).addHandler(Main.handler);
		try {
			File file = new File(input);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String in;
			while ((in = br.readLine()) != null) {
				String[] str = in.split("[:]");
				if ("Size".equals(str[0])) {
					Game.sizeOfMatrix = Integer.parseInt(str[1]);
					if (Game.sizeOfMatrix < 7 || Game.sizeOfMatrix > 10) {
						throw new IncorrectDatasException("Neispravna dimenzija matrice!");
					}
				} else if ("NumOfPlayers".equals(str[0])) {
					Game.numberOfPlayers = Integer.parseInt(str[1]);
					if (Game.numberOfPlayers > 4 || Game.numberOfPlayers < 2) {
						throw new IncorrectDatasException("Neispravan broj igraca");
					}
				} else if ("NumOfPlayedGames".equals(str[0])) {
					Game.numberOfPlayedGames = Integer.parseInt(str[1]);
				}
			}
			br.close();
		} catch (Exception ex) {
			Logger.getLogger(GameControll.class.getName()).log(java.util.logging.Level.SEVERE,
					ex.fillInStackTrace().toString());
		}

	}

	public static int getNofGames(String file) {

		Logger.getLogger(GameControll.class.getName()).addHandler(Main.handler);
		BufferedReader reader;
		String line = new String();
		try {
			reader = new BufferedReader(new FileReader(file));
			try {
				line = reader.readLine();
				reader.close();
			} catch (IOException e) {
				Logger.getLogger(GameControll.class.getName()).log(java.util.logging.Level.SEVERE,
						e.fillInStackTrace().toString());
			}
		} catch (FileNotFoundException e1) {
			Logger.getLogger(GameControll.class.getName()).log(java.util.logging.Level.SEVERE,
					e1.fillInStackTrace().toString());
		}

		return Integer.parseInt(line);

	}

	public static void setNofGames(String file) {
		Logger.getLogger(GameControll.class.getName()).addHandler(Main.handler);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file, false));
			pw.println(Game.numberOfPlayedGames.toString());
			pw.flush();
		} catch (IOException e) {
			Logger.getLogger(GameControll.class.getName()).log(java.util.logging.Level.SEVERE,
					e.fillInStackTrace().toString());
		}

	}

	public static void setFigureName() {

		int i = 1;
		for (Player p : Game.listOfPlayers) {
			for (Figure f : p.figures) {
				f.setName("Figure" + i);
				Window.arrayOfFigures[i - 1] = "Figure" + i;
				i++;
			}
		}

	}

	public static Figure getFigure(String Name) {
		int k = 0;
		for (Player p : Game.listOfPlayers) {
			if (p.getFigureByName(Name) != null) {
				int m = p.getFigureByName(Name);
				return Game.listOfPlayers.get(k).figures.get(m);
			}
			k++;
		}
		return null;
	}

	public static void writeToFile(Player pl, Figure fig) {
		Logger.getLogger(GameControll.class.getName()).addHandler(Main.handler);
		try {
			String str = Game.numberOfPlayedGames + "Igra.txt";
			PrintWriter pw = new PrintWriter(new FileWriter("." + File.separator + Window.resultFolder + File.separator + str, true));
			pw.println("Igrac: " + pl.toString() + " - " + fig);
			pw.flush();
		} catch (IOException e) {
			Logger.getLogger(GameControll.class.getName()).log(java.util.logging.Level.SEVERE,
					e.fillInStackTrace().toString());
		}
	}

	public static void removeFigureFromArray(String Name) {

		int k = 0;
		for (Player p : Game.listOfPlayers) {
			if (p.getFigureByName(Name) != null) {
				int m = p.getFigureByName(Name);
				writeToFile(Game.listOfPlayers.get(k), Game.listOfPlayers.get(k).figures.get(m));
				Game.listOfPlayers.get(k).figures.remove(m);

			}
			k++;
		}
	}

	public static void getPath(File inputPath) {
		Logger.getLogger(GameControll.class.getName()).addHandler(Main.handler);
		try {
			FileReader fr1 = new FileReader(inputPath);
			BufferedReader br1 = new BufferedReader(fr1);
			String in1;
			while ((in1 = br1.readLine()) != null) {
				if (in1.startsWith(((Integer) Game.sizeOfMatrix).toString())) {
					Game.stringPath = in1.split("[:,]");
					int i = 0;
					while (Game.stringPath[i] != null) {
						i++;
					}
				}
			}
			br1.close();
		} catch (Exception ex) {
			Logger.getLogger(GameControll.class.getName()).log(java.util.logging.Level.SEVERE,
					ex.fillInStackTrace().toString());
		}
		Game.finalField = Integer.parseInt(Game.stringPath[Game.stringPath.length - 1]);
	}

	public static Boolean playersHaveFigure(Integer numberOfFigure) {

		Boolean[] niz = { false, false, false, false };
		int i = 0;
		for (Player p : Game.listOfPlayers) {
			if (!p.figures.isEmpty())
				if (null != p.figures.get(numberOfFigure))
					niz[i] = true;
			i++;
		}
		if (niz[0] == false && niz[1] == false && niz[2] == false && niz[3] == false)
			return false;
		else
			return true;

	}

}
