package proj1dis;

import java.net.*;
import java.io.*;

public class Server {
    private static String dictHost = "localhost";
    private static int dictPort = 5050;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please selected the server port!!!.");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            
            Socket dictSocket = new Socket(dictHost, dictPort);// Connect to Dictionary Process
            PrintWriter dictOut = new PrintWriter(dictSocket.getOutputStream(), true);
            dictOut.println(port); 
            dictSocket.close();

            
            ServerSocket serverSocket = new ServerSocket(port);//  client connections
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                ServerRec serverRec = new ServerRec(clientSocket);
                Thread t = new Thread(serverRec);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
