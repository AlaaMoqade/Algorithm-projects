package proj1dis;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
    private static String dictHost = "localhost";
    private static int dictPort = 5050;

    public static void main(String[] args) {
        try {
        	
            // Connect with Dictionary Process and get a server address
        	
            Socket dictSocket = new Socket(dictHost, dictPort);
            BufferedReader dictIn = new BufferedReader(new InputStreamReader(dictSocket.getInputStream()));
            PrintWriter dictOut = new PrintWriter(dictSocket.getOutputStream(), true);
            dictOut.println("getServer");

            String serverResponse = dictIn.readLine();
            dictSocket.close();

            if (serverResponse.equals("No available servers")) {
                System.out.println("No available servers!!!");
                return;
            }

            String[] serverpart = serverResponse.split(" ");
            String serverHost = serverpart[0];
            int serverPort = Integer.parseInt(serverpart[1]);

            Socket serverSocket = new Socket(serverHost, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            Scanner read = new Scanner(System.in);

            while (true) {
                System.out.print("Enter integer number : ");
                if (!read.hasNextInt()) {
                    System.out.println("Invalid input!!! Please Enter number:");
                    read.next(); 
                    continue;
                }
                int num = read.nextInt();

                out.println("isPrime " + num);
                String isPrimeResponse = in.readLine();
                System.out.println("Is prime: " + isPrimeResponse);

                out.println("nextPrime " + num);
                String nextPrimeResponse = in.readLine();
                System.out.println("Next prime: " + nextPrimeResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
