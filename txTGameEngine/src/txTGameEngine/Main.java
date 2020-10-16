package txTGameEngine;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// ZWRACA 2 dialogi postaci przy continue. sprawdzic funkcje Show() oraz read()!!! - rozwiazanie = brak czyszczenia zmiennej txt (txt=""; w read())




public class Main implements KeyListener{
	
	static boolean ESC_MENU_STATUS = false;
	
	
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
	 * Engine to create Dialogs will be added in future UPDATE: Editor is already working!
	 * how it work: folder with a character name that contain:
		 * "Dialog_x" folder (where x is number of dialog), "Dialog_x" should contain:
			 * 1x "Dialog.txt" containing Dialog text;
			 * 3x "OptionX.txt" (where X is number of option) containing:
				 * in first (1.) line dialog text
				 * in second (2.) line character name that should speak next after picking that option
				 * in third (3.) line dialog number that should be spoken after this option.
		 * image.jpg - character image. 
	 */	
		
	static JLabel name = new JLabel("", JLabel.CENTER); // character name Label
	static JLabel dialogText = new JLabel("",JLabel.CENTER); // character Dialog Label
	static JLabel characterImage = new JLabel("",JLabel.CENTER); // character Image Label

	static JButton option1 = new JButton(""); // Dialog option 1 Label
	static JButton option2 = new JButton(""); // Dialog option 2 Label
	static JButton option3 = new JButton(""); // Dialog option 3 Label
		
	static String html = "<html><body style='width: %1spx'>%1s"; // needed to set up Word Wrap on Dialog
		
	
	
	
	//// make read Version from a save File - DONE
	static String _Character_Speaking = "Character"; // set active character 
	static String number = "1"; // set active dialog
	////
	
	

	static String _Dialog_number_path = number; // set Dialog number Path
	static String _Dialog_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Dialog.txt"; // set path to active dialog text
	static String _Option_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Option"; // set path to Options of active dialog
	
	static String OptionTxT[] = new String[3]; // Array contain Option Text
	static String OptionCharacter[] = new String[3]; // Array contain character that option set 
	static String OptionDialog[] = new String[3]; // Array contain Dialog number that option set 
	static String txt = ""; // string needed for using all lines from dialog document
	static Character character = new Character(); // set up new character (useless, just string name would be better)
	static String iconPath = "./MainDialogTree/"+_Character_Speaking+"/image.jpg"; // path to active character icon (standard 70 x 80)
	static JFrame Window = new JFrame(); // set new window 
	static JFrame ESCWindow = new JFrame(); // set new window 
	
