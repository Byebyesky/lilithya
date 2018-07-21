    
public class Action {

    MyListener listener = null;

    Action(MyListener listen) {
        listener = listen;
    }

    public void executeAction(String[] action) {
        switch(action[0].toLowerCase()) {
            case "logstatus":   System.out.print("Log is: " + listener.logIsEnabled() + "\n");
                                break;

            case "togglelog":   listener.logToggle();
                                System.out.print("Log is now: " + listener.logIsEnabled() + "\n");
                                break;

            case "showlog":     System.out.println("To Be Implemented");
                                break;

            case "sendmessage":
            case "sm":          try {
                                listener.sendMessage(action[2], listener.discoveredChannels.get(Integer.parseInt(action[1]))); }
                                catch(IndexOutOfBoundsException e) {
                                    System.out.println("An error occured while sending: " + e);
                                }
                                break;
            
            case "spm":
            case "sendpmessage": try {
                                    listener.sendMessage(action[2], listener.discoveredDMs.get(Integer.parseInt(action[1]))); }
                                catch(IndexOutOfBoundsException e) {
                                    System.out.println("An error occured while sending: " + e);
                                }
                                break;

            case "lc":                    
            case "listchannel": listener.listDiscoveredChannels();
                                break;

            case "help":        System.out.println("logstatus, togglelog, showlog, sendmessage(sm), sendpmessage(spm), listchannel(lc), help, exit");
                                break;

            case "exit":        System.out.print("Exiting...");
                                System.exit(0);
                                break;

            default:            System.out.println("Invalid command: " + action[0]);

        }
    }
}