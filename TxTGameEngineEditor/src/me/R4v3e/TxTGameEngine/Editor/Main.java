package me.R4v3e.TxTGameEngine.Editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main implements ActionListener{	
	
	static JFrame MainFrame = new JFrame("MainMenu"); 					// Frame for Main Menu Window
	static JFrame CreateCharacterFrame = new JFrame("CreateCharacter"); // Frame for Character Creating Window
	
	/*
	 * Main Menu Window
	 * */
	public static void MainMenu() {
		
		JLabel dialogText = new JLabel("<html><b>Main Menu</b></html>",JLabel.CENTER); // Title Label
		
		JButton option1 = new JButton("New Character"); // Create Character Button
		JButton option2 = new JButton("New Dialog"); 	// Create Dialog Button 
		JButton option3 = new JButton("Change Dialog"); // Change Dialog Button
	

		JPanel Window = new JPanel(); // set new Panel for Main Menu  
		
		// Main Menu Frame settings	
		MainFrame.setBounds(1,1,240,300); 							// x,y,width,height (px)
		MainFrame.setBackground(Color.darkGray);					// main frame Background Color
		MainFrame.setResizable(false); 								// turn off Resize option
		MainFrame.setVisible(true);									// set Visible on start 
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	// Default Close Operation
		
		// Main Menu Panel Settings
		Window.setBounds(1,1 ,240, 300); 		// x,y,width,height  
		Window.setBackground(Color.darkGray);	// Background Color (more important cause visible one)
		Window.setVisible(true);				// set panel visible to true
		Window.setLayout(null);					// set Panel layout to null  (not the best choice, but it work for small project)
				
		// Buttons Settings
		option1.setBounds(20,120,200,20);	// x,y,width,height 
		option2.setBounds(20,150,200,20);	// x,y,width,height 
		option3.setBounds(20,180,200,20);	// x,y,width,height 	
		
		// Title Label Settings		
		dialogText.setBounds(20,50,200,20);				// x,y,width,height 
		dialogText.setVerticalAlignment(JLabel.TOP);	// set vertical alignment 
		dialogText.setForeground(Color.WHITE);			// set Text color 
							
		// add items to panel
		Window.add(dialogText); // add title label
		Window.add(option1);	// add Button 1	
		Window.add(option2);	// add Button 2
		Window.add(option3);	// add Button 3
		
	     
	    option1.addActionListener(new ActionListener(){ 	// button action listener (on button1 click event)
	        public void actionPerformed(ActionEvent e){  	//
	                MainFrame.setVisible(false);			// when button 1 clicked set Main Menu to not visible
	                CreateCharacterFrame.setVisible(true);	// when button 1 clicked set Character Creating Window to visible
	        }  
	    }); 
	    option2.addActionListener(new ActionListener(){  	// button action listener (on button2 click event)
		    public void actionPerformed(ActionEvent e){  
	                MainFrame.setVisible(false);			// when button 2 clicked set Main Menu to not visible
	                										// 
	        }  
	    });  
	    option3.addActionListener(new ActionListener(){ 	// button action listener (on button3 click event)
	    	public void actionPerformed(ActionEvent e){  
		            MainFrame.setVisible(false);			// when button 3 clicked set Main Menu to not visible
		            										//
		    }  
		});  
	    
		MainFrame.add(Window); // add Main Menu Panel with all items to Main Frame
		
	}
	
	/*
	 * Character Creation Window
	 * */
	public static void Window_CreateCharacter() {
		
		JPanel MainPanel = new JPanel();	// Character Creation Main Panel
		
		JLabel Title = new JLabel("<html><b>Create Character</b></html>",JLabel.CENTER); 	// Window Title Label
		JLabel Name = new JLabel("Character Name: ",JLabel.LEFT);							// Name Label
		JLabel ImagePath = new JLabel("Character Image Path: ",JLabel.LEFT);				// Image Path Label
		JLabel Description = new JLabel("Character Description: ",JLabel.LEFT); 			// Description Label
		JLabel ErrorInfo = new JLabel(" ");													// Error/Status Label
		
		JTextField CharacterName = new JTextField();		// Character Name one line Text Field 
		JTextField CharacterImagePath = new JTextField(); 	// Image Path one line Text Field 
		
		JTextArea CharacterDescription = new JTextArea("Description");	// Description Multiple lines Text Area
		
		JButton Menu = new JButton("Back To Main Menu");	// back to Menu Button
		JButton Create = new JButton("Create Character");	// Create Character Button
		
		// Character Creation Frame Settings
		CreateCharacterFrame.setBounds(1,1 ,600, 500); 							// x,y,width,height
		CreateCharacterFrame.setBackground(Color.darkGray);						// Background Color
		CreateCharacterFrame.setResizable(false);								// Turn off Resize option
		CreateCharacterFrame.setVisible(false);									// not Visible on start
		CreateCharacterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// Default close operation
		
		// Character Creation Window Panel Settings
		MainPanel.setBounds(1,1,600,500);		// x,y,width,height
		MainPanel.setBackground(Color.GRAY);	// Background Color (more important)
		MainPanel.setVisible(true);				// Visible from start (only when frame is visible)
		MainPanel.setLayout(null);				// Set Layout to null (again it work cause small project, better avoid in future)
						 
		// Title Label Settings
		Title.setBounds(200,10,200,20);			// x,y,width,height
		Title.setVerticalAlignment(JLabel.TOP);	// Vertical Alignment Top
		Title.setForeground(Color.WHITE);		// Text color White
		
		// Name Label Settings
		Name.setBounds(50,51,125,20);			// x,y,width,height
		Name.setVerticalAlignment(JLabel.TOP);	// Vertical Alignment Top
		Name.setForeground(Color.WHITE);		// Text Color White
		
		//Image Path Label Settings
		ImagePath.setBounds(50,77,175,20);			// x,y,width,height
		ImagePath.setVerticalAlignment(JLabel.TOP);	// Vertical Alignment Top
		ImagePath.setForeground(Color.WHITE);		// Text Color White
		
		//Description Label Settings
		Description.setBounds(50,102,175,20);			// x,y,width,height
		Description.setVerticalAlignment(JLabel.TOP);	// Vertical Alignment Top
		Description.setForeground(Color.WHITE);			// Text Color White
		
		//Character Name TextField Settings
		CharacterName.setBounds(175,50,375,20);		// x,y,width,height
		CharacterName.setForeground(Color.BLACK);	// Text Color Black
		
		//Image Path TextField Settings 
		CharacterImagePath.setBounds(225,75,325,20);	// x,y,width,height
		CharacterImagePath.setForeground(Color.BLACK);	// Text Color Black
		
		// Description TextArea settings
		CharacterDescription.setBounds(225,100,325,200);	// x,y,width,height
		CharacterDescription.setForeground(Color.BLACK);	// Text Color Black
		
		// Buttons Settings
		Menu.setBounds(50,320,170,20);		// Back to Menu x,y,width,height
		Create.setBounds(380,320,170,20);	// Create Character x,y,width,height
		
		// Error/Status Label Settings
		ErrorInfo.setBounds(250,400,505,20);		// x,y,width,height
		ErrorInfo.setVerticalAlignment(JLabel.TOP);	// Vertical Alignment Top
		ErrorInfo.setForeground(Color.WHITE);		// Text Color White
		
		// Add to character creation window panel
		MainPanel.add(Title);					// add Title Label
		MainPanel.add(Name);					// add Name Label
		MainPanel.add(CharacterName);			// add Character Name TextField
		MainPanel.add(ImagePath);				// add Image Path Label
		MainPanel.add(CharacterImagePath);		// add Image Path TextField
		MainPanel.add(Description);				// add Description Label
		MainPanel.add(CharacterDescription);	// add Description TextArea
		MainPanel.add(Menu);					// add Menu Button
		MainPanel.add(Create);					// add Create Character Button
		MainPanel.add(ErrorInfo);				// add Error/Status Label
		
		CreateCharacterFrame.add(MainPanel);	// add Character creation MainPanel to Character Creation Frame 
		
		// Menu Button Action Listener
		Menu.addActionListener(new ActionListener(){  
	        public void actionPerformed(ActionEvent e){  
	                MainFrame.setVisible(true);				// set Main Frame to visible
	                CreateCharacterFrame.setVisible(false);	// set Character Creation Frame to not visible
	        }  
	    }); 
		// Create Button Action Listener
		Create.addActionListener(new ActionListener(){  
			
	        public void actionPerformed(ActionEvent e){  
	        	String name = CharacterName.getText(); // get character name and save in string "name" variable
	        	if (!(Files.isDirectory(Paths.get("MainDialogTree/" + name))) && name!=null) { // do if character not exist and name not empty
	        		
	        		try {
						Files.createDirectories(Paths.get("MainDialogTree/" + name)); 			// try to create character 
					} catch (IOException e1) {						
						ErrorInfo.setText("Error Occured / Can't Create Character Folder."); 	// if error change error label text
						return;
					}
	        		
	        		String filepath = CharacterImagePath.getText(); // get image path and save in string "filepath" variable
	        	
	        		String NewImagePath = "MainDialogTree/"+name; 	// save new image path to string variable 
	        		
	        		try {
	        	    	Files.copy(Paths.get(filepath), Paths.get(NewImagePath+"/image.jpg"), StandardCopyOption.REPLACE_EXISTING); // try to copy image (will happen only when character folder created)
					} catch (IOException e1) {						
						File index = new File("MainDialogTree/" + name);    	  // when error with image occur get created character folder path
						if (index.exists()) {									  // if folder was created  
							index.delete();										  // delete folder
						}					
						ErrorInfo.setText("Error Occured / Wrong Path to Image."); // set Error Label Text to error info (wrong Path to image / can't copy image)
						return;
					}
	        		
	        	    try {
	        	    	File myObj = new File("MainDialogTree/" + name + "/Description.txt"); 						// try to create Description.txt for created character
	        	        if (myObj.createNewFile()) {
	        	        	  FileWriter myWriter = new FileWriter("MainDialogTree/" + name + "/Description.txt");	// if description created crate file writer 
	        	        	  myWriter.write(CharacterDescription.getText());										// write Description Text Area Text to Description.txt
	        	              myWriter.close();	        	        	  											// close File Writer
	        	        	  
	        	        } 
	        	      } catch (IOException e1) {
	        	        System.out.println("An error occurred.");							// if can't create Description.txt
	        	        ErrorInfo.setText("Error Occured / Can't Create Description.");		// set Error Label to Error Info
						return;
	        	      }
	        	    ErrorInfo.setText("Character: " + name + " Created");	// if no errors set Error/Status Label Text to information that character was created;
	        		}else ErrorInfo.setText("Error Occured / Character Folder Already Exist"); // if Character folder already exist set error label to Character folder already exist
	        	
	        }  
	    }); 
		
	}

	/*
	 * Crate Dialog Window
	 */
	public static void Window_CreateDialog() {
		// TO DO NEXT
	}
	
	public static void main(String[] args) {
		
		MainMenu(); 				// start Main Menu
		Window_CreateCharacter();	// Start Window Create Character
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		// not needed
		
	}

}
