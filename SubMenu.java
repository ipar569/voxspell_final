import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SubMenu extends JFrame implements ActionListener{

	private JButton backMain = new JButton("Go Back to Main Menu");
	private JButton nextLevel = new JButton("Go to Next Level");
	private JButton repeat = new JButton("Repeat Quiz");
	private JButton video = new JButton("Play Video!!");
	
	private JPanel menuPanel = new JPanel();
	private int _level;
	private Main _main;
	private String _file;
	private JButton stats = new JButton("View Stats!!");
	
	public SubMenu(String file, Main main, int level, int correct, int testNum){
		//Setting the size of the main menu and choosing the layout of it.
				_file = file;
				_main = main;
				_level = level;
		
		
		setSize(700,600);
		//Choose default close option.
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				menuPanel.setLayout(null);
				
				//Entering the heading of the main menu to make it look more user freindly.
				JLabel label = new JLabel("The Quiz is finished! You got "+correct+" out of "+testNum+"!!");
				label.setBounds(0, 0, 484, 75);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Arial",Font.BOLD, 18));
				
				//Adding the heading label to the main menu
				menuPanel.add(label);
				backMain.setBounds(36, 421, 112, 39);
				//Adding Actionlistener to each buttons
				backMain.addActionListener(this);
				repeat.setBounds(161, 421, 100, 39);
				
				repeat.addActionListener(this);
				nextLevel.setBounds(271, 421, 121, 39);
				
				nextLevel.addActionListener(this);
				video.setBounds(402, 421, 112, 39);
				
				video.addActionListener(this);
				stats.setBounds(525, 421, 112, 39);
				
				stats.addActionListener(this);
				//Adding buttons to the Main menu.
				menuPanel.add(backMain);
				menuPanel.add(repeat);
				menuPanel.add(stats);
				
						menuPanel.add(nextLevel);
					menuPanel.add(video);
				
				getContentPane().add(menuPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Finding the button where the action event occured i.e. finding 
		//the button that is clicked
		try{
		JButton button = (JButton) e.getSource();
		if (button.equals(backMain)){  
			_main.setVisible(true);
			dispose();
		}else if(button.equals(repeat)){
			dispose();
			Quiz q = new Quiz("NZCER-spelling-lists.txt",_main, _level);
			q.setVisible(true);
		}else if(button.equals(stats)){
			_main.makeTable();
			
		}else if(button.equals(nextLevel)){
			_main.setVisible(true);
			_main.nextLevel();
			dispose();
		}else if(button.equals(video)){
			MediaPlayer player = new MediaPlayer();

		}
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}

}
