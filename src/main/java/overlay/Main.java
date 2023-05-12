package overlay;

// Custom Classes
import overlay.routing.*;

public class Main{
    public static void main(String[] args) {
        if(args[0].equals("server")) {
            Integer serverPort = Integer.parseInt(args[1]);
            Integer numOfConnections = Integer.parseInt(args[2]);

            startRegistry registry = new startRegistry(serverPort, numOfConnections);
            registry.startNewRegistry();
             
        }
        else if(args[0].equals("node")) {
            Integer serverPort = Integer.parseInt(args[1]);
            String serverAddress = args[2];
            

            startNode node = new startNode(serverAddress, serverPort);
            node.startNewNode();
            

            
        }
    }
}