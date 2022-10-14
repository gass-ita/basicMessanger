package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListener implements Runnable{

    private Socket server;
    private static BufferedReader in;



    public ClientListener(Socket server) throws IOException{
        this.server = server;
        in = new BufferedReader(new InputStreamReader(this.server.getInputStream()));
    }

    @Override
    public void run() {
        while(!server.isClosed()){
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    
}
