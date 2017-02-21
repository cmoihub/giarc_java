package Assignment3;

import java.util.*;
import java.util.regex.*;

//Algorithm: http://faculty.cs.niu.edu/~hutchins/csci241/eval.htm

public class TestInfix2PostfixConverter{
	
	void getInput(){
		Scanner infix = new Scanner(System.in);
		
		System.out.println("Please give an expression in infix format");
		//infix.
		
		infix.close();
	}
	
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
			//operand found
			if(isOperand(expression)){
				postFix.add(expression);
			}
			else if(expression == "("){
				opStack.push(Integer.valueOf(expression));
			}
			else if(expression == ")"){
				//pop is used instead of peek as there's a need to
				//eliminate the left parenthesis
				String top_op = (String) opStack.pop();
				while(!opStack.isEmpty() && top_op != "("){
					postFix.add(top_op);
					top_op = (String) opStack.pop();
				}
			}
			//operator found
			//also takes care of the possibility of finding
			//a "(" as it has the lowest precedence
			else{
				while(!opStack.isEmpty() && 
						precedence.get(opStack.peek()) >= 
						precedence.get(expression)){
					postFix.add((String) opStack.pop());
				}
				//push latest operator onto stack
				opStack.push(expression);
			}
		}
		
		while(!opStack.isEmpty()){
			postFix.add((String) opStack.pop());
		}
		return String.join(" ", postFix);
	}
	
	Integer getPostfix(String postFixEpression){
		StackReferenceBased<Object> operandStack = 
				new StackReferenceBased<>();
		
		List<String> operands = new ArrayList<>();
		operands = convertStringToArray(postFixEpression);
		
		for (String operand : operands){
			if(isOperand(operand)){
				operandStack.push(Integer.valueOf(operand));
			}
			else{
				Integer operand2 = (Integer) operandStack.pop();
				Integer operand1 = (Integer) operandStack.pop();
				Integer result = calculate(operand, operand1, operand2);
				operandStack.push(result);
			}
		}
		return (Integer) operandStack.pop();
	}
	
	//make an array of strings without delimiters
	public static List<String> convertStringToArray(String exp){
	    String regex = "(\\d+\\.\\d+)|(\\d+)|([+-/*])|([/(/)])";

	    Matcher m = Pattern.compile(regex).matcher(exp);

	    List <String> strings = new LinkedList<>();

	    while (m.find()) {
	        strings.add(m.group());
	    }
	    
	    System.out.println(strings);
	    return strings;
	}
	
	public static boolean isOperand(String op){
	    String numbers = "0123456789.";
	    int a = numbers.indexOf(op);
	    return a >= 0;
	}
	
	boolean isOperator(String operand){
		switch(operand){
			case "*": return true;
			case "+": return true;
			case "/": return true;
			case "-": return true;
			case "(": return true;
			case ")": return true;
			default: return false;
		}
	}
	
	int calculate(String operator, int operand1, int operand2){
		int result = 1;
		switch(operator){
		case "*": result = operand1*operand2;
		case "+": result = operand1+operand2;
		case "/": result = operand1/operand2;
		case "-": result = operand1-operand2;		
		default:
			System.out.println("invalid operand found: please use either *, +, / or -");
			break;
		}
		return result;
	}
	
	static void output(){
		String exp = "15+20+(3.84*25)*(78/3.8)";
		//input
		System.out.println("infix: " + exp);
		//convertPostfix
		System.out.println("postfix: " + convertPostfix(exp));
		//getPostfix
		System.out.println("result: ");
		
	    //String exp = "15+20+(3.84*25)*(78/3.8)";
	    convertStringToArray(exp);
	    
	    //System.out.println(Integer.valueOf("("));
	}
	
	public static void main (String args[]){
		output();
	}

}