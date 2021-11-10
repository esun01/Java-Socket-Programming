package lab4.lab4_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadForServer extends Thread {
    private Socket socket;
    private ArrayList<ThreadForServer> threadList;
    private DataOutputStream output;
    private DataInputStream input;

    public ThreadForServer(Socket socket, ArrayList<ThreadForServer> serverSockets) throws IOException {
        this.threadList = serverSockets;
        this.socket = socket;
        input = new DataInputStream(socket.getInputStream());
    }

    public void run() {
        try {
            String func;
            output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF("Hi Client" + Thread.currentThread().getId());
            output.writeUTF("1.Broadcast\n2.Private\n3.Group");
            func = input.readUTF();
            functionReader(func);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void functionReader(String s) throws IOException {
        if (s.equals("1")) {
            output.writeUTF("You choose Broadcast");
            while (true) {
                try {
                    String fromUser = input.readUTF();

                    //System.out.println(threadList.get(0).getId());
                    System.out.println("Client" + Thread.currentThread().getId() + " : " + fromUser);
                    broadcast(fromUser);

                } catch (IOException e) {
                    System.out.println("Client Disconnected");
                    break;
                }
            }
        } else if (s.equals("2")) {
            boolean userfound = false;
            while (true) {
                output.writeUTF("You choose Private\nSend to who ?");
                String UserNumber = input.readUTF();
                int tid = 0;
                for (int i = 0; i < threadList.size(); i++) {
                    if (threadList.get(i).getId() == Integer.parseInt(UserNumber) && Integer.parseInt(UserNumber) != Thread.currentThread().getId()) {
                        userfound = true;
                        tid = i;
                        break;
                    }
                }
                if (userfound) {
                    output.writeUTF("User Found");
                    privateMessage(threadList.get(tid));
                    break;
                } else {
                    output.writeUTF("User Not Found");
                }
            }

            //private
        } else if (s.equals("3")) {
            //group
        }
    }

    private void broadcast(String string) throws IOException {
        for (ThreadForServer thread : threadList) {
            thread.output.writeUTF(string);
        }
    }

    private void privateMessage(ThreadForServer thread) throws IOException {
        thread.output.writeUTF("Client" + Thread.currentThread() + "send private message : Hello");
        while (true) {
            String message = input.readUTF();
            thread.output.writeUTF("Client" + Thread.currentThread() + "send private message : " + message);
        }
    }

    private void groupMessage(ThreadForServer thread, String s) {

    }

}
