
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * written by Injae Park
 */

public class Quiz extends JFrame implements ActionListener {

	private JTextField txt = new JTextField("Enter Words Here!!");
	private JButton btn = new JButton(Images.scaleImage(75, 75, new ImageIcon("C:\\Users\\ip569\\Desktop\\se206\\project\\Voxspell\\img\\imageedit_3_2594180166.png")));
	private JButton speak = new JButton(Images.scaleImage(75, 75, new ImageIcon("C:\\Users\\ip569\\Desktop\\se206\\project\\Voxspell\\img\\imageedit_1_7914732336.png")));
	private JButton spelling = new JButton(Images.scaleImage(75, 75, new ImageIcon("C:\\Users\\ip569\\Desktop\\se206\\project\\Voxspell\\img\\imageedit_1_7914732336.png")));
	private JLabel score;
	private int _testNo=1;
	private int _wc;
	private JLabel label,label1;
	private int incorrect;
	private ArrayList<String> _testList = new ArrayList<String>();
	private int _maxNum;
	private String _level;
	private int _testNum;
	private WordList _wordList;

	private int _attempts;
	private int _fails;
	private int _correct;
	private String _voice;
	private ArrayList<String> _voices;
	private JComboBox<String> _selectVoices;
	private JButton stats = new JButton("View Stats!!");
	private JPanel panel_0;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3 = new JPanel();
	private JPanel panel_4 = new JPanel();
	private JPanel panel_5 = new JPanel();
	private JPanel panel_6 = new JPanel();
	private JPanel panel_7 = new JPanel();
	private JPanel panel_8 = new JPanel();
	private JPanel panel_9 = new JPanel();
	private JLabel incorrectimg = new JLabel(Images.scaleImage(75, 75, new ImageIcon(".\\img\\incorrect.jpg")));
	private JLabel correctImg = new JLabel(Images.scaleImage(75, 75, new ImageIcon(".\\img\\correct.jpg")));
	private HashMap<Integer,JPanel> panels = new HashMap<Integer,JPanel>();
	private final JLabel lblTestNumber = new JLabel("Test Number: ");

