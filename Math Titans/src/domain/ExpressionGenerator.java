package domain;

//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionGenerator {
	public String generate(int operandos, int max_number, int level) throws ScriptException{
		Random random = new Random();
		String sresult = new String();
		int randNumber;
		int randOp;
		int lastRandOp = -1;
		
		randNumber = random.nextInt(max_number) + 1;
		sresult += randNumber;
		operandos--;
		while (operandos > 0){
			while(true){
				randOp = random.nextInt(4);
				if(level == 1){
					if(randOp == 2){
						randOp = 0;
					}
					else if(randOp == 3){
						randOp = 1;
					}
					if(randOp != lastRandOp){
						lastRandOp = randOp;
						break;
					}
				}
				else if(level == 2){
					if(randOp == 0){
						randOp = 2;
					}
					else if(randOp == 1){
						randOp = 3;
					}
					if(randOp != lastRandOp){
						lastRandOp = randOp;
						break;
					}
				}
				else if(level == 3){
					if(randOp != lastRandOp){
						lastRandOp = randOp;
						break;
					}
				}
			}
			switch(randOp){
				case 0:
					randNumber = random.nextInt(max_number) + 1;
					sresult += "+";
					sresult += randNumber;
					break;
				case 1:
					randNumber = random.nextInt(max_number) + 1;
					sresult += "-";
					sresult += randNumber;
					break;
				case 2:
					randNumber = random.nextInt(max_number) + 1;
					sresult += "*";
					sresult += randNumber;
					break;
				case 3:
					sresult += "/";
					while(true){
						int aux = random.nextInt(max_number) + 1;
						if(randNumber%aux == 0){
							sresult += aux;
							randNumber = aux;
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
		sresult += "R";
		sresult += obj;
		//System.out.println("EG: " + sresult);
		return sresult;
	}
	
//	public static void main(String args[]) throws FileNotFoundException, IOException, ScriptException{
//		ExpressionGenerator eg = new ExpressionGenerator();
//		while(true){
//			eg.generate(5, 9, 3);
//		}
//	}
}
