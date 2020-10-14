package txTGameEngine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	
	static Color CharacterNameColor = Color.RED; // set color for Character Name
	static Color DialogColor = Color.white; // set Dialog Text Color
	
	//program will red items below from a Settings document(To Add in the future)
	static String BackgroundImagePath = "./MainDialogTree/Background"+"/bg.jpg";
	
	public static ImageIcon scaleImage(ImageIcon icon, int w, int h)
    {
        int nw = w;
        int nh = h;    

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
	public static ImageIcon scaleCharacterImage(ImageIcon icon, int w, int h)
    {
        int nw = (int)icon.getIconWidth();
        int nh = (int)icon.getIconHeight();

       if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
          
        }

        if(icon.getIconHeight() > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();          
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
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
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		characterImage.setIcon(scaleCharacterImage(new ImageIcon(iconPath), (int)200, (int)200)); // show character icon
		option1.setText(OptionTxT[0]); // show Option 1 text
		option2.setText(OptionTxT[1]); // show Option 2 text
		option3.setText(OptionTxT[2]); // show Option 3 text
		name.setText(character.Name); // show active character name
		name.setForeground(CharacterNameColor);
		dialogText.setText(	String.format(html,(int)dim.getWidth()-700,txt)); // show dialog text	
		dialogText.setForeground(DialogColor);
	}
	
	public static void game() {
		
		
		read(_Option_Path,_Dialog_Path); // read standard items
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		JPanel MainPanel = new JPanel();
		JPanel MainPanelBackground = new JPanel();
		
		JFrame Window = new JFrame(); // set new window 
		
		
		JLabel thumb = new JLabel("",JLabel.CENTER);
		ImageIcon imgThisImg = new ImageIcon(BackgroundImagePath);
		ImageIcon ScaledImageIcon = scaleImage(imgThisImg, (int)dim.getWidth(), (int)dim.getHeight());
		
				
		// window settings
		Window.setBounds(1,1 ,(int)dim.getWidth(), (int)dim.getHeight()); 
		thumb.setBounds(0,0 ,(int)dim.getWidth(), (int)dim.getHeight());		
		thumb.setIcon(ScaledImageIcon);
		
		MainPanel.setBounds(0, 0,(int)dim.getWidth(), (int)dim.getHeight());
		MainPanel.setBackground(null);
		
		MainPanelBackground.setBackground(Color.GREEN);
		MainPanelBackground.setBounds(1,1 ,(int)dim.getWidth(), (int)dim.getHeight()); 
						
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
		option1.setBounds(220,(int)dim.getHeight()-160,(int)dim.getWidth()-320,20);
		option2.setBounds(220,(int)dim.getHeight()-130,(int)dim.getWidth()-320,20);
		option3.setBounds(220,(int)dim.getHeight()-100,(int)dim.getWidth()-320,20);
		
		// set character image x y width height alignment
		characterImage.setBounds(5,70,200,200);
		characterImage.setVerticalAlignment(JLabel.TOP);
		
		// set name x y width height alignment
		name.setBounds(5,10,200,50);
		name.setVerticalAlignment(JLabel.TOP);
		
		// set dialog x y width height alignment		
		dialogText.setBounds(220,10,(int)dim.getWidth()-320,(int)dim.getHeight()-200);
		//JScrollPane scroller = new JScrollPane(dialogText);
		dialogText.setVerticalAlignment(JLabel.TOP);
		
		// add items to window
		MainPanel.setLayout(null);
		thumb.setLayout(null);
		MainPanelBackground.setLayout(null);
		MainPanel.add(name);	
		MainPanel.add(dialogText);
		MainPanel.add(option1);
		MainPanel.add(option2);
		MainPanel.add(option3);
		MainPanel.add(characterImage);
		MainPanel.add(thumb);
		MainPanelBackground.add(MainPanel);
		
		Window.add(MainPanelBackground);
	}
	
	
	
	public static void main(String[] args) {
		
		game();
		
	}
	
	

}
