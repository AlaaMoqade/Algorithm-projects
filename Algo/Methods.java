package Algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//import Algo.Main.Path;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Methods {
	
	public static String[][] next;
	public static String[] city;
	public static int numOfCities = 0;
	public static int SCity = 0;// fromcity
	public static int ECity = 0;// to city
	public static String path;
	public static String endValue;
	public static String startValue = "";
	public static String Data = "";
	public static int[][] DPtable;
	public static int d = 0;

	public static void readFile(File file) throws FileNotFoundException { // method to read file and fill cities name in
																			// combo box
		Scanner scan = new Scanner(file);
		String number = scan.nextLine();
		numOfCities = Integer.parseInt(number); // number of cities
		int numOfLine = 0;
		city = new String[numOfCities];
		String EndCity = "";
		Data = "";

		while (scan.hasNext()) {
			numOfLine++;
			if (numOfLine == 1) {
				String s = scan.nextLine();
				String[] part = s.split(", ");
				city[0] = part[0];// ex start
				EndCity = part[1]; // ex end
				city[numOfCities - 1] = part[1];
				startValue = city[0];
				endValue = city[numOfCities - 1];
				continue;
			}
			String s = scan.nextLine();
			Data += s + "\n";
			String[] part2 = s.split(", ");
			city[numOfLine - 2] = part2[0]; // add cities in array
			
		}
														
	}

	public static void FindMinCost(String startCity, String endCity, String input) {
	    startValue = startCity;
	    endValue = endCity;

	    SCity = getIndex(city, startCity);
	    ECity = getIndex(city, endCity);

	    d = SCity; // d = 2

	    numOfCities = ECity - SCity + 1;

	    DPtable = new int[numOfCities][numOfCities];
	    next = new String[numOfCities][numOfCities];

	    for (int i = 0; i < numOfCities; i++) { // fill the table with initial values
	        for (int j = 0; j < numOfCities; j++) {
	            if (i == j)
	                DPtable[i][j] = 0;
	            else
	                DPtable[i][j] = Integer.MAX_VALUE;
	            next[i][j] = "X";
	        }
	    }

	    String[] line = new String[numOfCities - 1];

	    line = input.split("\n");

	    for (int i = 0; i < numOfCities - 1; i++) {
	        String[] parts = line[i + SCity].split(", (?=\\[)");//split line 

	        int city1 = i; // current city like A

	        for (int j = 1; j < parts.length; j++) {
	            String[] cityWithCosts = parts[j].replaceAll("[\\[\\]]", "").split(",");

	            String value = cityWithCosts[0].trim(); // value of the city

	            int city2 = 0;

	            for (int k = SCity; k <= ECity; k++) { // to get the index of city2 in the array
	                if (city[k].equals(value)) {
	                    city2 = k - SCity;
	                    break;
	                }
	            }
	            int petrolCost = Integer.parseInt(cityWithCosts[1].trim());
	            int hotelCost = Integer.parseInt(cityWithCosts[2].trim());

	            DPtable[city1][city2] = petrolCost + hotelCost;

	        }
	    }

	    for (int i = 0; i < numOfCities; i++) {   // fill table with initial cost
	        for (int j = 0; j < numOfCities; j++) {
	            if (j < i)
	                DPtable[i][j] = Integer.MAX_VALUE;
	            if (i == j)
	                DPtable[i][j] = 0;
	        }
	    }

	    for (int i = 0; i < numOfCities; i++) { // Finding Minimum Costs Using Dynamic and fill the table 
	        for (int j = 0; j < numOfCities; j++) {
	            for (int k = 0; k < numOfCities; k++) {
	                if (DPtable[j][i] == Integer.MAX_VALUE || DPtable[i][k] == Integer.MAX_VALUE)
	                    continue;

	                if (DPtable[j][k] > DPtable[j][i] + DPtable[i][k]) {
	                    DPtable[j][k] = DPtable[j][i] + DPtable[i][k];
	                    next[j][k] = city[i +d];
	                }

	            }
	        }
	    }

	    for (int i = 1; i < numOfCities; i++) {
	        if (next[0][i] == "X")
	            next[0][i] = startValue;
	        else
	            break;
	    }

	    SCity = SCity - d; // fromCity = 0
	    ECity = ECity - d;

	    for (int i = 0; i < next.length; i++)  // This refines the results by marking unreachable destinations or the starting city itself as having no next city.
	        if (DPtable[0][i] == 0 || DPtable[0][i] == Integer.MAX_VALUE)
	            next[0][i] = "X";
	}


	public static String printPath(String[][] arr, String src, String cities) {
		int size = ECity;
		String path = "";
		int diff = 0;
		while (!cities.equals(src)) {
			for (int j = size; j >= SCity; j--) {
				if (j == SCity)
					break;

				if (city[j + d].equals(cities)) {

					diff = size - j;
					path = city[j + d] + " -> " + path; // Start -> B -> E -> I -> J
					cities = arr[0][ECity - diff]; // I
				}
			}
		}
		return src + " -> " + path;
	}

	public static String printAllPaths(String[] city, int[][] table, String source, String destination) {
	    String result = "";
	    int sourceIndex = getIndex(city, source);
	    int destinationIndex = getIndex(city, destination);

	    if (sourceIndex == -1 || destinationIndex == -1) {
	        return "Source or destination city not found.";
	    }

	    ArrayList<String[]> allPaths = new ArrayList<>();
	    String[] currentPath = new String[city.length];
	    boolean[] visited = new boolean[city.length];

	    findAllPaths(sourceIndex, destinationIndex, table, city, visited, 0, currentPath, allPaths);

	    result += "All paths from " + source + " to " + destination + ":\n";
	    if (allPaths.size() == 0) {
	        result += "No paths found.\n";
	    } else {
	        int count = 0;
	        for (int i = 0; i < allPaths.size() && count < 4; i++) {
	            String[] path = allPaths.get(i);
	            result += "Path: " + String.join(" -> ", path) + "\n";
	            result += "Cost: " + calculatePathCost(table, path) + "\n";
	            count++;
	        }
	    }
	    return result;
	}


	private static void findAllPaths(int current, int destination, int[][] table, String[] city, boolean[] visited,
	                                  int pathLength, String[] currentPath, List<String[]> allPaths) {
	    visited[current] = true;
	    currentPath[pathLength] = city[current];

	    if (current == destination) {
	        allPaths.add(Arrays.copyOf(currentPath, pathLength + 1));
	    } else {
	        for (int i = 0; i < table[current].length; i++) {
	            if (table[current][i] != Integer.MAX_VALUE && !visited[i]) {
	                findAllPaths(i, destination, table, city, visited, pathLength + 1, currentPath, allPaths);
	            }
	        }
	    }

	    visited[current] = false;
	}
	private static int calculatePathCost(int[][] table, String[] path) {
	    int cost = 0;
	    for (int i = 0; i < path.length - 1; i++) {
	        int city1Index = getIndex(path[i]);
	        int city2Index = getIndex(path[i + 1]);
	        cost += table[city1Index][city2Index];
	    }
	    return cost;
	}


	private static int getIndex(String[] city, String cityName) {
		for (int i = 0; i < city.length; i++) {
			if (city[i].equals(cityName)) {
				return i;
			}
		}
		return -1; // Return -1 if the city name is not found
	}


	private static int getIndex(String cityName) {
		// Assuming the city array contains unique city names
		for (int i = 0; i < city.length; i++) {
			if (city[i].equals(cityName)) {
				return i;
			}
		}
		return -1;
	}

	static class Cost {
		private String path;
		private int cost;

		public Cost(String path, int cost) {
			this.path = path;
			this.cost = cost;
		}

		public String getPath() {
			return path;
		}

		public int getCost() {
			return cost;
		}
	}

}
