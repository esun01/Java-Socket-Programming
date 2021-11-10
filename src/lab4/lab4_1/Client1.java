package lab4.lab4_1;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.*;

public class Client1 {
    public static void main(String[] args) throws IOException {
        String serverIP = "127.0.0.1";
        int port = 5678;
        System.out.println("Connecting to " + serverIP + "and Port: " + port);

        Socket client = new Socket(serverIP, port);
        System.out.println("Connecting to :" + client.getRemoteSocketAddress());
        while (true) {
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outToServer);
            for (int i = 0; i < input.length(); i++) {
                if (input.toCharArray()[i] == '@') {
                    input = "*";
                    break;
                }
            }

            dataOutputStream.writeUTF("Client says " + input);
        }
        //client.close();
    }
}
