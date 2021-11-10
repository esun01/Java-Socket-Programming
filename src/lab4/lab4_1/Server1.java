package lab4.lab4_1;

import java.io.*;
import java.net.*;

public class Server1 extends Thread {
    private final ServerSocket serverSocket;

    public Server1(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for Client on port " + serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just Connect to" + server.getRemoteSocketAddress());
                while (true) {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(server.getInputStream());
                        String s = dataInputStream.readUTF();
                        System.out.println(s);
                    }catch (IOException ioException){
                        System.out.println("Client Disconnected");
                        break;
                    }
                }
                //server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket time out\n");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 5678;
        Thread thread = new Server1(port);
        thread.start();
    }
}
