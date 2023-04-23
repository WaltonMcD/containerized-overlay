package overlay.main_helpers;

import java.util.Scanner;
import java.util.ArrayList;

import overlay.routing.Registry;

public class startRegistry {

    Scanner input = new Scanner(System.in);
    Integer serverPort = 0;
    Integer numOfConnections = 0;
    Boolean setupComplete = false;
    String command = "";

    public startRegistry(Integer serverPort, Integer numOfConnections){
        this.serverPort = serverPort;
        this.numOfConnections = numOfConnections;
    }

    public void startNewRegistry(){
        // Error check command entries
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
                    Thread registryThread = new Thread(registry);
                    registryThread.start();
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
            else if(command.equals("start")) {
                if(setupComplete) {
                    System.out.print("Number of Messages to Send: ");
                    String numOfMessagesToSend = input.nextLine().trim().toLowerCase();
                    if(numOfMessagesToSend.matches("0|[1-9]\\d*")) {
                        Integer numberOfMessages = Integer.parseInt(numOfMessagesToSend);
                        System.out.println("Starting to send messages. Count: " + numberOfMessages);
                    }
                    else{
                        System.out.println("Invalid entry for number of messages. Skipping... ");
                    }
                }
                else {
                    System.out.println("Please setup the overlay first.");
                }
            }
        }

        // Clean Up
        input.close();
    }
    
}