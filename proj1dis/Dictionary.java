package proj1dis;

import java.io.*;
import java.net.*;
import java.util.*;

public class Dictionary {
    private static List<ServerInfo> servers = new ArrayList<>();
    private static int Index = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println("this 5050 port in Dictionary process ");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new DictionaryHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class DictionaryHandler implements Runnable {
        private Socket socket;

        DictionaryHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String message = in.readLine();
                if (message.matches("\\d+")) {
                   
                    int port = Integer.parseInt(message); // message from server
                    servers.add(new ServerInfo(socket.getInetAddress().getHostAddress(), port));
                    System.out.println("Registered server: " + socket.getInetAddress().getHostAddress() + ":" + port);
                } else if (message.equals("getServer")) {// Request from client to get a server
                    if (servers.size() == 0) {
                        out.println("No servers !!!");
                    } else {
                        ServerInfo serverInfo = servers.get(Index);
                        Index = (Index + 1) % servers.size();
                        out.println(serverInfo.IP + " " + serverInfo.Port);
                    }
                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class ServerInfo {
        String IP;
        int Port;

        ServerInfo(String IP, int Port) {
            this.IP = IP;
            this.Port = Port;
        }
    }
}
