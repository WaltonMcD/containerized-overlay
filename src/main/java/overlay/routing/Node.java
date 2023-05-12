package overlay.routing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Node extends Thread {
    private Socket  socketToServer;
    private Integer port;
    private String  ip;
    
    public Node(Socket socketToServer) {
        this.socketToServer = socketToServer;
        InetAddress ipAddress = socketToServer.getLocalAddress();
        this.ip = ipAddress.getHostName();
        this.port = socketToServer.getLocalPort();
    }

    public void run() {
        try {
            System.out.println("Node: " + this.ip + ":" + this.port + " connected");
            socketToServer.close();
        } catch (IOException e) {
            System.out.println("Error Node: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
