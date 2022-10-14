package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

    private static final String IP = "192.168.1.20";
    private static final int PORT = 6900;

    private static BufferedReader in;
    private static BufferedReader keyboard;
    private static PrintWriter out;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket toServer = new Socket(IP, PORT); 

        out = new PrintWriter(toServer.getOutputStream(), true);
        keyboard = new BufferedReader(new InputStreamReader(System.in));

        ClientListener l = new ClientListener(toServer);
        Thread tr = new Thread(l);
        tr.start();

        while(true){
            System.out.print(">");
            String command = keyboard.readLine();
            out.println(command);
        }
       
        

        
    }   
}
