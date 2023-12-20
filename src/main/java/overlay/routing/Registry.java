package overlay.routing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Registry extends Thread {
    
    ServerSocket serverSocket;
    
    Integer port;
    Integer numConnections;
    Boolean complete;
    ArrayList<Socket> nodeArray;
    
    public Registry(Integer port, Integer numConnections, ArrayList<Socket> nodeArray) {
        try {
            serverSocket = new ServerSocket(port);
            this.numConnections = numConnections;
            this.complete = false;
            this.nodeArray = nodeArray;

        } catch (IOException ioe) {
            System.out.println("Error Registry: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(!complete){
                Socket incomingConnectionSocket = this.serverSocket.accept();
                nodeArray.add(incomingConnectionSocket);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " detected interruption, exiting...");
                    incomingConnectionSocket.close();
                    return;
                }
                
                incomingConnectionSocket.setReuseAddress(true);
            }
            
        } catch (IOException ioe) {
            System.out.println("Error Registry: " + ioe.getMessage());
        }
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
    
