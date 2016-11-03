package screens;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import game.Game;

public class AboutScreen{
	private Game game;
	private JLabel mainLabel;
	private JButton bt_back;
	
	public AboutScreen(Game game){
		this.game = game;
		this.mainLabel = new JLabel();
		this.mainLabel.setIcon(new ImageIcon("arquivos/backgrounds/menus/sobre.png"));
		this.mainLabel.setLayout(null);
		this.mainLabel.setBounds(0,0,400,700);
		this.mainLabel.setVisible(true);
		
		this.bt_back = new JButton();
		this.bt_back.setBounds(6,6,49,49);
		this.bt_back.setBorder(null);
		this.bt_back.setContentAreaFilled(false);
		this.bt_back.setOpaque(false);
		this.bt_back.addActionListener(game);
	}
	
	public JButton getBackButton(){
		return bt_back;
	}

	public void show(){
		game.getMainFrame().getContentPane().removeAll();
		game.getMainFrame().add(bt_back);
		game.getMainFrame().add(mainLabel);
		game.getMainFrame().getContentPane().repaint();
	}
}
