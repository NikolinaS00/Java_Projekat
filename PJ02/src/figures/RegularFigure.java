package figures;

import java.awt.Color;
import gui.MapOfFields;
import gui.Window;
import simulation.Game;
import time.DescriptionOfMove;

public class RegularFigure extends Figure {

	public RegularFigure(Color c) {
		this.color = c;
	
	}

	@Override
	public void colorField(Integer position) {

		Integer positionInString = Integer.parseInt(Game.stringPath[position]);
		MapOfFields.map.get(positionInString).setBackground(this.color);
		MapOfFields.map.get(positionInString).repaint();
		MapOfFields.map.get(positionInString).setFigureOnField(this);
		MapOfFields.map.get(position).setIsTaken(true);
		Window.numberOfPassedFieldsBasedOnName.put(this.getName(), position);
	}

	@Override
	public void removeFigure(Integer position) {

		Integer positionInString = Integer.parseInt(Game.stringPath[position]);
		MapOfFields.map.get(positionInString).setBackground(Color.white);
		MapOfFields.map.get(positionInString).repaint();
		MapOfFields.map.get(positionInString).setFigureOnField(null);
		MapOfFields.map.get(position).setIsTaken(false);
	}
	@Override
	public String toString() {

		String stigla = new String();
		if(this.gotToFinalField == true)
			stigla  = "DA";
		else stigla = "NE";
		String s = new String();
		for(Integer in : this.passedFields) {
			s = s + in.toString() + "->";
		}
		return this.Name + " " + "(" + DescriptionOfMove.colorName(this.color)+ ", obicna figura)  - prijedjeni put " + s + " - stigla do cilja ( " + stigla + ")";

	}
}
