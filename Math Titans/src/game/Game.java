package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import domain.GameSave;
import screens.AboutScreen;
import screens.GameScreen;
//import screens.HighscoresScreen;
import screens.LoadScreen;
import screens.MenuScreen;

public class Game implements ActionListener{
	
	private static JFrame main_frame = new JFrame(".: Math Titans :.");
	private GameScreen game_screen;
	private LoadScreen load_screen;
	private MenuScreen menu_screen;
	private AboutScreen about_screen;
	//private HighscoresScreen highscores_screen;
	private GameSave save;

	public Game(){
		this.save = new GameSave();
		this.menu_screen = new MenuScreen(this);
		this.about_screen = new AboutScreen(this);
		this.load_screen = new LoadScreen(this);
		//this.highscores_screen = new HighscoresScreen(this);
		main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		main_frame.setLayout(null);
		main_frame.setResizable(false);
		main_frame.setLocationRelativeTo(null);
		main_frame.setBounds(0, 0, 400, 700);
	}
	
	public JFrame getMainFrame(){
		return main_frame;
	}
	
	public GameSave getGameSave(){
		return save;
	}
	
	public void start(){
		menu_screen.show();
		main_frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object obj = evt.getSource();
		
		/**
		 * Botões da Menu Screen
		 */
		if((JButton) obj == menu_screen.getBeginButton()){
			game_screen = new GameScreen(this);
			game_screen.start();
		}
		
		if((JButton) obj == menu_screen.getHighscoresButton()){
			System.out.println("hs");
		}
		
		if((JButton) obj == menu_screen.getAboutButton()){
			about_screen.show();
		}
		
		if((JButton) obj == menu_screen.getLoadButton()){
			load_screen.show();
		}
		
		/**
		 * Botões da About Screen
		 */
		if((JButton) obj == about_screen.getBackButton()){
			menu_screen.show();
		}
		
		/**
		 * Botões Highscores Screen
		 */
		
		/**
		 * Botões Load Screen
		 */
		if((JButton) obj == load_screen.getBackButton()){
			menu_screen.show();
		}
		if((JButton) obj == load_screen.getLoadButton()){
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File("arquivos/saves"));
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de texto", "txt");
			fc.addChoosableFileFilter(filter);
			int everything_is_ok = fc.showOpenDialog(null);
			if(everything_is_ok == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				try{
					this.save = new GameSave(file.getAbsolutePath());
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			load_screen.show();
		}
		/**
		 * Botões Game Screen
		 */
		if((JButton) obj == game_screen.getBackButton()){
			game_screen.setRunning(false);
			menu_screen.show();
		}
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException{
		Game game = new Game();
		game.start();	
	}
}
