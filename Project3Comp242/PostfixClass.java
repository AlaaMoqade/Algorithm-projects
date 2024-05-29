package Project3Comp242;

import java.util.ArrayList;
import java.util.LinkedList;

import lap5.Stack;

public class PostfixClass implements Comparable<PostfixClass> {

	private LinkedList<Equation> equations;
    static LinkedList<Equation> results; 

    public PostfixClass() {
        this.equations = new LinkedList<>();
        this.results = new LinkedList<>();
    }

    public void addEquation(String equation) {
        equations.add(new Equation(equation));
    }
    
    public void addResultEquation(String resultEquation) {
        results.add(new Equation(resultEquation));
    }

    public LinkedList<Equation> getEquations() {
        return equations;
    }
    
	public void setResults(LinkedList<Equation> results) {
		this.results = results;
	}
	

	public int getSize() {
        return equations.size();
    }

	public static String convertPostfixToPrefix(String postfix) {
	    StakeList<String> operands = new StakeList<>();
	    String[] tokens = postfix.split("\\s+");
	    for (String token : tokens) {
	        if (Character.isDigit(token.charAt(0)) || Character.isLetter(token.charAt(0))) {
	            operands.push(token);
	        } else { 
	            String operand2 = operands.pop();
	            String operand1 = operands.pop();
	            operands.push( token + " " +operand1 +" "+ operand2);
	        }
	    }

	    return operands.pop().trim(); 
	}




	public static double EvaluatePre(String prefix) {
	    StakeList<Double> operands = new StakeList<>();
	    String[] tokens = prefix.split("\\s+");

	    for (int i = tokens.length - 1; i >= 0; i--) {
	        String token = tokens[i];
	        if (isOperand(token)) {
	            operands.push(Double.parseDouble(token));
	        } else if (isOperator(token)) {
	            double operand1 = operands.pop();
	            double operand2 = operands.pop();
	            double result = evaluateOperator(operand1, operand2, token.charAt(0));
	            operands.push(result);
	        }
	    }

	    return operands.pop();
	}

	private static boolean isOperand(String s) {
	    return s.matches("[0-9]+(\\.[0-9]+)?"); 
	}

	private static boolean isOperator(String s) {
	    return s.length() == 1 && "+-*/".contains(s); 
	}

	private static double evaluateOperator(double operand1, double operand2, char operation) {
	    switch (operation) {
	        case '+':
	            return operand1 + operand2;
	        case '-':
	            return operand1 - operand2;
	        case '*':
	            return operand1 * operand2;
	        case '/':
	            if (operand2 == 0) {
	                System.out.println("Error: Division by zero");
	                System.exit(1); 
	            }
	            return operand1 / operand2;
	        default:
	            throw new IllegalArgumentException("Unexpected value: " + operation);
	    }
	}


	public void solveEquations() {
        for (int i = equations.size() - 1; i >= 0; i--) { 
            Equation equation = equations.get(i);
            String prefix = convertPostfixToPrefix(equation.getEquation());
            double result2 = EvaluatePre(prefix);
            equation.setResult(result2); 
            equation.setPrefix(prefix);
        }
    }

    public LinkedList<Equation> getResults() {
        return results;
    }

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("<postfix>\n");
        for (Equation eq : equations) {
            result.append("\t\t").append(eq).append("\n");
        }
        result.append("\t</postfix>");
        return result.toString();
	}

	@Override
	public int compareTo(PostfixClass o) {
		return Integer.compare(this.equations.size(), o.equations.size());

	}

}
