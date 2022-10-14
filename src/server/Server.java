package server;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    public static final int PORT = 6900;
    private static BufferedReader in;
    private static PrintWriter out;
    private static  Executor pool = Executors.newFixedThreadPool(1); 
    private static ArrayList<ServerListener> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        ServerSocket listener = new ServerSocket(PORT);
        Socket client;

        try{
            while(true){
                client = listener.accept();
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                System.out.println("[SERVER] connected to the client " + client.getRemoteSocketAddress());
                out.println("connected");
                ServerListener clientThread = new ServerListener(client); 
                clients.add(clientThread);
                pool.execute(clientThread);
                pool = Executors.newFixedThreadPool(clients.size() + 1); 
            }
        } finally {
            listener.close();
            in.close();
            out.close();
        }
        
        
        
        



    }
}
