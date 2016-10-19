import com.franz.agbase.*;

public class AGConnecting {

    public static void main(String[] args) throws AllegroGraphException {
        
        // Connect to the default server, which is running
        AllegroGraphConnection ags = new AllegroGraphConnection();

        try {

			// If you use a port other than the default port:
            //ags.setPort(4126);

            System.out.println("Attempting to connect to the server on port " + ags.getPort());
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem. Server might be down.", e);
        }

        System.out.println("Connection enabled.");
        
        // Disconnect from the server
		
        System.out.println("Disconnecting from the server.");
        ags.disable();
        System.out.println("Done.");
        
    }
    
}