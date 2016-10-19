import com.franz.agbase.*;
public class AGCreatingTriples {
    public static void main(String[] args) throws AllegroGraphException {
 
		// Connect to server, which is running
        AllegroGraphConnection ags = new AllegroGraphConnection();
        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem", e);
        }

        // Create a new triplestore
        AllegroGraph ts = ags.renew("creatingtriples", AGPaths.TRIPLE_STORES);
        
        // Create a triple directly in N-Triples format and add it to the store
		
		ts.addStatement("<http://www.lesliesikos.com/datasets/sikos.rdf#sikos>",  "<http://xmlns.com/foaf/0.1/homepage>",  "<http://www.lesliesikos.com>");

        System.out.println("Created and added the following triple to the store:");
        AGUtils.showTriple(tr);
        
        // Close the store and disconnect from the server
        ts.closeTripleStore();
        ags.disable();
    }
}