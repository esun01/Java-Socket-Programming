package lab4.lab4_3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ArrayList<ThreadForServer> threadList = new ArrayList<>();
            ServerSocket serverSocket = new ServerSocket(2002);
            while (true) {
                Socket socket = serverSocket.accept();
                ThreadForServer serverThread = new ThreadForServer(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
