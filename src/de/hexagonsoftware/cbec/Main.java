package de.hexagonsoftware.cbec;

import java.net.*;
import java.io.*;

public class Main {
    private Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) throws IOException {
         Main main = new Main();
         main.startConnection("84.188.211.238", 420);
         while (true){
             char[] buffer = new char[200];
             int anzahlZeichen = in.read(buffer, 0, 200);
             System.out.println(new String(buffer, 0, anzahlZeichen));
         }
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
