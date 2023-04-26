package player;

import java.awt.Color;
import java.util.ArrayList;
import figures.Figure;
import figures.FloatingFigure;
import figures.RegularFigure;
import figures.SuperFastFigure;

public class Player {

	public String name;
	public Integer number;
	public Color colorOfFigures;
	public ArrayList<Figure> figures = new ArrayList<Figure>();

	public Player(String n, Color c) {

		name = n;
		colorOfFigures = c;
		for (int k = 0; k < 4; k++) {
			int random_int = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
			if (random_int == 1) {
				figures.add(new RegularFigure(c));
			} else if (random_int == 2) {
				figures.add(new FloatingFigure(c));
			} else if (random_int == 3) {
				figures.add(new SuperFastFigure(c));
			}
		}
	}
	
	
	public Integer getFigureByName(String name) {
		
		int i = 0;
		for(Figure f : this.figures) {
			if(f.getName().equals(name))
				return i;
		}
		 return null;
	}
	
	@Override 
	public String toString() {
			return  this.name ;
	}

}
