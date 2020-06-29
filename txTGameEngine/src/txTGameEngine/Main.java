package txTGameEngine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	
	/* IMPORTANT
	 * Applications made for Linux
	 * paths to folders are specially set just for it
	 * for now it can have problems when run on windows 
	 * this will be repaired in the future 
	 */
	
	/* READ ME (How to Use)
	 * Engine to create Dialogs will be added in future
	 * right now you need to make folder for a new character in character folder:
		 * Dialog_x folder where x is number of dialog, Dialog_x should contain:
			 * 1x Dialog.txt containing Dialog text;
			 * 3x OptionX.txt containing:
				 * in first line dialog text
				 * in second line character that should be next after picking option
				 * in third line dialog number that should be next after this option
			 * 
	 */
	
	
	static JLabel name = new JLabel("", JLabel.CENTER); // character name Label
	static JLabel dialogText = new JLabel("",JLabel.CENTER); // character Dialog Label
	static JLabel characterImage = new JLabel("",JLabel.CENTER); // character Image Label

	static JButton option1 = new JButton(""); // Dialog option 1 Label
	static JButton option2 = new JButton(""); // Dialog option 2 Label
	static JButton option3 = new JButton(""); // Dialog option 3 Label
		
	static String html = "<html><body style='width: %1spx'>%1s"; // needed to set up Word Wrap on Dialog
		
	static String _Character_Speaking = "Character"; // set active character
	static String number = "1"; // set active dialog
	static String _Dialog_number_path = number; // set Dialog number Path
	static String _Dialog_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Dialog.txt"; // set path to active dialog text
	static String _Option_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Option"; // set path to Options of active dialog
	
	static String OptionTxT[] = new String[3]; // Array contain Option Text
	static String OptionCharacter[] = new String[3]; // Array contain character that option set 
	static String OptionDialog[] = new String[3]; // Array contain Dialog number that option set 
	static String txt = ""; // string needed for using all lines from dialog document
	static Character character = new Character(); // set up new character (useless, just string name would be better)
	static String iconPath = "./MainDialogTree/"+_Character_Speaking+"/image.jpg"; // path to active character icon (standard 70 x 80)
	
	
	public static void read(String path_Option, String path_Dialog) {
		iconPath = "./MainDialogTree/"+_Character_Speaking+"/image.jpg"; // get active character icon
		
		character.Name=_Character_Speaking; // get active character name
		
		int i = 0;
		BufferedReader reader;
		// read Dialog Text
		try {
			reader = new BufferedReader(new FileReader(
					_Dialog_Path));
			String line = reader.readLine();
			while (line != null) {				
				
				txt = txt + line + " ";
				
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// read options
		for(int x=1; x<4; x++) {
			_Option_Path = path_Option+x+".txt"; 
			i=0;
			try {
				reader = new BufferedReader(new FileReader(
						_Option_Path));
				String line = reader.readLine();
				while (line != null) {
					i++;
					if(i%3 == 1) {									
						OptionTxT[x-1] = line; // read option x text
					}
					if(i%3 == 2) {									
						OptionCharacter[x-1] = line; // read option x Character
					}
					if(i%3 == 0) {									
						OptionDialog[x-1] = line; // read option x dialog number
					}
					
					// read next line
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void Show() {
		characterImage.setIcon(new ImageIcon(iconPath)); // show character icon
		option1.setText(OptionTxT[0]); // show Option 1 text
		option2.setText(OptionTxT[1]); // show Option 2 text
		option3.setText(OptionTxT[2]); // show Option 3 text
		name.setText(character.Name); // show active character name
		dialogText.setText(	String.format(html,560,txt)); // show dialog text		
	}
	
	public static void game() {
		read(_Option_Path,_Dialog_Path); // read standard items

		JFrame Window = new JFrame(); // set new window 
		
		// window settings
		Window.setBounds(10,10 ,900, 320); 
		Window.setBackground(Color.darkGray);
		Window.setResizable(false);
		Window.setVisible(true);		
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		
		
	
		
		
		// buttons actions
		option1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				_Character_Speaking = OptionCharacter[0];
				_Dialog_number_path = OptionDialog[0];
				_Option_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Option"; 
				_Dialog_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Dialog.txt";
				txt = ""; // clear dialog text				
				read(_Option_Path,_Dialog_Path);
				Show();
			}   
		});
		option2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				_Character_Speaking = OptionCharacter[1];
				_Dialog_number_path = OptionDialog[1];
				_Option_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Option";
				_Dialog_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Dialog.txt";
				txt = ""; // clear dialog text
				read(_Option_Path,_Dialog_Path);
				Show();
			}   
		});
		option3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				_Character_Speaking = OptionCharacter[2];
				_Dialog_number_path = OptionDialog[2];
				_Option_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Option"; 
				_Dialog_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Dialog.txt";
				txt = ""; // clear dialog text
				read(_Option_Path,_Dialog_Path);
				Show();
			}   
		});
		
		Show();
		
		// set options x y width height
		option1.setBounds(120,220,750,20);
		option2.setBounds(120,250,750,20);
		option3.setBounds(120,280,750,20);
		
		// set character image x y width height alignment
		characterImage.setBounds(10,70,100,80);
		characterImage.setVerticalAlignment(JLabel.TOP);
		
		// set name x y width height alignment
		name.setBounds(10,10,100,50);
		name.setVerticalAlignment(JLabel.TOP);
		
		// set dialog x y width height alignment		
		dialogText.setBounds(120,10,750,200);
		dialogText.setVerticalAlignment(JLabel.TOP);
		
		// add items to window
		Window.setLayout(null);
		Window.add(name);
		Window.add(dialogText);
		Window.add(option1);
		Window.add(option2);
		Window.add(option3);
		Window.add(characterImage);
	     
		
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		game();
		
	}

}
