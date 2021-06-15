package de.hexagonsoftware.sbec;

import de.hexagonsoftware.abec.text.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.UUID;

public class ServerThread implements Runnable {
    private boolean running = false;

    private ServerSocket server;
    private Thread thread;

    public static HashMap<SocketAddress, ClientThread> CLIENTS = new HashMap<>();
    public static HashMap<UUID, User> USERS = new HashMap<>();

    public void start(int port) throws IOException{
        this.server = new ServerSocket(port);
        this.thread = new Thread(this, "SERVER");
        this.thread.start();
    }

    public void run() {
        running = true;
        try {
            while (running) {
                Socket socket = server.accept();
                CLIENTS.put(socket.getRemoteSocketAddress(), new ClientThread(socket));
                createUser(CLIENTS.get(socket.getRemoteSocketAddress()));

                Logger.info("Registered new Client and Created user for: "+socket.getRemoteSocketAddress().toString()+", "+CLIENTS.get(socket.getRemoteSocketAddress()).getUserId());

                CLIENTS.get(socket.getRemoteSocketAddress()).start();
            }
        } catch (IOException e) {
            running = false;
        }
    }

    private void createUser(ClientThread client) {
        UUID uuid = UUID.randomUUID();
        client.assignUUID(uuid);

        User user = new User();
        USERS.put(uuid, user);
    }
}
