package proj1dis;

import java.net.*;
import java.io.*;

public class ServerRec implements Runnable {
    private Socket cs;

    ServerRec(Socket cs) {
        this.cs = cs;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);

            while (true) {
                String message = in.readLine();
                if (message == null) break; // connection closed
                System.out.println("Received: " + message);

                String[] parts = message.split(" ");
                String command = parts[0];
                int number = Integer.parseInt(parts[1]);

                if (command.equals("isPrime")) {
                    boolean isPrime = isPrime(number);
                    out.println(isPrime);
                } else if (command.equals("nextPrime")) {
                    int nearestPrime = findNearestPrime(number);
                    out.println(nearestPrime);
                }
            }

            
            cs.close();// Close the socket 
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }

    // method to check is prime number or not 
    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
    // method ot find next prime
    private int findNearestPrime(int number) {
        while (true) {
            number++;
            if (isPrime(number)) {
                return number;
            }
        }
    }
}
