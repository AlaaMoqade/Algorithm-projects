package Project3Comp242;

public class Equation implements Comparable<Equation>{

	private String equation;
	private double result;
	private String postfix;
	private String infix;
	
	private String prefix;
	

	public Equation(String equation) {
		this.equation = equation;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	@Override
	public String toString() {
		return   equation ;
	}
	
	

	public double getResult() {
		return result;
	}
	

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setResult(double result) {
		this.result = result;
	}
	

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public String getInfix() {
		return infix;
	}

	public void setInfix(String infix) {
		this.infix = infix;
	}

	@Override
	public int compareTo(Equation o) {
		return Integer.compare(this.equation.length(), o.equation.length());
	}
}
