import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectCategory extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public SelectCategory(WordList wordlist) {
		
		String[] word = wordlist.getCategories();
		
		JComboBox box = new JComboBox(word);
		box.setBounds(100, 71, 226, 29);
		
		setBounds(100, 100, 450, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		contentPanel.add(box);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a category that you want:");
		lblPleaseSelectA.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblPleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectA.setBounds(50, 21, 334, 33);
		contentPanel.add(lblPleaseSelectA);
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String category = (String) box.getSelectedItem();
						if(wordlist.getWordCount(category)<1){
							JOptionPane.showInputDialog( "No word to be tested!!", "Warning");
						}else{
							//else start the quiz
							setVisible(false);
							Quiz q;
							try {
								q = new Quiz(wordlist, category);
								q.setVisible(true);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
