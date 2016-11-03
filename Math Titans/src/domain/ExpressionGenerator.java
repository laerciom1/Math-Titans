package domain;

import java.util.ArrayList;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionGenerator {
	public String generate(int operandos, int max_number) throws ScriptException{
		Random random = new Random();
		ArrayList<String> resultado = new ArrayList<String>();
		String sresult = new String();
		int randNumber;
		int randOp;
		int lastRandOp = -1;
		max_number++;
		
		randNumber = random.nextInt(max_number) + 1;
		resultado.add(Integer.toString(randNumber));
		sresult += randNumber;
		operandos--;
		while (operandos > 0){
			while(true){
				randOp = random.nextInt(4);
				if(randOp != lastRandOp){
					lastRandOp = randOp;
					break;
				}
			}
			
			switch(randOp){
				case 0:
					randNumber = random.nextInt(max_number) + 1;
					resultado.add("+");
					resultado.add(Integer.toString(randNumber)); 
					sresult += "+";
					sresult += randNumber;
					break;
				case 1:
					randNumber = random.nextInt(max_number) + 1;
					resultado.add("-");
					resultado.add(Integer.toString(randNumber)); 
					sresult += "-";
					sresult += randNumber;
					break;
				case 2:
					randNumber = random.nextInt(max_number) + 1;
					resultado.add("*");
					resultado.add(Integer.toString(randNumber)); 
					sresult += "*";
					sresult += randNumber;
					break;
				case 3:
					resultado.add("/");
					sresult += "/";
					while(true){
						int aux = random.nextInt(max_number) + 1;
						if(randNumber%aux == 0){
							resultado.add(Integer.toString(aux));
							sresult += randNumber;
							break;
						}
					}						
					break;
			}
			operandos--;
		}
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		Object obj = engine.eval(sresult);
		int iresult = (Integer) obj;
		resultado.add("R");
		resultado.add(Integer.toString(iresult));
		String RRESULTADO = "";
		for(String s : resultado){
			RRESULTADO += s;
		}
		System.out.println("EG: " + RRESULTADO + "\n\n");
		return RRESULTADO;
	}
}
