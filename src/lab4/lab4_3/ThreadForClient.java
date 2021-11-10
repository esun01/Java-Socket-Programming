package lab4.lab4_3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadForClient extends Thread {
    private Socket socket;
    private DataInputStream input;

    public ThreadForClient(Socket socket) throws IOException {
        this.socket = socket;
        input = new DataInputStream(socket.getInputStream());
    }

    public void run() {
        String getLine;
        String getFunc;

        try {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            getLine = input.readUTF();
            System.out.println(getLine);
            getFunc = input.readUTF();
            System.out.println(getFunc);
            while (true) {
                String functionChoose = scanner.nextLine();
                output.writeUTF(functionChoose);
                System.out.println("Function : " + functionChoose);
                System.out.println(input.readUTF());

                if (functionChoose.equals("1")) {
                    while (true) {
                        String s = scanner.nextLine();
                        if (s.equals("close")) {
                            output.writeUTF("close");
                            throw new IOException();
                        } else {
                            output.writeUTF(s);
                            String read = input.readUTF();
                            System.out.println(read);
                        }
                    }
                }

                if (functionChoose.equals("2")) {
                    while (true) {
                        output.writeUTF(scanner.nextLine());
                        String s = input.readUTF();
                        if (s.equals("User Not Found")){
                            System.out.println(s);
                            System.out.println(input.readUTF());
                        }else {
                            System.out.println(s);
                        }
                    }
                }

            }

        } catch (IOException e) {
            System.out.println("Client Close");
        }
    }

}
