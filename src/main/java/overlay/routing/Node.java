package overlay.routing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import overlay.wireformats.RegisterMessageFormat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Node extends Thread {
    private Socket  socketToServer;
    private Integer port;
    private String  fqdn;
    
    public Node(Socket socketToServer) {
        this.socketToServer = socketToServer;
        InetAddress fqdnAddress = socketToServer.getLocalAddress();
        this.fqdn = fqdnAddress.getHostName();
        this.port = socketToServer.getLocalPort();
    }

    public void run() {
        try {
            System.out.println("Node: " + this.fqdn + ":" + this.port + " connected");

            DataOutputStream serverOutputStream = new DataOutputStream( new BufferedOutputStream(socketToServer.getOutputStream()));
            DataInputStream serverInputStream = new DataInputStream(new BufferedInputStream(socketToServer.getInputStream()));

            RegisterMessageFormat registrationRequest = new RegisterMessageFormat(fqdn, port);
            byte[] marshalledRegMsg = registrationRequest.getBytes();
            serverOutputStream.writeInt(registrationRequest.type);
            serverOutputStream.writeInt(marshalledRegMsg.length);
            serverOutputStream.write(marshalledRegMsg);

            serverOutputStream.close();
            serverInputStream.close();
            socketToServer.close();
        } catch (IOException e) {
            System.out.println("Error Node: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
