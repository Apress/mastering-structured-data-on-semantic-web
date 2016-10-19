import com.franz.agbase.*;
public class AGTripleStoreInfo {
    public static void main(String[] args) throws AllegroGraphException {
        AllegroGraphConnection ags = new AllegroGraphConnection();
        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem", e);
        }
       
	   // Create a new triplestore
        AllegroGraph ts = ags.renew("tsinfo", AGPaths.TRIPLE_STORES);

        // Show information about the triplestore
        showTripleStoreInfo(ts);
        
        // Close the triplestore and disconnect from the server
        ts.closeTripleStore();
        ags.disable();
    }

    public static void showTripleStoreInfo(AllegroGraph ts) throws AllegroGraphException {
        System.out.println("  TRIPLESTORE INFO ");       
        System.out.println("Version: " + AllegroGraph.version());
        System.out.println("NumberOfTriples: " + ts.numberOfTriples());
        System.out.println("UnindexedTripleCount: " + ts.getUnindexedTripleCount());
        System.out.println("UnindexedThreshold: " + ts.getUnindexedThreshold());
        System.out.println("SelectLimit: " + ts.getSelectLimit());
        System.out.println("LookAhead: " + ts.getLookAhead());
        System.out.println("UnmergedCount: " + ts.getUnmergedCount());
        System.out.println("UnmergedThreshold: " + ts.getUnmergedThreshold());
        AGUtils.printStringArray("IndexFlavors: ", ts.getIndexFlavors());
        AGUtils.printStringArray("DataMapping: ", ts.getDataMapping());
        AGUtils.printStringArray("Namespace Registry:", ts.getNamespaces());
        String[] fp = ts.getFreetextPredicates();
        if (fp!=null) AGUtils.printStringArray("Freetext Predicates:", fp);
    }
}