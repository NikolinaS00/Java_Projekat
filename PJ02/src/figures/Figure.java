package figures;

import java.awt.Color;
import java.util.ArrayList;

import player.Player;
public abstract class Figure {

	public Color color;
	public String Name;
	public Integer numberOfPassedFields = 1;
	public Boolean gotToFinalField = false;
	Player player;
	ArrayList<Integer> passedFields = new ArrayList<Integer>();
	Integer numberOfColectedDiamonds = 0;
	
	
	public Figure() {

	}

	public Figure(Color c) {
		color = c;
	}

	public abstract void colorField(Integer position);

	public abstract void removeFigure(Integer position);
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Integer> getPassedFields() {
		return passedFields;
	}

	public void setPassedFields(Integer field) {
		this.passedFields.add(field);
	}

	public Boolean getGotToFinalField() {
		return gotToFinalField;
	}

	public void setGotToFinalField(Boolean gotToFinalField) {
		this.gotToFinalField = gotToFinalField;
	}

	public Integer getNumberOfColectedDiamonds() {
		return numberOfColectedDiamonds;
	}

	public void setNumberOfColectedDiamonds() {
		this.numberOfColectedDiamonds = this.numberOfColectedDiamonds + 1;
	}

	public Integer getNumberOfPassedFields() {
		return numberOfPassedFields;
	}

	public void setNumberOfPassedFields(Integer numberOfPassedFields) {
		this.numberOfPassedFields = numberOfPassedFields;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


}
