package screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import game.Game;
import domain.ExpressionGenerator;

public class GameScreen extends Thread implements ActionListener{
	private Game game;
	private JLabel character;
	private JButton bt_back;
	private boolean running;
	private int score;
	
	// Level
	private JLabel[] monsters;
	private JLabel background;
	
	// GamePlay
	private ExpressionGenerator eg;
	private JButton right_button;
	private JButton[] wrong_buttons = new JButton[3];
	private int level;
	private int sublevel;
	private Boolean paint;
	private Boolean generate_expression;
	private Boolean counting;
	private int[] monsters_hp;
	private int[] monsters_hp_actual;
	private JLabel[] monster_hp;
	private int actual_score;
	private int sublevel_actual_score;
	private JLabel time_bar_counting;
	
	public GameScreen(Game game){
		this.eg = new ExpressionGenerator();
		this.game = game;
		this.monsters_hp = new int[]{2, 2, 2, 3, 3, 3, 4, 4, 4, 5};
		
		this.level = game.getGameSave().getIntLevel();
		this.score = game.getGameSave().getIntScore();
		loadLevel(level);
		
		this.character = new JLabel();
		ImageIcon ii = new ImageIcon("arquivos/objects/char.png");
		this.character.setOpaque(false);
		this.character.setIcon(ii);
		this.character.setBounds((400-ii.getIconWidth())/2, 550, 56, 91);
		this.character.setVisible(true);
		
		this.bt_back = new JButton();
		this.bt_back.setIcon(new ImageIcon("arquivos/buttons/voltar.png"));
		this.bt_back.setBounds(6,6,49,49);
		this.bt_back.setBorder(null);
		this.bt_back.setContentAreaFilled(false);
		this.bt_back.setOpaque(false);
		this.bt_back.addActionListener(game);
	}
	
	private void loadLevel(int level){
		if(level > 3){
			setRunning(false);
			game.getGameSave().setLevel(3);
			game.getGameSave().setScore(score);
			game.getFinal_screen().show();
		}
		else{
			monsters = new JLabel[10];
			monsters_hp_actual = new int[monsters_hp.length];
			for(int i = 0; i < monsters_hp.length; i++){
				monsters_hp_actual[i] = monsters_hp[i];
			}
			for(int i = 0; i < 10; i++){
				monsters[i] = new JLabel();
				ImageIcon ii = new ImageIcon("arquivos/monsters/level" + level + "/" + i + ".png");
				monsters[i].setOpaque(false);
				monsters[i].setIcon(ii);
				monsters[i].setBounds((400-ii.getIconWidth())/2,150,ii.getIconWidth(),ii.getIconHeight());
				monsters[i].setVisible(true);
			}
			sublevel = 0;
			actual_score = 0;
			sublevel_actual_score = 600;
			this.time_bar_counting = new JLabel();
			this.time_bar_counting.setOpaque(true);
			this.time_bar_counting.setBackground(Color.GREEN);
			this.time_bar_counting.setBounds(17, 645, 360, 10);
			this.time_bar_counting.setVisible(true);
			monster_hp = new JLabel[monsters_hp[sublevel]];
			for(int i = 0; i < monster_hp.length; i++){
				monster_hp[i] = new JLabel();
			}
			config_monster_hp(monster_hp, monsters, sublevel);
			background = new JLabel(new ImageIcon("arquivos/backgrounds/levels/"+level+".png"));
			background.setBounds(0,0,400,700);
			background.setVisible(true);
		}
	}
	
	public JButton getBackButton(){
		return this.bt_back;
	}
	
	public void setRunning(boolean r){
		this.running = r;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt){
		Object obj = evt.getSource();
		if((JButton) obj == right_button){
			monsters_hp_actual[sublevel]--;
			if(monsters_hp_actual[sublevel] == 0){
				counting = false;
				actual_score += sublevel_actual_score;
				sublevel_actual_score = 600;
				sublevel++;
				if(sublevel > 9){
					score += actual_score;
					actual_score = 0;
					loadLevel(++level);
				}
				else{
					monster_hp = new JLabel[monsters_hp[sublevel]];
					for(int i = 0; i < monster_hp.length; i++){
						monster_hp[i] = new JLabel();
					}
					config_monster_hp(monster_hp, monsters, sublevel);
				}
			}
			paint = true;
			generate_expression = true;
		}
		else if((JButton) obj == wrong_buttons[0] ||
					(JButton) obj == wrong_buttons[1] ||
						(JButton) obj == wrong_buttons[2]){
		}
	}
	
