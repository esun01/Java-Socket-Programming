package lab4.lab4_2;

import lab4.lab4_3.ThreadForServer;

import java.io.*;
import java.net.*;

class Server2 {
    static class MultipleClients extends Thread {
        private ServerSocket serverSocket;


        MultipleClients(int port) throws IOException {
            serverSocket = new ServerSocket(port);
        }

        public void run() {
            String string = "";

            try {
                Socket server = serverSocket.accept();
                DataInputStream inputFromClient = new DataInputStream(server.getInputStream());
                while (true) {
                    string = inputFromClient.readUTF();
                    System.out.println("Client says " + string);
                    if (string.equals("*Close*")) {
                        server.close();
                        System.out.println("Client Disconnected");
                        break;
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 5001;

        Thread thread = new MultipleClients(port);
        thread.start();


    }
}