	public static void read(String path_Option, String path_Dialog) {
		iconPath = "./MainDialogTree/"+_Character_Speaking+"/image.jpg"; // get active character icon
		
		character.Name=_Character_Speaking; // get active character name
		
		int i = 0;
		BufferedReader reader;
		// read Dialog Text
		try {
			txt="";
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
public static void ESCMenu() {			
	
		JLabel dialogText = new JLabel("<html><b>ESC Menu</b></html>",JLabel.CENTER); // Title Label
		
		JButton option1 = new JButton("Start"); // Create Start Button	
		
		option1.setFocusable(false);
		
		JPanel Windows = new JPanel(); // set new Panel for Main Menu  
		
		// Main Menu Frame settings	
		ESCWindow.setBounds(1,1,240,300); 							// x,y,width,height
		ESCWindow.setBackground(Color.darkGray);					// main frame Background Color
		ESCWindow.setResizable(false); 								// turn off Resize option
		ESCWindow.setVisible(false);									// set Visible on start 
		ESCWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Default Close Operation
		
		// Main Menu Panel Settings
		Windows.setBounds(1,1 ,240, 300); 		// x,y,width,height  
		Windows.setBackground(Color.darkGray);	// Background Color (more important cause visible one)
		Windows.setVisible(true);				// set panel visible to true
		Windows.setLayout(null);					// set Panel layout to null  (not the best choice, but it work for small project)
				
		// Buttons Settings
		option1.setBounds(20,120,200,20);	// x,y,width,height 
		
		
		// Title Label Settings		
		dialogText.setBounds(20,50,200,20);				// x,y,width,height 
		dialogText.setVerticalAlignment(JLabel.TOP);	// set vertical alignment 
		dialogText.setForeground(Color.WHITE);			// set Text color 
							
		// add items to panel
		Windows.add(dialogText); // add title label
		Windows.add(option1);	// add Button 1			
	     
	    option1.addActionListener(new ActionListener(){ 	// button action listener (on button1 click event)
	        public void actionPerformed(ActionEvent e){  	//	               
	                try {	        		     		
					// Save current place in game
					String Path = "Save.txt";
					FileWriter myWriter = new FileWriter(Path); 	
					myWriter.write(_Character_Speaking+"\n"+_Dialog_number_path);												
					myWriter.close();  																				
					
					} catch (IOException e1) {
					
					
					return;
					}
	        }  
	    });         
	    ESCWindow.add(Windows); // add Main Menu Panel with all items to Main Frame
	    ESCWindow.setFocusable(true);
	    ESCWindow.addKeyListener(new KeyListener() {
			
			public void keyReleased(KeyEvent evt) {
				  char ch = evt.getKeyChar();
				  System.out.print(ch);
				  if(ch=='') {
					  if(!ESC_MENU_STATUS) {						   
						  System.out.print("turn on esc menu");
						  ESCWindow.setVisible(true);
					  }
					  if(ESC_MENU_STATUS) {
						  ESCWindow.setVisible(false);
						  System.out.print("turn off esc menu");
					  }
					  ESC_MENU_STATUS = !ESC_MENU_STATUS;
					 
				  }
		    }
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	    ESCWindow.requestFocus();
	    
		
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
		Window.setVisible(false);		
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
		
		option1.setFocusable(false);
		option2.setFocusable(false);
		option3.setFocusable(false);
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
		Window.setFocusable(true);
		Window.addKeyListener(new KeyListener() {
			
			public void keyReleased(KeyEvent evt) {
				  char ch = evt.getKeyChar();
				  System.out.print(ch);
				  if(ch=='') {
					  if(!ESC_MENU_STATUS) {						   
						  System.out.print("turn on esc menu");
						  ESCWindow.setVisible(true);
					  }
					  if(ESC_MENU_STATUS) {
						  ESCWindow.setVisible(false);
						  System.out.print("turn off esc menu");
					  }
					  ESC_MENU_STATUS = !ESC_MENU_STATUS;
					 
				  }
		    }
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		Window.requestFocus();
		
		
	}
	
public static void MainMenu() {
		
		JFrame MainMenuFrame = new JFrame();
	
		JLabel dialogText = new JLabel("<html><b>Main Menu</b></html>",JLabel.CENTER); // Title Label
		
		JButton option1 = new JButton("Start"); // Create Start Button	
		JButton option2 = new JButton("exit"); // Create End Button	
		JButton option3 = new JButton("continue"); // Create End Button	
		
		JPanel Windows = new JPanel(); // set new Panel for Main Menu  
		
		// Main Menu Frame settings	
		MainMenuFrame.setBounds(1,1,240,300); 							// x,y,width,height
		MainMenuFrame.setBackground(Color.darkGray);					// main frame Background Color
		MainMenuFrame.setResizable(false); 								// turn off Resize option
		MainMenuFrame.setVisible(true);									// set Visible on start 
		MainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Default Close Operation
		
		// Main Menu Panel Settings
		Windows.setBounds(1,1 ,240, 300); 		// x,y,width,height  
		Windows.setBackground(Color.darkGray);	// Background Color (more important cause visible one)
		Windows.setVisible(true);				// set panel visible to true
		Windows.setLayout(null);					// set Panel layout to null  (not the best choice, but it work for small project)
				
		// Buttons Settings
		option1.setBounds(20,120,200,20);	// x,y,width,height 
		option3.setBounds(20,180,200,20);
		option2.setBounds(20,300-80,200,20);
		
		// Title Label Settings		
		dialogText.setBounds(20,50,200,20);				// x,y,width,height 
		dialogText.setVerticalAlignment(JLabel.TOP);	// set vertical alignment 
		dialogText.setForeground(Color.WHITE);			// set Text color 
							
		// add items to panel
		Windows.add(dialogText); // add title label
		Windows.add(option1);	// add Button 1	
		Windows.add(option2);
		Windows.add(option3);
	     
	    option1.addActionListener(new ActionListener(){ 	// button action listener (on button1 click event)
	        public void actionPerformed(ActionEvent e){  	//
	                MainMenuFrame.setVisible(false);			// when button 1 clicked set Main Menu to not visible
	                Window.setVisible(true);	// when button 1 clicked set Character Creating Window to visible
	        }  
	    }); 
	    option3.addActionListener(new ActionListener(){ 	// button action listener (on button1 click event)
	        public void actionPerformed(ActionEvent e){  	//
	                MainMenuFrame.setVisible(false);			// when button 1 clicked set Main Menu to not visible
	                
	                BufferedReader reader;
	                int i = 0;
	        		// read Dialog Text
	        		try {
	        			reader = new BufferedReader(new FileReader("Save.txt"));
	        			String line = reader.readLine();
	        			while (line != null) {				
	        				i++;
	        				if(i==1) {
	        					_Character_Speaking = "";
	        					_Character_Speaking = line;	        					
	        				
	        				}
	        				if(i==2) {
	        					number = line;	   
	        					_Dialog_number_path = number;	        					
	        				}
	        				// read next line
	        				line = reader.readLine();
	        			}
	        			reader.close();
	        			line = "";
	        			_Option_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Option"; 
	    				_Dialog_Path = "./MainDialogTree/"+_Character_Speaking+"/Dialog_"+_Dialog_number_path+"/Dialog.txt";
	        			read(_Option_Path,_Dialog_Path); 
	        			Show();
	        		} catch (IOException e2) {
	        			e2.printStackTrace();
	        		}	                
	                
	                
	                
	                
	                Window.setVisible(true);	// when button 1 clicked set Character Creating Window to visible
	        }  
	    }); 
	    
	    MainMenuFrame.add(Windows); // add Main Menu Panel with all items to Main Frame
	   
	    
		
	}

	




	public static void main(String[] args){	
		MainMenu();
		ESCMenu();
		game();				
	}
	
	public void keyReleased(KeyEvent evt) {
		 
    }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    

}

