package Submissions;

import java.util.*;
import java.util.regex.*;

//Algorithm: http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm

public class TestInfix2PostfixConverter{
	
	private static String exp;
	
	private void setExpression(String newExp){
		exp = newExp;
	}
	
	public static void main (String args[]){
		System.out.println("Frank Tests");
		TestInfix2PostfixConverter longClassName = new TestInfix2PostfixConverter();
		
		String test = "(10 + 3 * 4 / 6)";
		longClassName.setExpression(test);
		longClassName.run();
		
		test = "12*3 - 4 + (18 / 6)";
		longClassName.setExpression(test);
		longClassName.run();
		
		test = "35-42*17/2+10";
		longClassName.setExpression(test);
		longClassName.run();
		
		test = "3 * (4 + 5)";
		longClassName.setExpression(test);
		longClassName.run();
		
		test = "3 * ( 17 - (5+2))/(2+3)";
		longClassName.setExpression(test);
		longClassName.run();		
		
		test = infixExpression();
		longClassName.setExpression(test);
		longClassName.run();
	}
	
	//get user input
	private static String infixExpression(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please put in your infix expression");
		String input = scan.nextLine();
		scan.close();
	    return input;
	}
	
//	public TestInfix2PostfixConverter(){
//		TestInfix2PostfixConverter.exp = exp;
//	}
	
	void run(){
		System.out.println("infix: " + exp);
		convertPostfix();
		getPostfix();
	}
	
	static void convertPostfix(/*String infixExpression*/){
		
		HashMap<String, Integer> precedence = new HashMap<>();
		//Each operator is mapped to a corresponding precedence
		//Therefore an operator compared to another may or may not
		//be placed on top of it
		precedence.put("*", 3);
		precedence.put("/", 3);
		precedence.put("+", 2);
		precedence.put("-", 2);
		precedence.put("(", 1);
		
		StackReferenceBased <Object> opStack = 
				new StackReferenceBased<> ();
		List<String> postFix = new ArrayList<>();
		List <String> infixExpressions = convertStringToArray(exp);
		
		
		for (String token : infixExpressions){
			//operand found: edit postfix expression
			if(isInteger(token))
				postFix.add(token);
			else if(token.equals("("))
				opStack.push(token);
			else if(token.equals(")")){
//				//pop is used instead of peek as there's a need to
//				//eliminate the left parenthesis
				while(!opStack.isEmpty() && !opStack.peek().equals("(")){
					postFix.add((String) opStack.pop());
				}
				//pop the left parenthesis
				opStack.pop();
			}
//			operator found
//			also takes care of the possibility of finding
//			a "(" as it has the lowest precedence
			else if (isOperator(token)){
				//add first operator
				if (opStack.isEmpty() || opStack.peek().equals("("))
					opStack.push(token);
				else{
					while(!opStack.isEmpty() &&	!opStack.peek().equals("(") && 
							precedence.get(token) <= precedence.get(opStack.peek())
							){
						postFix.add((String) opStack.pop());
					}
					//push latest operator onto stack
					opStack.push(token);
				}
			}
		}

		while(!opStack.isEmpty()){
			postFix.add((String) opStack.pop());
		}
		
//		format string
		exp = String.join(" ", postFix);
		System.out.println("postfix: " + exp);
//		take care of stray brackets
		//postFixExpression = postFixExpression.replaceAll("\\(","").replaceAll("\\)", "");
		//return exp;
	}
	
	
	
	static void getPostfix(){
		StackReferenceBased<Object> operandStack = 
				new StackReferenceBased<>();
		
		List<String> operands = new ArrayList<>();
		operands = convertStringToArray(exp);
		
		for (String op : operands){
			if(isInteger(op)){
				operandStack.push(Double.valueOf(op));
			}
			else if (isOperator(op)){
				Double operand2 = (Double) operandStack.pop();
				Double operand1 = (Double) operandStack.pop();
				Double result = calculate(op, operand1, operand2);
				operandStack.push(result);
			}
		}
		System.out.println("result: " + (Double) operandStack.pop());
		//return ;
	}
	
	//make an array of strings without delimiters
	static List<String> convertStringToArray(String exp){
	    String regex = "(\\d+\\.\\d+)|(\\d+)|([+-/*])|([/(/)])";
	    Matcher m = Pattern.compile(regex).matcher(exp);
	    List <String> strings = new LinkedList<>();
	    while (m.find()) {
	        strings.add(m.group());
	    }
	    //System.out.println(strings);
	    return strings;
	}
	
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}

	static boolean isOperator (String op){	
		switch(op){
		case "*": case "+": 
		case "/": case "-": 
			return true;
		default: 
			return false;
		}
	}
	
	static double calculate(String operator, double operand1, double operand2){
		double result = 1;
		if (evaluateOp(operator) == '*')
			result = operand1 * operand2;
		else if (evaluateOp(operator) == '+')
			result = operand1 + operand2;
		else if (evaluateOp(operator) == '-')
			result = operand1 - operand2;
		else
			result = operand1 / operand2;
		return result;
	}
	
	//needed to create this function as for some reason calculate function never 
	//recognized the operators correctly
	static char evaluateOp(String op){
		switch (op){
		case "*": return '*';
		case "/": return '/';
		case "+": return '+';
		case "-": return '-';
		default: return '=' ;
		}
	}
}