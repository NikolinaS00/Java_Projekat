package exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class IncorrectDatasException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IncorrectDatasException(){
		super();
		
	}
	
	public IncorrectDatasException(String mess){
		super(mess);
		JOptionPane.showMessageDialog(new JFrame(), mess, "Dialog",
				JOptionPane.ERROR_MESSAGE);
		
	}

}
