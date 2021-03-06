package ass3;

import java.util.*;
import java.util.regex.*;

//Algorithm: http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm

public class TestInfix2PostfixConverter_{
	
	static String convertPostfix(String infixExpression){
		
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
		List <String> infixExpressions = convertStringToArray(infixExpression);
		
		
		for (String expression : infixExpressions){
			//operand found: edit postfix expression
//			if(Integer.parseInt(expression) == Integer.valueOf(expression) ){
//				postFix.add(expression);
//			}
			if(isInteger(expression)){
				postFix.add(expression);
			}
			else if(expression == "("){
				opStack.push(expression);
			}
			else if(expression == ")"){
//				//pop is used instead of peek as there's a need to
//				//eliminate the left parenthesis
				String top_op = (String) opStack.pop();
				while(/*!opStack.isEmpty() && */opStack.peek() != "("){
					postFix.add((String) opStack.pop());
					opStack.pop();
				}
//				opStack.pop();
//				if(opStack.peek() == "("){
//					opStack.pop();
//				}
			}
//			operator found
//			also takes care of the possibility of finding
//			a "(" as it has the lowest precedence
			else {
				if (opStack.isEmpty() || opStack.peek() == "("){
					opStack.push(expression);
				}
				else{
					while(!opStack.isEmpty() &&	opStack.peek() == "(" && 
							precedence.get(opStack.peek()) >=precedence.get(expression)){
				postFix.add((String) opStack.pop());
					}
					//push latest operator onto stack
					opStack.push(expression);
				}
			}
		}

		while(!opStack.isEmpty()){
			postFix.add((String) opStack.pop());
		}
		
//		format string
		String postFixExpression = String.join(" ", postFix);
//		take care of stray brackets
		postFixExpression = postFixExpression.replaceAll("\\(","").replaceAll("\\)", "");
		return postFixExpression;
	}
	
	static Double getPostfix(String postFixEpression){
		StackReferenceBased<Object> operandStack = 
				new StackReferenceBased<>();
		
		List<String> operands = new ArrayList<>();
		operands = convertStringToArray(postFixEpression);
		
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
		return (Double) operandStack.pop();
	}
	
	//make an array of strings without delimiters
	static List<String> convertStringToArray(String exp){
	    String regex = "(\\d+\\.\\d+)|(\\d+)|([+-/*])|([/(/)])";
	    Matcher m = Pattern.compile(regex).matcher(exp);
	    List <String> strings = new LinkedList<>();
	    while (m.find()) {
	        strings.add(m.group());
	    }
	    System.out.println(strings);
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
		case "(": case ")": 
			return true;
		default: 
			return false;
		}
	}
	
	static double calculate(String operator, double operand1, double operand2){
		double result = 1;
		//System.out.println(evaluateOp(operator));
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
	
	public static void main (String args[]){
		String exp = infixExpression();
		System.out.println("infix: " + exp);
		String postFixExpression = convertPostfix(exp); 
		System.out.println("postfix: " + postFixExpression);
		System.out.println("result: " + getPostfix(postFixExpression));
	}
	
	//get user input
	private static String infixExpression(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Please put in your infix expression");
		String input = scan.nextLine();
		scan.close();
	    return input;
	}
}