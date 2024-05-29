package Project3Comp242;

import java.util.ArrayList;
import java.util.LinkedList;

public class Section implements Comparable<Section> {

	
	private LinkedList<Infixclass> infixExpressions;
	private LinkedList<PostfixClass> postfixExpressions;

	private PostfixClass postc;
	private Infixclass infixc;

	public Section(LinkedList<Infixclass> infixExpressions, LinkedList<PostfixClass> postfixExpressions) {

		this.infixExpressions = infixExpressions != null ? infixExpressions : new LinkedList<>();
		this.postfixExpressions = postfixExpressions != null ? postfixExpressions : new LinkedList<>();

	}

	public LinkedList<Infixclass> getInfixExpressions() {
		return infixExpressions;
	}

	public void setInfixExpressions(LinkedList<Infixclass> infixExpressions) {
		this.infixExpressions = infixExpressions;
	}

	public LinkedList<PostfixClass> getPostfixExpressions() {
		return postfixExpressions;
	}

	public void setPostfixExpressions(LinkedList<PostfixClass> postfixExpressions) {
		this.postfixExpressions = postfixExpressions;
	}

	public String printSection() {
		StringBuilder result = new StringBuilder("");

		// Infix expressions
		result.append("\t\t<infix>\n");
		for (Infixclass infix : infixExpressions) {
			for (Equation equation : infix.getEquations()) {

				result.append("\t\t\t * ").append(equation.getEquation()).append("  ==>  ").append(equation.getPostfix())
						.append("  ==>  ").append(equation.getResult()).append("\n");

			}
		}

		// Postfix expressions
		result.append("\t\t<postfix>\n");
		for (PostfixClass postfix : postfixExpressions) {
			for (Equation equation : postfix.getEquations()) {

				result.append("\t\t\t * ").append(equation.getEquation()).append("  ==>  ").append(equation.getPrefix())
						.append("  ==>  ").append(equation.getResult()).append("\n");

			}
		}

		return result.toString();
	}

	@Override
	public String toString() {
		return "Section [infixExpressions=" + infixExpressions + ", postfixExpressions=" + postfixExpressions
				+ ", postc=" + postc + ", infixc=" + infixc + "]";
	}

	@Override
	public int compareTo(Section o) {
		int totalEquations1 = infixExpressions.size() + postfixExpressions.size();
		int totalEquations2 = o.infixExpressions.size() + o.postfixExpressions.size();

		return Integer.compare(totalEquations1, totalEquations2);
	}

}
