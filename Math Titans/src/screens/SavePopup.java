package screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import game.Game;

public class SavePopup implements ActionListener{
	private static JFrame save = new JFrame("Salvar?");
	private Game game;
	private JButton yes;
	private JButton no;
	
	public SavePopup(Game game){
		this.game = game;
		JLabel text1 = new JLabel("Deseja salvar o progresso?");
		text1.setBounds(20, 10, 200, 20);
		text1.setVisible(true);
		JLabel text2 = new JLabel("Sera criado um arquivo 'save'");
		text2.setBounds(20, 25, 200, 20);
		text2.setVisible(true);
		JLabel text3 = new JLabel("na pasta de saves e voce poderá");
		text3.setBounds(20, 40, 200, 20);
		text3.setVisible(true);
		JLabel text4 = new JLabel("usá-lo para retomar o jogo");
		text4.setBounds(20, 55, 200, 20);
		text4.setVisible(true);
		JLabel text5 = new JLabel("de onde parou :D");
		text5.setBounds(20, 70, 200, 20);
		text5.setVisible(true);
		this.yes = new JButton("Sim");
		this.yes.setBounds(20, 90, 100, 20);
		this.yes.setVisible(true);
		this.yes.addActionListener(this);
		this.no = new JButton("Nao");
		this.no.setBounds(130, 90, 100, 20);
		this.no.setVisible(true);
		this.no.addActionListener(this);
		
		save.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		save.setLayout(null);
		save.setResizable(false);
		save.setLocationRelativeTo(null);
		save.setBounds(1100, 200, 250, 150);
		
		save.add(text1);
		save.add(text2);
		save.add(text3);
		save.add(text4);
		save.add(text5);
		save.add(yes);
		save.add(no);
	}

	public void show(){
		save.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object obj = evt.getSource();
		
		if((JButton) obj == this.yes){
			String level = Integer.toString(this.game.getGameSave().getIntLevel());
			String score = Integer.toString(this.game.getGameSave().getIntScore());
			FileWriter file = null;
			try{
				file = new FileWriter("arquivos/saves/save.txt");
				file.write(level+"\n"+score);
				file.close();
			}
			catch(IOException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		
		if((JButton) obj == this.no){
			System.exit(0);
		}
	}
}
