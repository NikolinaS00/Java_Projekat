package figures;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import gui.MapOfFields;
import gui.Window;
import simulation.Game;
import simulation.Main;
import time.DescriptionOfMove;

public class FloatingFigure extends Figure {

	File icon = new File("." + File.separator + "in" + File.separator + "wings.png");

	public FloatingFigure(Color c) {
		this.color = c;
	}

	@Override
	public void colorField(Integer position) {

		Integer positionInString = Integer.parseInt(Game.stringPath[position]);
		MapOfFields.map.get(positionInString).setBackground(this.color);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(icon);
			Icon pic = new ImageIcon(myPicture);
			MapOfFields.map.get(positionInString).picLabel.setIcon(pic);
			MapOfFields.map.get(positionInString).picLabel.setBounds(1, 1, 39, 39);
			MapOfFields.map.get(positionInString).picLabel.setVisible(true);
			
		} catch (IOException e) {
			Logger.getLogger(FloatingFigure.class.getName()).addHandler(Main.handler);
			Logger.getLogger(FloatingFigure.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
		}
		MapOfFields.map.get(positionInString).add(MapOfFields.map.get(positionInString).picLabel);
		MapOfFields.map.get(positionInString).setFigureOnField(this);
		MapOfFields.map.get(position).setIsTaken(true);
		MapOfFields.map.get(positionInString).repaint();
		Window.numberOfPassedFieldsBasedOnName.put(this.getName(), position);
	}

	@Override
	public void removeFigure(Integer position) {

		Integer positionInString = Integer.parseInt(Game.stringPath[position]);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MapOfFields.map.get(positionInString).picLabel.setIcon(null);
				MapOfFields.map.get(positionInString).picLabel.repaint();
				MapOfFields.map.get(positionInString).picLabel.revalidate();
				MapOfFields.map.get(positionInString).add(MapOfFields.map.get(positionInString).picLabel);
				MapOfFields.map.get(positionInString).setBackground(Color.white);
				MapOfFields.map.get(positionInString).revalidate();
				MapOfFields.map.get(position).setIsTaken(false);
				MapOfFields.map.get(positionInString).setFigureOnField(null);
				MapOfFields.map.get(positionInString).repaint();

			}
		});
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
		return this.Name + " " + "(" + DescriptionOfMove.colorName(this.color) + ", lebdeca figura) - prijedjeni put " + s + " - stigla do cilja ( " + stigla + ")" ;
	}

}
