package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import cards.SpecialCard;
import figures.Figure;
import figures.FloatingFigure;
import simulation.Game;
import simulation.GameControll;

public class MapOfFields {

	public static HashMap<Integer, Field> map = new HashMap<Integer, Field>();
	JLabel picLabel;
	Boolean createdHoles = false;
	ArrayList<Integer> fieldsWithHoles = new ArrayList<Integer>();
	Integer numOfHoles;

	public MapOfFields(int sizeOfMap) {

		int size = sizeOfMap;
		int brojac = 1;
		for (int j = 1; j <= sizeOfMap; j++) {
			for (int i = brojac; i <= size; i++) {
				int k = i % sizeOfMap;
				if (k == 0)
					k = sizeOfMap;
				map.put(i, new Field((k - 1) * 40 + 1, (j - 1) * 40 + 1, 39, 39, Color.WHITE, i));
			}
			brojac = brojac + sizeOfMap;
			size = size + sizeOfMap;
		}
	}

	public Integer getNumOfHoles() {
		return numOfHoles;
	}

	public void setNumOfHoles(Integer numOfHoles) {
		this.numOfHoles = numOfHoles;
	}

	public Boolean getCreatedHoles() {
		return createdHoles;
	}

	public void setCreatedHoles(Boolean createdHoles) {
		this.createdHoles = createdHoles;
	}

	public void removeHole(Integer position) {

		map.get(position).setHasHole(false);
		map.get(position).setIsTaken(false);
		map.get(position).setBackground(Color.white);
		map.get(position).repaint();

	}

	public void createHoles(SpecialCard sc) {

		Integer[] arrayOfHoles = new Integer[sc.getNumberOfHoles()];
		this.setNumOfHoles(sc.getNumberOfHoles());

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i < sc.getNumberOfHoles(); i++) {
					Integer random_int = (int) Math.floor(Math.random() * ((Game.stringPath.length - 1) - 1 + 1) + 1);
					Integer position = Integer.parseInt(Game.stringPath[random_int]);
					if (!(map.get(position).getFigureOnField() instanceof FloatingFigure)) {
						arrayOfHoles[i] = position;
						fieldsWithHoles.add(position);
						map.get(position).setHasHole(true);
						map.get(position).setBackground(Color.black);
						map.get(position).repaint();
					}
					if (map.get(position).getFigureOnField() != null
							&& !(map.get(position).getFigureOnField() instanceof FloatingFigure)) {
						Figure fig = map.get(position).getFigureOnField();
						removeHole(position);
						GameControll.removeFigureFromArray(fig.getName());
						fig.removeFigure(random_int);
					}
				}
			}
		});
		this.setCreatedHoles(true);
	}

	public void removeAllHoles() {

		for (Integer i : fieldsWithHoles) {
			if (map.get(i).getFigureOnField() == null && map.get(i).getHasHole())
				this.removeHole(i);
		}
		this.setCreatedHoles(false);
	}

}
