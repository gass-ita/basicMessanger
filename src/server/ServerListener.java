package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ServerListener implements Runnable{
    private Socket client;
    private static BufferedReader in;



    public ServerListener(Socket client) throws IOException{
        this.client = client;
        in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
    }

    @Override
    public void run() {
        while(!client.isClosed()){
            try {
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
