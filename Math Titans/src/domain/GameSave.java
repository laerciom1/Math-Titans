package domain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameSave{
	private int int_level;
	private int int_coins;
	private String str_level;
	private String str_coins;
	private BufferedReader bf;
	
	public GameSave(){
		this.int_level = 1;
		this.int_coins = 0;
		this.str_level = "1";
		this.str_coins = "0";
	}
	
	public GameSave(String s) throws IOException,FileNotFoundException{
		bf = new BufferedReader(new FileReader(s));
		String aux;
		aux = bf.readLine();
		this.int_level = Integer.parseInt(aux);
		this.str_level = aux;
		aux = bf.readLine();
		this.int_coins = Integer.parseInt(aux);
		this.str_coins = aux;
	}

	public int getIntLevel(){
		return int_level;
	}
	
	public int getIntCoins(){
		return int_coins;
	}
	
	public char[] getStrLevel(){
		return str_level.toCharArray();
	}
	
	public char[] getStrCoins(){
		return str_coins.toCharArray();
	}
}
