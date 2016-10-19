import com.franz.agbase.*;

public class AGCreateTripleStore {

    public static void main(String[] args) throws AllegroGraphException {
        
        // Connect to the server, which is running
        AllegroGraphConnection ags = new AllegroGraphConnection();

        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem.", e);
        }

        // Create a triple store
        System.out.println("Attempting to create a triple store.");
        try {
            AllegroGraph ts = ags.create("nonexistingstore", AGPaths.TRIPLE_STORES);
            System.out.println("Triple store created.");
        
            // Close the triple store
            System.out.println("Closing the triple store.");
            ts.closeTripleStore();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Disconnect from the server
        System.out.println("Disconnecting from the server.");
        ags.disable();
        System.out.println("Done.");  
    }
}