	//Constructor takes two input. One file name contained the wordlist and second is 
	//the object where the quiz is excuted.
	public Quiz(WordList wordList,String level) throws  Exception {
		getContentPane().setBackground(Color.WHITE);
		//Initialising field variables using arguments

		_wordList = wordList;
		_level = level;

		//getAccuracy();

		_selectVoices = selectVoice();
		/*
		_voice = _voices.get(0);
*/
		//Setting the size and layout of the spelling quiz
		setSize(700,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		changeVoice("voice_akl_nz_jdt_diphone");
		JPanel bottom = new JPanel();
		bottom.setBounds(0, 347, 700, 115);
		//Setting the heading of the quiz using label.
		if(!_wordList._failed){
			label1 = new JLabel("Review Mistakes. " + _level);
			_maxNum = 5;
		}
			label1 = new JLabel("New Spelling Quiz. " + _level);
			label1.setLocation(20, 11);
			label1.setSize(650, 45);
			_maxNum = 10;
		
		//Defining the font style of the heading
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Comic Sans MS", Font.BOLD, 25));

		//Getting the word count.
		_wc = _wordList.getWordCount(level);

		//Choosing the number of quiz depending on the word count
		String tts = "";
		if(_wc<_maxNum){
			_testNum=_wc;
			tts="Spell word 1 of "+_wc+": ";
			label = new JLabel(tts);
		}else{
			_testNum = _maxNum;
			tts="Spell word 1 of "+_maxNum+": ";
			label = new JLabel(tts);
		}

		//Word to be tested
		setTestList();
			spelling.setBackground(Color.WHITE);
			spelling.setLocation(590, 95);
			spelling.setSize(80, 80);
		//if(_maxNum==5){
			getContentPane().add(spelling);
		//} 
		
		score = new JLabel(_correct+" out of "+_testNum);
		score.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		score.setBounds(20, 285, 221, 30);
		score.setHorizontalAlignment(SwingConstants.CENTER);

		//Adding labels and pane to the main frame.
		getContentPane().add(label1);
		getContentPane().setLayout(null);
		getContentPane().add(score);
		getContentPane().add(_selectVoices);
		getContentPane().add(stats);
				txt.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						txt.setText("");
					}
				});
				txt.setBackground(new Color(255, 255, 153));
				txt.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
				txt.setHorizontalAlignment(SwingConstants.CENTER);
	
				txt.setBounds(20, 95, 470, 171);
				getContentPane().add(txt);
		
				//Setting the size of the JText field.
				txt.setPreferredSize(new Dimension(200,30));
				this.getRootPane().setDefaultButton(btn);
						btn.setBackground(Color.WHITE);
						btn.setBounds(500, 186, 80, 80);
						getContentPane().add(btn);
						
						speak.setBounds(500, 95, 80, 80);
						getContentPane().add(speak);
						
						ImageIcon setting = new ImageIcon("C:\\Users\\ip569\\Downloads\\settings-1630709_640.png");
						Image img2 = setting.getImage();
						Image newimg = img2.getScaledInstance(65, 65, java.awt.Image.SCALE_SMOOTH);
						setting = new ImageIcon(newimg); 
						JButton button = new JButton(setting);
						button.setBackground(Color.WHITE);
						button.setBounds(590, 186, 80, 80);
						getContentPane().add(button);
						
						
						
						panel_2 = new JPanel();
						panel_2.setBackground(new Color(204, 204, 153));
						panel_2.setBounds(328, 349, 75, 75);
						getContentPane().add(panel_2);
						
						panel_0 = new JPanel();
						panel_0.setBackground(new Color(204, 204, 153));
						panel_0.setBounds(68, 349, 75, 75);
						getContentPane().add(panel_0);
						panel_0.setLayout(new BorderLayout(0, 0));
						
						panel_1 = new JPanel();
						panel_1.setBackground(new Color(204, 204, 153));
						panel_1.setBounds(198, 349, 75, 75);
						getContentPane().add(panel_1);
						panel_3.setBackground(new Color(204, 204, 153));
						panel_3.setBounds(458, 349, 75, 75);
						
						getContentPane().add(panel_3);
						panel_4.setBackground(new Color(204, 204, 153));
						panel_4.setBounds(595, 349, 75, 75);
						
						getContentPane().add(panel_4);
						panel_5.setBackground(new Color(204, 204, 153));
						panel_5.setBounds(68, 450, 75, 75);
						
						getContentPane().add(panel_5);
						panel_6.setBackground(new Color(204, 204, 153));
						panel_6.setBounds(198, 450, 75, 75);
						
						getContentPane().add(panel_6);
						panel_7.setBackground(new Color(204, 204, 153));
						panel_7.setBounds(328, 450, 75, 75);
						
						getContentPane().add(panel_7);
						panel_8.setBackground(new Color(204, 204, 153));
						panel_8.setBounds(458, 450, 75, 75);
						
						getContentPane().add(panel_8);
						panel_9.setBackground(new Color(204, 204, 153));
						panel_9.setBounds(595, 450, 75, 75);
						
						getContentPane().add(panel_9);
						
						JLabel label_1 = new JLabel("1:");
						label_1.setHorizontalAlignment(SwingConstants.LEFT);
						label_1.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_1.setBounds(23, 364, 35, 45);
						getContentPane().add(label_1);
						
						JLabel label_2 = new JLabel("2:");
						label_2.setHorizontalAlignment(SwingConstants.LEFT);
						label_2.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_2.setBounds(153, 364, 35, 45);
						getContentPane().add(label_2);
						
						JLabel label_3 = new JLabel("3:");
						label_3.setHorizontalAlignment(SwingConstants.LEFT);
						label_3.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_3.setBounds(283, 364, 35, 45);
						getContentPane().add(label_3);
						
						JLabel label_4 = new JLabel("4:");
						label_4.setHorizontalAlignment(SwingConstants.LEFT);
						label_4.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_4.setBounds(413, 364, 35, 45);
						getContentPane().add(label_4);
						
						JLabel label_5 = new JLabel("5:");
						label_5.setHorizontalAlignment(SwingConstants.LEFT);
						label_5.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_5.setBounds(545, 364, 35, 45);
						getContentPane().add(label_5);
						
						JLabel label_6 = new JLabel("10:");
						label_6.setHorizontalAlignment(SwingConstants.LEFT);
						label_6.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_6.setBounds(545, 466, 45, 45);
						getContentPane().add(label_6);
						
						JLabel label_7 = new JLabel("9:");
						label_7.setHorizontalAlignment(SwingConstants.LEFT);
						label_7.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_7.setBounds(413, 466, 35, 45);
						getContentPane().add(label_7);
						
						JLabel label_8 = new JLabel("8:");
						label_8.setHorizontalAlignment(SwingConstants.LEFT);
						label_8.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_8.setBounds(283, 466, 35, 45);
						getContentPane().add(label_8);
						
						JLabel label_9 = new JLabel("7:");
						label_9.setHorizontalAlignment(SwingConstants.LEFT);
						label_9.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_9.setBounds(153, 466, 35, 45);
						getContentPane().add(label_9);
						
						JLabel label_10 = new JLabel("6:");
						label_10.setHorizontalAlignment(SwingConstants.LEFT);
						label_10.setFont(new Font("Comic Sans MS", Font.BOLD, 23));
						label_10.setBounds(23, 466, 35, 45);
						getContentPane().add(label_10);
						lblTestNumber.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
						lblTestNumber.setBounds(20, 67, 192, 17);
						
						getContentPane().add(lblTestNumber);
						speak.addActionListener(this);
				
						//Adding action listeners to the button.
						btn.addActionListener(this);
		
		_selectVoices.addActionListener(this);
		spelling.addActionListener(this);
		stats.addActionListener(this);
		
		putPanels();

		//Speaking out instruction to start and the word to be tested.
		festival("Spell "+_testList.get(_testNo-1)+".");

	}

	private void putPanels(){
		panels.put(0, panel_0);
		panels.put(1, panel_1);
		panels.put(2, panel_2);
		panels.put(3, panel_3);
		panels.put(4, panel_4);
		panels.put(5, panel_5);
		panels.put(6, panel_6);
		panels.put(7, panel_7);
		panels.put(8, panel_8);
		panels.put(9, panel_9);
	}
	
	public void actionPerformed(ActionEvent e) {
		//Getting the word that user wrote
		String word = txt.getText();
		
		if(e.getSource().equals(_selectVoices)){
			String data = (String) _selectVoices.getItemAt(_selectVoices.getSelectedIndex());
			_voice = data;
			return;
		}
		try{
			//If user pressed speak button,  the word
			//is spoken by festival.
			JButton button = (JButton) e.getSource();  
			if (button.equals(speak)){  

				festival(_testList.get(_testNo-1));
				return;
				//if spelling is pressed, the alphabet of the word being tested  is spoken.
			}else if (button.equals(spelling)){
				festivalAlphabet(_testList.get(_testNo-1));
				return;
				//open up the stats view.
			}
			//If user is correct
			JPanel panel = panels.get(_testNo-1);
			if(_testList.get(_testNo-1).equalsIgnoreCase(word)){
				//Showing and telling correct message

				//Remove word from failed test list
				removeFailed(_testList.get(_testNo-1));

				panel.add(correctImg);
				
				_attempts++;
				_testNo++;
				_correct++;


				
				incorrect =0;
				//If user gets incorrect
			}else{
				//If second time failing
				if(incorrect<1){
					//Setting message to the user about the fault
					festival("Incorrect!! Spell"+_testList.get(_testNo-1)+".");
					//Word is spoken again.
					incorrect++;
					txt.setText("");
					return;
					//First time failing
				}else{
					//Result message to user
					
					//increase test number and fail value
					_attempts++;
					_fails++;

					//Adding failed word to the failed list.
					failed();
					failedTotal();
					
					panel.add(incorrectimg);
					
					//Changing field as needed

					_testNo++;
					incorrect =0;

					//Setting new label for new quiz

				}
			}
			//updating the values.
			score.setText(_correct+" out of "+_testNum);
			updateAccuracy();
			//Clearing the Jtext field
			txt.setText("");
			//If test is finished
			if((_testNo==_maxNum+1)||(_wc<_testNo)){
				//Telling the user the teset is finished
				
				if(_maxNum==5){
					//Bring back the main menu
					Main main = new Main();
					main.setVisible(true);
					dispose();
				}else{
					//opens options menu where user can choose their next action.
					SubMenu sub = new SubMenu(_wordList,_level,_correct,_testNo-1);
					sub.setVisible(true);
					dispose();
				}

			}else{
				//Continue the quiz
				festival(" Spell "+_testList.get(_testNo-1)+".");
			}
		}catch(Exception excep){
			excep.printStackTrace();
		}
	}

	//Function that allows user to change the voice of festival.
	private JComboBox<String> selectVoice() throws Exception{
/*
		Festival f = new Festival("","");
		_voices = f.listOfVoices();

		String[] str = new String[_voices.size()];
		for(int i = 0;i<_voices.size();i++)str[i]=_voices.get(i);*/


		JComboBox<String> voices = new JComboBox<>();


		return voices;
	}
	
	//method to change the voice.
	private void changeVoice(String voice) throws IOException{
		File failed = new File(".festivalrc");
		//If file does not exist, create new file
		if(!failed.exists()) {
			failed.createNewFile();
		} 

		//Appending the word to the file
		Writer output;
		output = new BufferedWriter(new FileWriter(failed,false)); 
		output.append("(set! voice_default '"+voice +")");
		output.close();
	}

	/*
	//Method that tells u if a string contains something other than alphabet
	private boolean onlyAlphabet(String s){
		return s.matches("[a-zA-Z]+");
	}
	 */

	//Method that uses festival to speak out the string passed into it
	private void festival(String tts) throws Exception{
		Festival say = new Festival(tts,_voice);
		say.execute();

	}

	//Speaking out the spelling of the word passed into it
	private void festivalAlphabet(String tts) throws Exception{
		String word="";
		String[] alpha = tts.split("");

		for(int i=0;i<alpha.length;i++)
			word = word + alpha[i]+" ";
		festival(word);
	}

	/*
	//Setting the word to be tested using getRandomWord method from word list
	private void setWord() throws IOException{
		WordList wordlist = new WordList(_file);
		_testWord = wordlist.getRandomWord(1);	
	}
	 */

	private void setTestList() throws IOException{
		_testList = _wordList.createTestList(_level,_maxNum);	
	}

	//Obtains the accuracy values from the save file (for the current quiz level)
	// then add the levels to the corresponding fields
	private void getAccuracy() throws IOException {
		File accuracy = new File(".accuracy_" + _level);
		if (! accuracy.exists()) {
			accuracy.createNewFile();
		} else {

			FileReader fr = new FileReader(accuracy);
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine();
			_attempts = Integer.parseInt(str);
			str = br.readLine();
			_fails = Integer.parseInt(str);

		}
	}
	//Write the values from the accuracy fields (_attempts, _fails) to the corresponding
	// save files
	private void updateAccuracy() throws IOException {
		File accuracy = new File(".accuracy_" + _level);

		PrintWriter pw = new PrintWriter(accuracy);
		pw.close();

		FileWriter fw = new FileWriter(accuracy);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(_attempts + "\n");
		bw.write(_fails + "\n");
		bw.close();
	}

	//Putting Failed word into failed list
	private void failed() throws IOException{
		File failed = new File(".\\failed\\.failed"+_level);
		//If file does not exist, create new file
		if(!failed.exists()) {
			failed.createNewFile();
		} 

		//Appending the word to the file
		Writer output;
		output = new BufferedWriter(new FileWriter(failed,true)); 
		output.append(_testList.get(_testNo-1)+"\n");
		output.close();
	}

	private void removeFailed(String word) throws IOException{
		File failed = new File(".failed"+_level);
		//If file does not exist, create new file
		if(!failed.exists()) {
			failed.createNewFile();
		} 
		//Creating temporary file to store data
		File temp = new File(".temp");
		if(!temp.exists())
			temp.createNewFile();

		//Choosing input and output files
		BufferedReader input = new BufferedReader(new FileReader("."+File.separator+failed));
		PrintWriter output= new PrintWriter(new FileWriter("."+File.separator+temp));

		String line;

		//Reading word where and adding it to arrayList if it is not an empty line
		while ((line = input.readLine()) != null){
			//If the line does not equal to line to remove, it is copied to temp file
			if(!word.equalsIgnoreCase(line.trim())){
				output.println(line);
				output.flush();
			}	
		}

		//Closing input output streams
		input.close();
		output.close();

		//Delete orginal file
		failed.delete();

		//Changing output file to be the failed list file.
		temp.renameTo(failed);
	}

	//Adding failed word to the failed_total list
	private void failedTotal() throws IOException{
		File failed = new File(".failed_total"+_level);
		//If file does not exist, create new file
		if(!failed.exists()) {
			failed.createNewFile();
		} 

		//Appending the word to the file
		Writer output;
		output = new BufferedWriter(new FileWriter(failed,true)); 
		output.append(_testList.get(_testNo-1)+"\n");
		output.close();
	}
}