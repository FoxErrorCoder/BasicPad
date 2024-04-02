package BasicPad;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class BasicPad extends JFrame {
	
	String percorsoFile; //Tiene conto di dove si trova il file
	String nomeFile = "new file"; //Il nome del default è new file e poi cambia a seconda del nome vero
	String Versione = "BasicPad 0.7";//Stringa versione per rendere il codice più modulare e cambiare i titoli più facilmente
	FileWriter fw = null ; //Fw è uno scrivi file che è impostato a null quindi non scrive niente all'inizio
	PrintWriter fileOutput = null ; //Anche l'output è null
	File Percorso;//percorso di dove si trova il file
	int CurrentLanguage = 1;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Qui avvia l'app.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicPad frame = new BasicPad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Qui si crea l'interfaccia.
	 */
	public BasicPad() {
		
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(BasicPad.class.getResource("/BasicPad/Icon x16.png")));
		setTitle(Versione + " - " + "new file");//Qui imposta il titolo della finestra
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Imposta di di chiudere la finestra
		setBounds(100, 100, 300, 300);//Qui ci sono le grandezze della finestra
		
		//Qui sono dichiarati gli elementi grafici che sono usati
		
		JMenuBar menuBar = new JMenuBar(); //Barra dei menu 
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setForeground(new Color(0, 0, 0));
		getContentPane().add(menuBar, BorderLayout.NORTH);//La barra dei menu è impostata a Nord fissa
		
		//Sezione File
		
		JMenu File = new JMenu("File"); //Nuovo la nuova sezione file nella barra dei menu
		menuBar.add(File);//Aggiugi l'oggetto file alla barra dei menu
		
		JMenuItem NewFile = new JMenuItem("New File"); //Dichiara il JMenu NewFile
		NewFile.setIcon(new ImageIcon(BasicPad.class.getResource("/BasicPad/New_File  x16.png"))); //Imposta l'icona dell'opzione
		NewFile.setHorizontalAlignment(SwingConstants.LEFT); // Imposta l'orientamento a sinistra
		File.add(NewFile);//Aggiunge nel menu File l'opzione New File
		
		JMenuItem OpenFile = new JMenuItem("Open file"); //Dichiara il JMenu Open File
		OpenFile.setIcon(new ImageIcon(BasicPad.class.getResource("/BasicPad/Open_File x16.png")));//Imposta l'icona dell'opzione
		OpenFile.setHorizontalAlignment(SwingConstants.LEFT);// Imposta l'orientamento a sinistra
		File.add(OpenFile);//Aggiunge nel menu File l'opzione Open File
		
		JMenuItem Save = new JMenuItem("Save file");//Dichiara il JMenu Save File
		Save.setIcon(new ImageIcon(BasicPad.class.getResource("/BasicPad/Save x16.png")));//Imposta l'icona dell'opzione
		Save.setHorizontalAlignment(SwingConstants.LEFT);// Imposta l'orientamento a sinistra
		File.add(Save);//Aggiunge nel menu File l'opzione Save
		
		JMenuItem SaveAs = new JMenuItem("Save as");//Dichiara il JMenu Save AS
		SaveAs.setIcon(new ImageIcon(BasicPad.class.getResource("/BasicPad/Save_As x16.png")));//Imposta l'icona dell'opzione
		SaveAs.setHorizontalAlignment(SwingConstants.LEFT);// Imposta l'orientamento a sinistra
		File.add(SaveAs);//Aggiunge nel menu File l'opzione Save AS
		
		
		JMenuItem Quit = new JMenuItem("Quit");//Dichiara il JMenu Quit
		Quit.setHorizontalAlignment(SwingConstants.LEFT);// Imposta l'orientamento a sinistra
		Quit.setIcon(new ImageIcon(BasicPad.class.getResource("/BasicPad/Quit x16.png")));//Imposta l'icona dell'opzione
		File.add(Quit);//Aggiunge nel menu File l'opzione Quit
		
		//Sezione View
		
		JMenu View = new JMenu("View");//Nuovo la nuova sezione View nella barra dei menu
		menuBar.add(View);//Aggiugi l'oggetto View alla barra dei menu
		
		JLabel ZoomCont = new JLabel("               Zoom - 20%");//Crea l'etichetta ZoomCont che serve solo per visualizzare lo zoom impostato e gli spazi sono per allineamento
		ZoomCont.setHorizontalAlignment(SwingConstants.CENTER);//Imposta l'orientamento al centro
		View.add(ZoomCont);//Aggiunge il contatore come il primo elemento della sezione View
		
		JSlider Zoom = new JSlider();//Crea Zoom come uno slider
		View.add(Zoom);//Aggiunge lo slider sotto a ZoomCont
		Zoom.setValue(20);
		
		//Sezione Theme
		
		JMenu Theme = new JMenu("Theme");//Crea la sezione Themes
		menuBar.add(Theme);//lo aggiunge alla menubar
		
		JMenuItem Dark = new JMenuItem("Dark");//Dichiara il bottone Dark
		Dark.setHorizontalAlignment(SwingConstants.LEFT);//Imposta l'allineamento
		Theme.add(Dark);//Aggiunge il bottone
		
		JMenuItem Light = new JMenuItem("Light");// Dichiara il bottono Light
		Light.setHorizontalAlignment(SwingConstants.LEFT);//Imposta l'allineamento
		Theme.add(Light);//Aggiunge il bottone
		
		//Sezione Help
		
		JMenu help = new JMenu("Help"); //Dichiara il bottone nel menu nominato Help
		menuBar.add(help);//Aggiunge la sezione
		
		JMenuItem Language = new JMenuItem("Select Language");
		help.add(Language);
		
		JMenuItem About = new JMenuItem("About"); // Crea il pulsante About
		help.add(About);
		
		JTextArea textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.WEST);
		
		//La casella di testo principale
		
		JEditorPane Text = new JEditorPane(); // Viene dichiarata come casella di testo nominata Text
		Text.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Imposta il font e la grandezza
		Text.setText("");// Il testo di default è vuoto
		getContentPane().add(Text, BorderLayout.CENTER);
		
		// Azione per i tasti
		//Qui sono tutte le interazioni che sono eseguite per ogni elemento
		/*
		 Gli elementi sono:
		 Sezione File:
		 NewFile() Bottone
		 OpenFile() Bottone
		 SaveFile() Bottone
		 SaveAs() Bottone
		 Quit() Bottone
		 
		 Sezione View:
		 ZoomCont() JLabel
		 Zoom() Slider
		 
		 Sezione Theme:
		 Light() Bottone
		 Dark() Bottone
		 
		 Sezione Help:
		 About() Bottone
		 
		 Altri elementi:
		 Text() = Casella di test
		 ScroOr() Jscrolbar aka scorrimento orizzontale
		 ScroVert() Jscrolbar aka scorrimento verticale
		  		 
		 */
		
		//Tasto nuovo file
		NewFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BasicPad newInterfaccia = new BasicPad(); // Creazione di una nuova istanza di Interfaccia
		        newInterfaccia.setVisible(true); // Mostra la nuova finestra
			}
		});
		
		//Qui c'è la funzione apri file
		OpenFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {//se il pulsante è cliccato
				JFileChooser fileChooser = new JFileChooser();//usa la funzione per scegliere il file
		        int result = fileChooser.showOpenDialog(BasicPad.this);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        reader.close();
                        Text.setText(content.toString());
                        nomeFile = selectedFile.getName();
                        setTitle(Versione + " - " + nomeFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
		        }
				Percorso = fileChooser.getSelectedFile();
			}
		});

		Save.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(nomeFile == "new file") 
            	{
            		JFileChooser fileChooser = new JFileChooser();
    		        int result = fileChooser.showSaveDialog(BasicPad.this);
    		        if (result == JFileChooser.APPROVE_OPTION) {
    		            Percorso = fileChooser.getSelectedFile();
    		            salvaFile(Text, Percorso);
    		            nomeFile = Percorso.getName();
    		            setTitle(Versione + " - " + nomeFile);
    		        }
            	}
            	else 
            	{
            		salvaFile(Text, Percorso);
            	}
			}
		});
		
		SaveAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showSaveDialog(BasicPad.this);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            Percorso = fileChooser.getSelectedFile();
		            salvaFile(Text, Percorso);
		            nomeFile = Percorso.getName();
		            setTitle(Versione + " - " + nomeFile);
		        }
			}
		});

		//Chiudi senza salvare
		Quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//Qui la scritta he indica lo zoom viene aggiornata se il mouse viene trascinato
		Zoom.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				ZoomCont.setText("               Zoom - " + Zoom.getValue() + "%"); // imposta il valore
				Font font = new Font("Tahoma", Font.PLAIN, Zoom.getValue());//cambia la grandezza a seconda del valore dello slider
				Text.setFont(font);
			}
		});
		
		//Di default lo zoom è di 20
		Zoom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {//Quando il mouse è rilasciato
				Font font = new Font("Tahoma", Font.PLAIN, Zoom.getValue());//cambia la grandezza a seconda del valore dello slider
				Text.setFont(font);
				ZoomCont.setText("               Zoom - " + Zoom.getValue() + "%");
			}
		});
		
		Dark.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Text.setBackground(Color.BLACK);
				Text.setForeground(Color.WHITE);
				menuBar.setBackground(Color.BLACK);
				menuBar.setForeground(Color.WHITE);			
			}
		});
		
		//Cambia i colori per applicare il tema
		Light.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Text.setBackground(Color.WHITE);
				Text.setForeground(Color.BLACK);
				menuBar.setBackground(Color.WHITE);
				menuBar.setForeground(Color.BLACK);
			}
		});

		About.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {//Se premuto il tasto about mostra un messagio infomazione
				JOptionPane.showMessageDialog(null, "A Simple notepad program made by FoxErrorCoder");
			}
			
		});
		
		// dialogo di chiusura
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
		        if(Text.getText() != "") {
		            int i = dialogo("Yes","No","Cancel","Do you want to save the current file?","","New changes made to " + nomeFile);
		            switch(i) {// qui sopra viene chiesto all'utente cosa vuole fare
		                case 0://se si vuole salvare si controlla se il file è nuovo
		                	if(nomeFile == "new file") //e se è un file nuovo si esegue un salva con nome
		                	{
		                		JFileChooser fileChooser = new JFileChooser();
		        		        int result = fileChooser.showSaveDialog(BasicPad.this);
		        		        if (result == JFileChooser.APPROVE_OPTION) {
		        		            Percorso = fileChooser.getSelectedFile();
		        		            salvaFile(Text, Percorso);
		        		            nomeFile = Percorso.getName();
		        		            setTitle(Versione + " - " + nomeFile);
		        		        }
		                	}
		                	else // se è un file gia salvato precedentemente viene salvato dove era gia
		                	{
		                		salvaFile(Text, Percorso);
		                	}
		                    dispose(); // Chiudi solo la finestra corrente
		                    break;
		                case 1:
		                    dispose();
		                    break;
		                case 2:
		                    try {
		                        Empty();
		                    } catch (InterruptedException | IOException e1) {
		                        e1.printStackTrace();
		                    }
		                    break;
		                default:
		            }
		        } else {
		            dispose(); // Chiudi solo la finestra corrente se non ci sono modifiche
		        }
			}
		});
		//Modifica titolo per file nuovi
		Text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});		
	}
	
	//Questa funzione salva il file ne percorso attuale
	private void salvaFile(JEditorPane text, File Path) {
        try {
            FileWriter fw = new FileWriter(Path);
            fw.write(text.getText());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//Questa è una funzione che non fa nulla è usata per impedire la chiusura del programma in caso che si voglia annullare l'operazione di chiusura
	private void Empty() throws InterruptedException, IOException{
		fw.wait();
    } 
	
	public int dialogo (String opzione1,String opzione2,String opzione3, String messaggio, String messaggioAggiuntivo, String titolo) 
	{
		Object[] options = {opzione1,opzione2,opzione3};
		int n = JOptionPane.showOptionDialog(null,
		messaggio
		+ messaggioAggiuntivo,
		titolo,
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		options,
		options[2]);
		return n;
	}
}