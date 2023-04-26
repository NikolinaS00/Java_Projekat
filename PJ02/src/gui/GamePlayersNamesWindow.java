package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import simulation.Game;

public class GamePlayersNamesWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	public static String nameOfRedPlayer;
	public static String nameOfBluePlayer;
	public static String nameOfGreenPlayer;
	public static String nameOfYellowPlayer;
	private JPanel contentPane;
	public static JTextField BluePlayertextField;
	public static JTextField GreenPlayertextField;
	public static JTextField RedPlayertextField;
	public static JTextField yellowPlayertextField;
	public static Boolean windowClosed = false;
	JButton btnNewButton;
	
	public GamePlayersNamesWindow() {
		setTitle("Set players name");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 330);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Blue player");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(5, 10, 105, 20);
		contentPane.add(lblNewLabel, "2, 2");
		
		BluePlayertextField = new JTextField();
		BluePlayertextField.setSize(110, 20);
		BluePlayertextField.setLocation(120, 10);
		contentPane.add(BluePlayertextField, "6, 2, fill, bottom");
		BluePlayertextField.setColumns(10);
		
		JLabel lblGreenPlayer = new JLabel("Green player");
		lblGreenPlayer.setSize(105, 20);
		lblGreenPlayer.setLocation(5, 45);
		lblGreenPlayer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblGreenPlayer, "2, 6");
		
		GreenPlayertextField = new JTextField();
		GreenPlayertextField.setSize(110, 20);
		GreenPlayertextField.setLocation(120, 45);
		GreenPlayertextField.setColumns(10);
		contentPane.add(GreenPlayertextField, "6, 6, fill, bottom");
		
		JLabel lblRedPlayer = new JLabel("Red player");
		lblRedPlayer.setSize(105, 20);
		lblRedPlayer.setLocation(5, 80);
		lblRedPlayer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblRedPlayer, "2, 10");
		
		RedPlayertextField = new JTextField();
		RedPlayertextField.setSize(110, 20);
		RedPlayertextField.setLocation(120, 80);
		RedPlayertextField.setColumns(10);
		contentPane.add(RedPlayertextField, "6, 10, fill, bottom");
		
		JLabel lblYellowPlayer = new JLabel("Yellow player");
		lblYellowPlayer.setSize(105, 20);
		lblYellowPlayer.setLocation(5, 115);
		lblYellowPlayer.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblYellowPlayer, "2, 14");
		
		yellowPlayertextField = new JTextField();
		yellowPlayertextField.setSize(110, 20);
		yellowPlayertextField.setLocation(120, 115);
		yellowPlayertextField.setColumns(10);
		contentPane.add(yellowPlayertextField, "6, 14, fill, bottom");
		
		 btnNewButton = new JButton("Create players");
		 btnNewButton.setSize(100, 30);
		 btnNewButton.setLocation(324, 193);
		btnNewButton.setBorder(new LineBorder(Color.BLUE));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton, "8, 17, 1, 2");
		
		GamePlayersNamesWindow.BluePlayertextField.setEnabled(false);
		GamePlayersNamesWindow.GreenPlayertextField.setEnabled(false);
		GamePlayersNamesWindow.RedPlayertextField.setEnabled(false);
		GamePlayersNamesWindow.yellowPlayertextField.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnNewButton) {
			Game.generatePlayers();
			Game.drawGui();
			this.dispose();
			windowClosed = true;			
		}
	}

}
