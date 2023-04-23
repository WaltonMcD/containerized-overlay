package overlay;

// Exceptions
import java.io.IOException;
import java.net.UnknownHostException;

// Standard Classes
import java.util.Scanner;
import java.net.Socket;
import java.util.ArrayList;

// Custom Classes
//import overlay.node.Node;
import overlay.routing.Registry;
//import overlay.routing.RegistryThread;

public class Main{
    public static void main(String[] args) {
      
        if(args[0].equals("server")) {

            Scanner input = new Scanner(System.in);
            Integer serverPort = Integer.parseInt(args[1]);
            Integer numOfConnections = Integer.parseInt(args[2]);
            Boolean setupComplete = false;
            String command = "";

            // Rrror check command entries
            ArrayList<String> possibleCommands = new ArrayList<String>();
            possibleCommands.add("setup-overlay");
            possibleCommands.add("exit-overlay");
            possibleCommands.add("list-messaging-nodes");
            possibleCommands.add("start");

            System.out.println("\nWelcome to the Overlay!");
            System.out.println("Possible commands include:\n- setup-overlay\n- exit-overlay\n- list-messaging-nodes\n- start");

            while(! command.equals("exit-overlay")) {
                System.out.print("\nEnter a command: ");
                command = input.nextLine().trim().toLowerCase();
                System.out.println("\nCommand Entered: " + command);

                if(! possibleCommands.contains(command)) {
                    System.out.println("Command not found... ");
                }
                else if(command.equals("exit-overlay")) {
                    System.out.println("Exiting... ");
                }
                else if(command.equals("setup-overlay")) {
                	if(! setupComplete) {
                        System.out.println("Starting Overlay... ");
                        Registry registry = new Registry(serverPort, numOfConnections);
	                    Thread overlayThread = new Thread(registry);
	                    overlayThread.start();
	                    setupComplete = true;
                        System.out.println("Overlay setup complete... \nWaiting for start directive...");
                    }
                    else{
                        System.out.println("Overlay has already been setup. Skipping... ");
                    }
                }
                else if(command.equals("list-messaging-nodes")) {
                    if(setupComplete){
                        System.out.println("Message Nodes: ");
                    }
                    else{
                        System.out.println("Please setup the overlay first. Skipping... "); 
                    }
                }
                else if(command.substring(0,5).equals("start")) {
                    if(setupComplete) {
                        System.out.println("Starting... ");
                    }
                    else {
                        System.out.println("Please setup the overlay first.");
                    }
                }
            } 
        }
        else if(args[1].equals("node")) {
            Scanner input = new Scanner(System.in);
            String serverAddress = args[2];
            Integer serverPort = Integer.parseInt(args[1]);
            String command = "";
            Socket sock = null;

            try {
                sock = new Socket(serverAddress, serverPort);
                //Node node = new Node(sock);
                //Thread thread = new Thread(node);
                //thread.start();

                while(!command.equals("exit-overlay")){
                    command = input.next();
                }
                
                input.close();
                //thread.interrupt();
                sock.close();
            } 
            catch (UnknownHostException un) {
                System.out.println("Error Node: " + un.getMessage());
                un.printStackTrace();
            } 
            catch (IOException e) {
                System.out.println("Error Node: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}