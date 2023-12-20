package overlay.routing;

// Exceptions
import java.io.IOException;
import java.net.UnknownHostException;

// Standard Classes
import java.util.Scanner;
import java.net.Socket;

// Custom Classes
import overlay.routing.*;

public class startNode {

    String serverAddress = "";
    Integer serverPort = 0;
    String command = "";
    Socket sock = null;

    public startNode(String serverAddress, Integer serverPort){
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }
    
    public void startNewNode(){
        try {
            sock = new Socket(serverAddress, serverPort);
            Node node = new Node(sock);
            Thread thread = new Thread(node);
            thread.start();

            while(true){}
        } 
        catch (UnknownHostException un) {
            System.out.println("Error Node: " + un.getMessage());
            un.printStackTrace();
        } 
        catch (IOException ioe) {
            System.out.println("Error Node: " + ioe.getMessage());
            ioe.printStackTrace();
        }   
    }
}
