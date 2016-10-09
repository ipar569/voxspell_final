import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

/* 
 * Author: Injae Park and Jack Huang
 */

public class Main extends JFrame implements ActionListener {

	//Fields defining the buttons to be used
	private JButton quiz = new JButton("New Spelling Quiz");
	private JButton review = new JButton("Review Mistakes");
	private JButton viewStats = new JButton("View Statistics");
	private JButton clearStats = new JButton("Clear Statistics");
	private JButton newButton;
	private JPanel menuPanel = new JPanel();
	private String _level="";
	private String _wordlist = ".\\NZCER-spelling-lists.txt";

	public Main() {
		setResizable(false);

		//Setting the size of the main menu and choosing the layout of it.
		setSize(700,600);
		//Choose default close option.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		menuPanel.setBackground(Color.WHITE);
		menuPanel.setLayout(null);
		quiz.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		quiz.setBounds(10, 488, 146, 51);
		quiz.setBackground(new Color(255, 102, 204));
		
		//Adding Actionlistener to each buttons
		quiz.addActionListener(this);
	
		review.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		review.setBounds(460, 490, 132, 46);
		review.addActionListener(this);
		viewStats.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		viewStats.setBounds(166, 487, 129, 53);
		viewStats.addActionListener(this);
		clearStats.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		clearStats.setBounds(305, 489, 145, 48);
		clearStats.addActionListener(this);

		//Adding buttons to the Main menu.
		menuPanel.add(quiz);
		menuPanel.add(review);
		menuPanel.add(viewStats);
		menuPanel.add(clearStats);

		getContentPane().add(menuPanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(21, 11, 650, 450);
		menuPanel.add(panel);
		
		ImageIcon img = new ImageIcon(".\\img\\Voxspell_banner.jpg");
		JLabel imga = new JLabel(img);
		panel.add(imga);
		
		ImageIcon setting = new ImageIcon("C:\\Users\\ip569\\Downloads\\settings-1630709_640.png");
		setting = Images.scaleImage(60,60,setting); 
		newButton= new JButton(setting);
		newButton.setBounds(602, 478, 69, 61);
		menuPanel.add(newButton);
		
		newButton.addActionListener(this);
	}
	

	

	public void actionPerformed(ActionEvent e) {
		//Finding the button where the action event occured i.e. finding 
		//the button that is clicked
		JButton button = (JButton) e.getSource();  
		try {

			//If quiz button is clicked
			if (button.equals(quiz)){  

				//If no wordlist is found show error message to user
				File f = new File("NZCER-spelling-lists.txt");
				if(!f.exists()){
					JOptionPane.showMessageDialog(this, "No wordlist file is found!!\n(Please place wordlist file in the working directory)", "Warning", getDefaultCloseOperation());
					//If there is no word inside the lsit
				}else{ 
					SelectCategory select = new SelectCategory(new WordList(_wordlist));
					select.setVisible(true);
				}
				return;  
				//If review button is clicked
			}else if (button.equals(review)){  

				File f = new File(".failed"+_level);
				//If failed file does not exist or there is no word inside it
				if(!f.exists()){
					JOptionPane.showMessageDialog(this, "No failed word to be tested!!", "Warning", getDefaultCloseOperation());
				}else{ 

				}
				return;  
				//If viewStats button is clicked
			}else if (button.equals(viewStats)){  
				//ViewStats view = new ViewStats();
				//view.setVisible(true);
				makeTable();

				return;  
				//If clearStats button is clicked
			}else if (button.equals(clearStats)){  
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int clear = JOptionPane.showConfirmDialog (this, "Would You Like to Clear the Statistics?","Warning",dialogButton);
				if(clear == JOptionPane.YES_OPTION){
					clearStats();
				}

				return;  
			}else if(button.equals(newButton)){
				chooseFile();
			}
			//If exception is caught
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	/*
	 * This functions makes the table using the ViewAccuracy class. Table is placed in a 
	 * new JPanel.
	 */
	protected void makeTable() {
		ViewAccuracy va = new ViewAccuracy();
		JTable table = new JTable(va);
		final JFrame fr = new JFrame();
		fr.setSize(500,500);
		fr.setVisible(true);
		JPanel statsPanel = new JPanel();
		//Add a close button to close the frame
		JButton returnBtn = new JButton("Close Stats");
		statsPanel.setLayout(new BorderLayout());
		statsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		returnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
			}
			
		});
		statsPanel.add(returnBtn, BorderLayout.SOUTH);
		fr.getContentPane().add(statsPanel);
		
	}


	/*
	 * main method that brings up the main menu
	 */
	public static void main(String[] agrs) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		 UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main frame = new Main();
				frame.createAccuracy();
				frame.setVisible(true);
			}
		});
	}

	//This method clears the stats by overwriting existing files that
	//stores information.
	private void clearStats() throws IOException{
		//String array contains the name of the files to be cleared.
		String[] files = {".mastered",".faulted",".failed_total",".failed"};

		for(String f:files){
			//Check if file exist or not and if don't exist, create new one.
			File file = new File(f);
			if(!file.exists()) {
				file.createNewFile();
			} 
			//If exist clear the file by setting append to false.
			Writer output;
			output = new BufferedWriter(new FileWriter(f,false));
			output.close();
		}

		for (int i = 1; i <= 11; i++) {
			File accuracy = new File(".accuracy_" + i);
			accuracy.delete();
		}
		createAccuracy();
	}
	
	//Creates save files to store the accuracy, then add zeros to the file. There is a save 
	//file for each level
	private void createAccuracy() {

		for (int i = 1; i <= 11; i++) {
			try {
				File accuracy = new File(".accuracy_" + i);
				if (! accuracy.exists()) {
					accuracy.createNewFile();

					FileWriter fw = new FileWriter(accuracy);
					BufferedWriter bw = new BufferedWriter(fw);

					bw.write("0" + "\n");
					bw.write("0" + "\n");

					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	private void chooseFile(){
		JFileChooser chooser = new JFileChooser("");
		chooser.setCurrentDirectory(new java.io.File("."));
		int returnValue = chooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = chooser.getSelectedFile();
	          _wordlist = selectedFile.getAbsolutePath();
	      }
	}
}