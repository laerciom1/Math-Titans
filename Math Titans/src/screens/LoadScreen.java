package screens;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import game.Game;

public class LoadScreen{
	private Game game;
	private JLabel mainLabel;
	private JButton bt_back;
	private JButton bt_load; //TODO adicionar opção de carregar jogo

	public LoadScreen(Game main_frame){
		this.game = main_frame;
		
		this.mainLabel = new JLabel();
		this.mainLabel.setIcon(new ImageIcon("arquivos/backgrounds/menus/load.png"));
		this.mainLabel.setBounds(0,0,400,700);
		this.mainLabel.setVisible(true);
		
		this.bt_back = new JButton();
		this.bt_back.setBounds(6,6,49,49);
		this.bt_back.setBorder(null);
		this.bt_back.setContentAreaFilled(false);
		this.bt_back.setOpaque(false);
		this.bt_back.addActionListener(main_frame);
				
		this.bt_load = new JButton();
		this.bt_load.setBounds(73,611,253,40);
		this.bt_load.setBackground(new Color(0,0,0,0));
		this.bt_load.setBorder(null);
		this.bt_load.setContentAreaFilled(false);
		this.bt_load.addActionListener(main_frame);
	}
	
	public JButton getBackButton(){
		return bt_back;
	}

	public JButton getLoadButton(){
		return bt_load;
	}

	public void show(){	
		this.game.getMainFrame().getContentPane().removeAll();
		char[] level = game.getGameSave().getStrLevel();
		char[] coins = game.getGameSave().getStrCoins();
		
		int level_lenght = 0;
		JLabel[] label_level = new JLabel[level.length];
		int coins_lenght = 0;
		JLabel[] label_coins = new JLabel[coins.length];
		
		int x_padding;
		
		for(int i = 0; i < level.length; i++){
			switch(level[i]){
				case '0':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/0.png"));
					break;
				case '1':
					level_lenght += 26+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos//numbers//1.png"));
					break;
				case '2':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/2.png"));
					break;
				case '3':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/3.png"));
					break;
				case '4':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/4.png"));
					break;
				case '5':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/5.png"));
					break;
				case '6':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/6.png"));
					break;
				case '7':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/7.png"));
					break;
				case '8':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/8.png"));
					break;
				case '9':
					level_lenght += 39+2;
					label_level[i] = new JLabel(new ImageIcon("arquivos/numbers/9.png"));
					break;
			}
		}
		
		x_padding = (400 - level_lenght)/2;
		
		for(int i = 0; i < level.length; i++){
			if(level[i] == '1'){
				label_level[i].setBounds(x_padding, 315, 26, 37);
				label_level[i].setVisible(true);
				this.game.getMainFrame().add(label_level[i]);
				x_padding += 26+2;
			}
			else{
				label_level[i].setBounds(x_padding, 315, 39, 37);
				label_level[i].setVisible(true);
				this.game.getMainFrame().add(label_level[i]);
				x_padding += 39+2;
			}
		}
		
		for(int i = 0; i < coins.length; i++){
			switch(coins[i]){
				case '0':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/0.png"));
					break;
				case '1':
					coins_lenght += 26+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/1.png"));
					break;
				case '2':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/2.png"));
					break;
				case '3':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/3.png"));
					break;
				case '4':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/4.png"));
					break;
				case '5':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/5.png"));
					break;
				case '6':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/6.png"));
					break;
				case '7':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/7.png"));
					break;
				case '8':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/8.png"));
					break;
				case '9':
					coins_lenght += 39+2;
					label_coins[i] = new JLabel(new ImageIcon("arquivos/numbers/9.png"));
					break;
			}
		}
		
		x_padding = (400 - coins_lenght)/2;
		
		for(int i = 0; i < coins.length; i++){
			if(coins[i] == '1'){
				label_coins[i].setBounds(x_padding, 430, 26, 37);
				label_coins[i].setVisible(true);
				this.game.getMainFrame().add(label_coins[i]);
				x_padding += 26+2;
			}
			else{
				label_coins[i].setBounds(x_padding, 430, 39, 37);
				label_coins[i].setVisible(true);
				this.game.getMainFrame().add(label_coins[i]);
				x_padding += 39+2;
			}
		}
		
		this.game.getMainFrame().add(mainLabel);
		this.game.getMainFrame().add(bt_back);
		this.game.getMainFrame().add(bt_load);
		
		this.game.getMainFrame().getContentPane().repaint();
	}
}
