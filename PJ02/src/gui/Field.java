package gui;


import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


import figures.Figure;

public class Field extends JPanel {

	private static final long serialVersionUID = 1L;
	Boolean hasHole = false;
	Boolean isTaken = false;
	Figure figureOnField;
	Boolean hasDiamond = false;
	public JLabel picLabel = new JLabel();

	public Field(int sx, int sy, int lx, int ly, Color color, int nOfField) {
		this.setBounds(sx, sy, lx, ly);
		this.setBackground(color);
		this.setLayout(null);
		Window.matrica.add(this);
	}

	public Boolean getHasDiamond() {
		return hasDiamond;
	}

	public void setHasDiamond(Boolean hasDiamond) {
		this.hasDiamond = hasDiamond;
	}

	public Figure getFigureOnField() {
		return figureOnField;
	}

	public void setFigureOnField(Figure figureOnField) {
		this.figureOnField = figureOnField;
	}

	public Boolean getHasHole() {
		return hasHole;
	}

	public void setHasHole(Boolean hasHole) {
		this.hasHole = hasHole;
	}

	public Boolean getIsTaken() {
		return isTaken;
	}

	public void setIsTaken(Boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	

}
