package Project3Comp242;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import lap5.Stack;

public class driver {

	static StakeList<Section> sectionStack = new StakeList<>();
	static LinkedList<Section> sectionList = new LinkedList<>();

	public static boolean isEquationsFileBalanced(File filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			StakeList<String> stack = new StakeList<>();

			String line;
			while ((line = reader.readLine()) != null) {

				line = line.trim();

				switch (line) {

				case "<242>":
					stack.push("<242>");
					break;
				case "</242>":
					if (stack.isEmpty() || !stack.pop().equals("<242>")) {
						return false;
					}
					break;

				case "<section>":
					stack.push("<section>");

					break;
				case "</section>":
					if (stack.isEmpty() || !stack.pop().equals("<section>")) {
						return false;
					}

					break;

				case "<infix>":
					stack.push("<infix>");

					break;
				case "</infix>":
					if (stack.isEmpty() || !stack.pop().equals("<infix>")) {
						return false;
					}

					break;

				case "<postfix>":
					stack.push("<postfix>");
					break;
				case "</postfix>":
					if (stack.isEmpty() || !stack.pop().equals("<postfix>")) {
						return false;
					}
					break;

				case "<equation>":
					stack.push("<equation>");
					break;
				case "</equation>":
					if (stack.isEmpty() || !stack.pop().equals("<equation>")) {
						return false;
					}

					break;
				}
			}
			return stack.isEmpty();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static LinkedList<Section> processEquationsFile(File filePath) {
		Section section = null;

		Infixclass currentInfix = null;
		PostfixClass currentPostfix = null;

		Infixclass currentInfixR = null;
		PostfixClass currentPostfixR = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();

				switch (line) {
				case "<section>":
					section = new Section(new LinkedList<>(), new LinkedList<>());

					break;
				case "</section>":
					sectionList.add(section);

					currentInfix = null;
					currentPostfix = null;

					currentInfixR = null;

					break;

				case "<infix>":
					currentInfix = new Infixclass();
					currentInfixR = new Infixclass();

					currentPostfix = null;

					break;
				case "</infix>":
					section.getInfixExpressions().add(currentInfix); // Add to the beginning of the list
					currentInfix.solveEquations();

					break;

				case "<postfix>":
					currentPostfix = new PostfixClass();

					currentInfix = null;

					break;
				case "</postfix>":
					section.getPostfixExpressions().add(currentPostfix); // Add to the beginning of the list
					currentPostfix.solveEquations();

					break;

				default:
					if (!line.startsWith("<equation>"))
						break;

					line = line.substring(10, line.length() - 11);
					line = line.trim();
					if (currentInfix != null) {
						currentInfix.addEquation(line);
					}

					if (currentPostfix != null) {
						currentPostfix.addEquation(line);
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sectionList;
	}

}