package de.hexagonsoftware.sbec;

import de.hexagonsoftware.abec.text.Logger;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ClientThread implements Runnable {
    /**
     * Socket for Server-Client communication
     * */
    private Socket socket;
    /**
     * UUID for connection to the User it represents
     * */
    private UUID userId;
    private PrintWriter writer;
    private BufferedReader reader;

    private Thread thread;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        this.thread = new Thread(this, userId.toString());
        this.thread.start();
    }

    public void run() {
        try {
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            requestUserName();

            while (!socket.isClosed()) {
                char[] buffer = new char[200];
                int anzahlZeichen = reader.read(buffer, 0, 200);
                System.out.println(new String(buffer, 0, anzahlZeichen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ServerThread.CLIENTS.remove(this.socket.getRemoteSocketAddress());
        ServerThread.USERS.remove(userId);
        Logger.info("User "+userId+" disconnected and was deregistered!");
    }

    private void requestUserName() {
        String name = requestInput("NAME");
        ServerThread.USERS.get(userId).NAME = name;
    }

    public void sendMessage(String msg) {
        writer.write("<MSG>"+msg);
        writer.flush();
    }

    public String requestInput(String msg) {
        writer.write("!"+msg);
        writer.flush();

        try {
            char[] buffer = new char[200];
            int anzahlZeichen = reader.read(buffer, 0, 200);
            return new String(buffer, 0, anzahlZeichen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void assignUUID(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return this.userId;
    }
}
