package simulation;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import figures.GhostFigure;
import gui.GUIControll;
import time.Timer;


public class Main {

	public static String input = "."+File.separator+"in" +File.separator+"inFile.txt";
	public static Game game = new Game();
	public static Boolean signForWait = false;
	public static Timer tim = new Timer();
	public static GhostFigure gf = new GhostFigure();
	public static Handler handler ;
	
	public static void main(String[] args) {
		try {
			handler = new FileHandler("." + File.separator +"Logger.log");
			Logger.getLogger(Main.class.getName()).addHandler(handler);
		} catch (SecurityException | IOException e) {
			Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
		} 
		GameControll.getInputDatas(input);
		GUIControll.drawStartingWindow();
	
			
			
		
		
		
		

	}

}
