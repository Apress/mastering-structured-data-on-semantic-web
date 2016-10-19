import com.franz.agbase.*;

public class AGDisconnecting {

    public static void main(String[] args) throws AllegroGraphException {
        
        // Connect to the default server, which is running
        System.out.println("Attempting to connect to the default server.");
        AllegroGraphConnection ags = new AllegroGraphConnection();
        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem -- please ensure the default server is running.", e);
        }

        System.out.println("Connected to the server on port " + ags.getPort());
        
        // Disconnect from the server
        System.out.println("Disconnecting from the server.");
        ags.disable();
        
        // Confirm disconnection
        System.out.println("Connection status enabled? " + ags.isEnabled());
        
        // Reconnect to the server
        System.out.println("Attempting to reconnect.");
        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem -- please ensure the default server is running.", e);
        }

        System.out.println("Reconnected to the server on port " + ags.getPort());
        
        // Show current server parameter settings
        AGServerInfo.showConnectionInstanceInfo(ags);
        
        // Disconnect from the server
        System.out.println("Disconnecting from the server.");
        ags.disable();
        System.out.println("Done.");
    }
}