import com.franz.agbase.*;
import com.franz.agbase.AllegroGraph.StoreAttribute;
public class AGOpenTripleStore {
    public static void main(String[] args) throws AllegroGraphException {
        
        // Connect to the server, which is running
        AllegroGraphConnection ags = new AllegroGraphConnection();
        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem.", e);
        }

        // Access a triplestore (open a triplestore, creates it if necessary)
        AllegroGraph ts = ags.access("existingstore", AGPaths.TRIPLE_STORES);
        ts.closeTripleStore();
        
        // Open a triplestore (open an existing store, error otherwise)
        ts = ags.open("existingstore", AGPaths.TRIPLE_STORES);
        System.out.println("Triplestore opened with " + ts.numberOfTriples() + " triples.");
        
        // Close the triplestore
        ts.closeTripleStore(true);

        // Open a triplestore in read-only mode
        ts = new AllegroGraph(AGPaths.TRIPLE_STORES + "existingstore"); 
        ts.setAttribute(StoreAttribute.READ_ONLY, true);
        ags.open(ts);

        // Disconnect from the server
        System.out.println("Disconnecting from the server.");
        ags.disable();
        System.out.println("Done.");
    }
}