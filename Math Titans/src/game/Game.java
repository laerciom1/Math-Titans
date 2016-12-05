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
import screens.FinalScreen;
import screens.GameScreen;
import screens.HighscoreScreen;
//import screens.HighscoresScreen;
import screens.LoadScreen;
import screens.MenuScreen;
import screens.OverScreen;

public class Game implements ActionListener{
	
	private static JFrame main_frame = new JFrame(".: Math Titans :.");
	private GameScreen game_screen;
	private LoadScreen load_screen;
	private MenuScreen menu_screen;
	private AboutScreen about_screen;
	private FinalScreen final_screen;
	private OverScreen over_screen;
	private HighscoreScreen hs_screen;
	//private HighscoresScreen highscores_screen;
	private GameSave save;

	public Game(){
		this.save = new GameSave();
		this.menu_screen = new MenuScreen(this);
		this.about_screen = new AboutScreen(this);
		this.load_screen = new LoadScreen(this);
		this.final_screen = new FinalScreen(this);
		this.over_screen = new OverScreen(this);
		this.hs_screen = new HighscoreScreen(this);
		this.game_screen = new GameScreen(this);
		//this.highscores_screen = new HighscoresScreen(this);
		main_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		main_frame.setLayout(null);
		main_frame.setResizable(false);
		main_frame.setLocationRelativeTo(null);
		main_frame.setBounds(1000, 100, 400, 700);
	}

	public MenuScreen getMenu_screen(){
		return menu_screen;
	}
	
	public void setMenu_screen(MenuScreen menu_screen){
		this.menu_screen = menu_screen;
	}
	
	public FinalScreen getFinal_screen(){
		return final_screen;
	}

	public void setFinal_screen(FinalScreen final_screen){
		this.final_screen = final_screen;
	}

	public OverScreen getOver_screen(){
		return over_screen;
	}

	public void setOver_screen(OverScreen over_screen){
		this.over_screen = over_screen;
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
			game_screen.setRunning(true);
			game_screen.start();
		}
		
		if((JButton) obj == menu_screen.getHighscoresButton()){
			hs_screen.show();
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
		if((JButton) obj == hs_screen.getBackButton()){
			menu_screen.show();
		}
		
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
		
		/*
		 * Botao Final Screen
		 */
		if((JButton) obj == final_screen.getBackButton()){
			menu_screen.show();
		}
		
		/*
		 * Botao Over Screen
		 */
		if((JButton) obj == over_screen.getBackButton()){
			menu_screen.show();
		}
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException{
		Game game = new Game();
		game.start();	
	}
}
