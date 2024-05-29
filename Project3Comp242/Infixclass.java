package Project3Comp242;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import lap5.Stack;

public class Infixclass implements Comparable<Infixclass> {

	private LinkedList<Equation> equations;


	public Infixclass() {
		this.equations = new LinkedList<>();
	}

	public void addEquation(String equation) {
		equations.add(new Equation(equation));
	}

	

	public LinkedList<Equation> getEquations() {
		return equations;
	}


	public static String infixToPostfix(String infix) {
		StringBuilder postfix = new StringBuilder();
		StakeList<Character> stack = new StakeList<>();

		StringTokenizer tokenizer = new StringTokenizer(infix, "+-*/^() ", true);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().trim();

			if (token.isEmpty()) {
				continue; // Skip empty tokens
			}

			char firstChar = token.charAt(0);

			if (Character.isDigit(firstChar)) {
				postfix.append(token).append(" ");
			} else if (isOperator(firstChar)) {
				while (!stack.isEmpty() && hasHigherPrecedence(firstChar, stack.peek())) {
					postfix.append(stack.pop()).append(" ");
				}
				stack.push(firstChar);
			} else if (firstChar == '(') {
				stack.push(firstChar);
			} else if (firstChar == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop()).append(" ");
				}
				if (!stack.isEmpty() && stack.peek() == '(') {
					stack.pop(); // Pop '('
				}
			}
		}

		while (!stack.isEmpty()) {
			postfix.append(stack.pop()).append(" ");
		}

		return postfix.toString().trim();
	}

	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}

	private static boolean hasHigherPrecedence(char op1, char op2) {
		return getPrecedence(op1) <= getPrecedence(op2);
	}

	private static int getPrecedence(char operator) {
		switch (operator) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		default:
			return 0;
		}
	}

	public static double EvaluatePost(String postfix) {
		StakeList<Double> operands = new StakeList<>();
		String[] tokens = postfix.split(" ");
		for (String token : tokens) {
			if (isOperator(token)) {
				double y = operands.pop();
				double x = operands.pop();
				double result = evaluateOparator(x, y, token.charAt(0));
				operands.push(result);
			} else {
				operands.push(Double.parseDouble(token));
			}

		}

		return operands.pop();
	}

	private static boolean isOperator(String s) {
		if (s.length() != 1) {
			return false;
		}
		switch (s.charAt(0)) {
		case '+':
		case '-':
		case '*':
		case '/':
		case '^':
			return true;

		default:
			return false;
		}

	}

	private static double evaluateOparator(double op1, double op2, char opration) {
		switch (opration) {
		case '+':
			return op1 + op2;
		case '*':
			return op1 * op2;
		case '-':
			return op1 - op2;
		case '/':
			if (op2 == 0)
				System.out.println("error !!! you cant division by 0!!");
			return op1 / op2;
		case '^':
			return Math.pow(op1, op2);

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}

	}

	public void solveEquations() {
		for (int i = equations.size() - 1; i >= 0; i--) {
			Equation equation = equations.get(i);
			String postfix = infixToPostfix(equation.getEquation());
			double result = EvaluatePost(postfix);
			equation.setResult(result);
			equation.setPostfix(postfix);
			
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("<infix>\n");
		for (Equation equation : equations) {
			result.append("\t\t").append(equation).append("\n");
		}
		result.append("</infix>");
		return result.toString();
	}

	@Override
	public int compareTo(Infixclass o) {
		return Integer.compare(this.equations.size(), o.equations.size());
	}
}
