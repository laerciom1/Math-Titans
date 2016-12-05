package screens;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import game.Game;

public class FinalScreen{
	private Game game;
	private JLabel mainLabel;
	private JButton bt_back;

	public FinalScreen(Game main_frame){
		this.game = main_frame;
		
		this.mainLabel = new JLabel();
		this.mainLabel.setIcon(new ImageIcon("arquivos/backgrounds/menus/final.png"));
		this.mainLabel.setBounds(0,0,400,700);
		this.mainLabel.setVisible(true);
		
		this.bt_back = new JButton();
		this.bt_back.setBounds(6,6,49,49);
		this.bt_back.setBorder(null);
		this.bt_back.setContentAreaFilled(false);
		this.bt_back.setOpaque(false);
		this.bt_back.addActionListener(main_frame);
	}
	
	public JButton getBackButton(){
		return bt_back;
	}

	public void show(){	
		this.game.getMainFrame().getContentPane().removeAll();
		char[] score = game.getGameSave().getStrScore();
		int score_lenght = 0;
		JLabel[] label_score = new JLabel[score.length];
		
		int x_padding;
		
		for(int i = 0; i < score.length; i++){
			switch(score[i]){
				case '0':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/0.png"));
					break;
				case '1':
					score_lenght += 26+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/1.png"));
					break;
				case '2':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/2.png"));
					break;
				case '3':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/3.png"));
					break;
				case '4':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/4.png"));
					break;
				case '5':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/5.png"));
					break;
				case '6':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/6.png"));
					break;
				case '7':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/7.png"));
					break;
				case '8':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/8.png"));
					break;
				case '9':
					score_lenght += 39+2;
					label_score[i] = new JLabel(new ImageIcon("arquivos/numbers/9.png"));
					break;
			}
		}
		
		x_padding = (400 - score_lenght)/2;
		
		for(int i = 0; i < score.length; i++){
			if(score[i] == '1'){
				label_score[i].setBounds(x_padding, 460, 26, 37);
				label_score[i].setVisible(true);
				this.game.getMainFrame().add(label_score[i]);
				x_padding += 26+2;
			}
			else{
				label_score[i].setBounds(x_padding, 460, 39, 37);
				label_score[i].setVisible(true);
				this.game.getMainFrame().add(label_score[i]);
				x_padding += 39+2;
			}
		}
		
		this.game.getMainFrame().add(mainLabel);
		this.game.getMainFrame().add(bt_back);
		
		this.game.getMainFrame().getContentPane().repaint();
	}
}
