package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameSave{
	private int int_level;
	private int int_score;
	private String str_level;
	private String str_score;
	private BufferedReader bf;
	
	public GameSave(){
		this.int_level = 1;
		this.int_score = 0;
		this.str_level = "1";
		this.str_score = "0";
	}
	
	public GameSave(String s) throws IOException,FileNotFoundException{
		bf = new BufferedReader(new FileReader(s));
		String aux;
		aux = bf.readLine();
		this.int_level = Integer.parseInt(aux);
		this.str_level = aux;
		aux = bf.readLine();
		this.int_score = Integer.parseInt(aux);
		this.str_score = aux;
	}
	
	public void setLevel(int level){
		this.int_level = level;
		this.str_level = Integer.toString(level);
	}
	
	public void setScore(int score){
		this.int_score = score;
		this.str_score = Integer.toString(score);
	}

	public int getIntLevel(){
		return int_level;
	}
	
	public int getIntScore(){
		return int_score;
	}
	
	public char[] getStrLevel(){
		return str_level.toCharArray();
	}
	
	public char[] getStrScore(){
		return str_score.toCharArray();
	}
}
