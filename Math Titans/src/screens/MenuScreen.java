package screens;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import game.Game;

public class MenuScreen{
	private Game game;
	private JLabel mainLabel;
	private JButton bt_begin;
	private JButton bt_highscores;
	private JButton bt_about;
	private JButton bt_load; //TODO adicionar opção de carregar jogo

	public MenuScreen(Game main_frame){
		this.game = main_frame;
		
		this.mainLabel = new JLabel();
		this.mainLabel.setIcon(new ImageIcon("arquivos/backgrounds/menus/menu.png"));
		this.mainLabel.setBounds(0,-20,400,700);
		this.mainLabel.setVisible(true);
		
		this.bt_begin = new JButton();
		this.bt_begin.setBackground(new Color(0,0,0,0));
		this.bt_begin.setBorder(null);
		this.bt_begin.setContentAreaFilled(false);
		this.bt_begin.setBounds(255,535,120,40);
		this.bt_begin.addActionListener(main_frame);
		
		this.bt_highscores = new JButton();
		this.bt_highscores.setBackground(new Color(0,0,0,0));
		this.bt_highscores.setBorder(null);
		this.bt_highscores.setContentAreaFilled(false);
		this.bt_highscores.setBounds(18,563,192,39);
		this.bt_highscores.addActionListener(main_frame);
		
		this.bt_about = new JButton();
		this.bt_about.setBackground(new Color(0,0,0,0));
		this.bt_about.setBorder(null);
		this.bt_about.setContentAreaFilled(false);
		this.bt_about.setBounds(254,591,122,39);
		this.bt_about.addActionListener(main_frame);
		
		this.bt_load = new JButton();
		this.bt_load.setBackground(new Color(0,0,0,0));
		this.bt_load.setBorder(null);
		this.bt_load.setContentAreaFilled(false);
		this.bt_load.setBounds(18,619,192,39);
		this.bt_load.addActionListener(main_frame);
	}
	
	public JButton getBeginButton(){
		return bt_begin;
	}
	
	public JButton getAboutButton(){
		return bt_about;
	}
	
	public JButton getHighscoresButton(){
		return bt_highscores;
	}

	public JButton getLoadButton(){
		return bt_load;
	}

	public void show(){
		this.game.getMainFrame().getContentPane().removeAll();
		this.game.getMainFrame().add(bt_begin);
		this.game.getMainFrame().add(bt_highscores);
		this.game.getMainFrame().add(bt_about);
		this.game.getMainFrame().add(bt_load);
		this.game.getMainFrame().add(mainLabel);
		this.game.getMainFrame().getContentPane().repaint();
	}
}