	@Override
	public void run(){
		String[] expression_and_answer = null;
		paint = true;
		generate_expression = true;
		counting = true;
		
		JLabel[] label_expression = null; 	// Precisa para o paint
		JLabel[][] label_answer = null;		// referenciar
		while(running){			
			if(generate_expression){
				try{
					expression_and_answer = eg.generate(monsters_hp[sublevel], 9, level).split("R");
				}
				catch(ScriptException e){e.printStackTrace();}
				
				/**
				 * Expression!!!
				 */
				expression_and_answer[0] += "?";
				char[] actual_expression = expression_and_answer[0].toCharArray();
				label_expression = generate_expression_label(actual_expression);
				
				/**
				 * Answer!!!
				 */
				int actual_answer = Integer.parseInt(expression_and_answer[1]);
				label_answer = generate_answers_label(actual_answer);
				
				generate_expression = false;
			}
			
			if(paint){
				game.getMainFrame().getContentPane().removeAll();
				game.getMainFrame().add(time_bar_counting);
				game.getMainFrame().add(right_button);
				for(JButton jb : wrong_buttons){
					game.getMainFrame().add(jb);
				}
				for(int i = 0; i < monsters_hp_actual[sublevel]; i++){
					this.game.getMainFrame().add(monster_hp[i]);
			}
				for(JLabel jl : label_expression){
						this.game.getMainFrame().add(jl);
				}
				for(JLabel[] jlv : label_answer){
					for(JLabel jl: jlv){
						this.game.getMainFrame().add(jl);
					}
				}
				game.getMainFrame().add(bt_back);
				game.getMainFrame().add(monsters[sublevel]);
				game.getMainFrame().add(character);
				game.getMainFrame().add(background);
				game.getMainFrame().getContentPane().repaint();
				paint = false;
				counting = true;
			}
			try{
				GameScreen.sleep(300);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(counting){
				sublevel_actual_score -= 6;
				if(sublevel_actual_score <= 0){
					setRunning(false);
					game.getGameSave().setLevel(level);
					game.getGameSave().setScore(score);
					game.getOver_screen().show();
					break;
				}
				time_bar_counting.setBounds(17, 645, ((360*sublevel_actual_score) / 600), 10);
			}
		}
	}
	
	private JLabel[] generate_expression_label(char[] actual_expression){
		int expression_lenght = 0;
		JLabel[] label_expression = new JLabel[actual_expression.length];
		
		for(int i = 0; i < actual_expression.length; i++){
			switch(actual_expression[i]){
				case '0':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/0.png"));
					break;
				case '1':
					expression_lenght += 26+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos//numbers//1.png"));
					break;
				case '2':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/2.png"));
					break;
				case '3':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/3.png"));
					break;
				case '4':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/4.png"));
					break;
				case '5':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/5.png"));
					break;
				case '6':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/6.png"));
					break;
				case '7':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/7.png"));
					break;
				case '8':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/8.png"));
					break;
				case '9':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/9.png"));
					break;
				case '+':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/op_plus.png"));
					break;
				case '-':
					expression_lenght += 34+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/op_minus.png"));
					break;
				case '*':
					expression_lenght += 34+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/op_mult.png"));
					break;
				case '/':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/op_div.png"));
					break;
				case '?':
					expression_lenght += 39+2;
					label_expression[i] = new JLabel(new ImageIcon("arquivos/numbers/xinter.png"));
					break;
			}
		}
		
		int x_padding = (400 - expression_lenght)/2;
		
		for(int i = 0; i < actual_expression.length; i++){
			if(actual_expression[i] == '1'){
				label_expression[i].setBounds(x_padding, 100, 26, 37);
				label_expression[i].setVisible(true);
				x_padding += 26+2;
			}
			else if(actual_expression[i] == '-'){
				label_expression[i].setBounds(x_padding, 118, 34, 14);
				label_expression[i].setVisible(true);
				x_padding += 34+2;
			}
			else if(actual_expression[i] == '*'){
				label_expression[i].setBounds(x_padding, 100, 34, 32);
				label_expression[i].setVisible(true);
				x_padding += 34+2;
			}
			else{
				label_expression[i].setBounds(x_padding, 100, 39, 37);
				label_expression[i].setVisible(true);
				x_padding += 39+2;
			}
		}
		return label_expression;
	}
	
	public JLabel[][] generate_answers_label(int actual_answer){
		char[][] actual_answers = new char[4][];
		Random rand = new Random();
		int pos = rand.nextInt(4);
		int fator;
		if(actual_answer != 0){
			 fator = rand.nextInt(Math.abs(actual_answer))+1;
		}
		else{
			fator = rand.nextInt(9)+1;
		}
		
		for(int i = 0; i < 4; i++){
			if(i == pos){
				actual_answers[i] = Integer.toString(actual_answer).toCharArray();
			}
			else{
				if(i < pos){
					actual_answers[i] = Integer.toString(actual_answer-((pos-i)*fator)).toCharArray();
				}
				else{
					actual_answers[i] = Integer.toString(actual_answer+((i-pos)*fator)).toCharArray();
				}
			}
		}
		
		int[] answers_lenght = new int[4];
		JLabel[][] label_answer = new JLabel[4][];
		
		int wrong = 0;
		for(int a = 0; a < 4; a++){
			label_answer[a] = new JLabel[actual_answers[a].length];
			for(int i = 0; i < actual_answers[a].length; i++){
				switch(actual_answers[a][i]){
					case '0':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/0.png"));
						break;
					case '1':
						answers_lenght[a] += 26+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos//numbers//1.png"));
						break;
					case '2':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/2.png"));
						break;
					case '3':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/3.png"));
						break;
					case '4':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/4.png"));
						break;
					case '5':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/5.png"));
						break;
					case '6':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/6.png"));
						break;
					case '7':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/7.png"));
						break;
					case '8':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/8.png"));
						break;
					case '9':
						answers_lenght[a] += 39+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/9.png"));
						break;
					case '-':
						answers_lenght[a] += 34+2;
						label_answer[a][i] = new JLabel(new ImageIcon("arquivos/numbers/op_minus.png"));
						break;
				}
			}
		}
		
		int x_padding = 0;
		for(int a = 0; a < 4; a++){
			if(a == 1 || a == 2){
				x_padding = (a*133) - (answers_lenght[a]/2);
			}
			else if(a == 0){
				x_padding = 80 - (answers_lenght[a]/2);
			}
			else{
				x_padding = 320 - (answers_lenght[a]/2);
			}
			
			for(int i = 0; i < actual_answers[a].length; i++){
				if(a == 0 || a == 3){
					if(actual_answers[a][i] == '1'){
						label_answer[a][i].setBounds(x_padding, 520, 26, 37);
						label_answer[a][i].setVisible(true);
						x_padding += 26+2;
					}
					else if(actual_answers[a][i] == '-'){
						label_answer[a][i].setBounds(x_padding, 538, 34, 14);
						label_answer[a][i].setVisible(true);
						x_padding += 34+2;
					}
					else{
						label_answer[a][i].setBounds(x_padding, 520, 39, 37);
						label_answer[a][i].setVisible(true);
						x_padding += 39+2;
					}
				}
				else if(a == 1 || a == 2){
					if(actual_answers[a][i] == '1'){
						label_answer[a][i].setBounds(x_padding, 460, 26, 37);
						label_answer[a][i].setVisible(true);
						x_padding += 26+2;
					}
					else if(actual_answers[a][i] == '-'){
						label_answer[a][i].setBounds(x_padding, 478, 34, 14);
						label_answer[a][i].setVisible(true);
						x_padding += 34+2;
					}
					else{
						label_answer[a][i].setBounds(x_padding, 460, 39, 37);
						label_answer[a][i].setVisible(true);
						x_padding += 39+2;
					}
				}
			}
			if(a == pos){
				right_button = generate_option(label_answer[a]);
			}
			else{
				wrong_buttons[wrong++] = generate_option(label_answer[a]);
			}
		}
		return label_answer;
	}
	
	private JButton generate_option(JLabel[] label_answer){
		JButton option = new JButton();
			int x = label_answer[0].getBounds().x;
			int y = label_answer[0].getBounds().y;
			int width = (label_answer[label_answer.length-1].getBounds().x + label_answer[label_answer.length-1].getBounds().width) - x;
			int height = 0;
			for(int i = 0; i < label_answer.length; i++){
				if(label_answer[i].getBounds().height > height){
					height = label_answer[i].getBounds().height;
				}
			}
			option.setBounds(x, y, width, height);
			option.setBorder(null);
			option.setContentAreaFilled(false);
			option.setOpaque(false);
			option.addActionListener(this);
		return option;
	}
	
	private void config_monster_hp(JLabel[] monster_hp, JLabel[] monsters, int sublevel){
		int X_pos = (monsters[sublevel].getBounds().x + (monsters[sublevel].getBounds().width / 2)) - ((monster_hp.length*25 + (monster_hp.length-1 * 3))/2);
		int Y_pos = monsters[sublevel].getBounds().y + monsters[sublevel].getBounds().height + 10;
		for(int i = 0; i < monster_hp.length; i++){
			monster_hp[i].setOpaque(true);
			monster_hp[i].setBackground(Color.RED);
			monster_hp[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			monster_hp[i].setBounds(X_pos, Y_pos, 25, 10);
			monster_hp[i].setVisible(true);
			X_pos += 25 + 3;
		}
	}
}
