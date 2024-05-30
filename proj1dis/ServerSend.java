//package proj1dis;
//
//import java.net.*;
//import java.util.Scanner;
//import java.io.*;
//
//public class ServerSend implements Runnable {
//    private Socket cs;
//
//    ServerSend(Socket cs) {
//        this.cs = cs;
//    }
//
//    @Override
//    public void run() {
//        try {
//            PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
//            Scanner input = new Scanner(System.in);
//
//            while (true) {
//                String message = input.nextLine();
//                out.println(message);
//                System.out.println(message + " has been sent");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error sending .. ");
//        }
//    }
//}
//
