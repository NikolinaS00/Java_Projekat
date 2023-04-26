package cards;

import simulation.Game;

public class SpecialCard extends Card{

	Integer numberOfHoles;
	
	public SpecialCard() {
		numberOfHoles =  (int) Math.floor(Math.random() * ((Game.stringPath.length - 1) - 1 + 1) + 1);
	}

	public Integer getNumberOfHoles() {
		return numberOfHoles;
	}

	public void setNumberOfHoles(Integer numberOfHoles) {
		this.numberOfHoles = numberOfHoles;
	}
	
}
