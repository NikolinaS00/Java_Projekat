package time;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.logging.Logger;
import cards.Card;
import cards.RegularCard;
import cards.SpecialCard;
import gui.Window;
import simulation.Main;

public class DescriptionOfMove {

	public static String colorName(Color c) {
		Logger.getLogger(DescriptionOfMove.class.getName()).addHandler(Main.handler);
		for (Field f : Color.class.getDeclaredFields()) {
			if (f.getType().equals(Color.class))
				try {
					if (f.get(null).equals(c))
						return f.getName().toLowerCase();
				} catch (IllegalArgumentException | IllegalAccessException e) {
					Logger.getLogger(DescriptionOfMove.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
				}
		}
		return null;
	}

	public static void describeMove(Card karta) {

		if(karta instanceof SpecialCard) {
			Window.textPaneOpisZnacenjaKarte.setText("Izvucena je specijalna karta, otvara se " + ((SpecialCard)karta).getNumberOfHoles() + " rupa/e");
		}else if(karta instanceof RegularCard) {
			Window.textPaneOpisZnacenjaKarte.setText("Izvucena je obicna karta, sa brojem " + ((RegularCard)karta).getNumberOfFields() );
		}
			
		
	}


}
