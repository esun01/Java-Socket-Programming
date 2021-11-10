package lab4.lab4_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2002);

            DataInputStream input = new DataInputStream(socket.getInputStream());
            ThreadForClient clientThread = new ThreadForClient(socket);
            clientThread.start();

        } catch (Exception e) {
            System.out.println("Interrupt Client Disconnected");
        }
    }
}
