package overlay.routing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Registry extends Thread {
    
    ServerSocket serverSocket;
    
    Integer port;
    Integer numConnections;
    Boolean complete;
    
    public Registry(Integer port, Integer numConnections) {
        try {
            serverSocket = new ServerSocket(port);
            this.numConnections = numConnections;
            this.complete = false;

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
    
