import com.franz.agbase.*;
public class AGSparqlSelect {
    public static void main(String[] args) throws AllegroGraphException {

	// Connect to server which is running
        AllegroGraphConnection ags = new AllegroGraphConnection();
        try {
            ags.enable();
        } catch (Exception e) {
            throw new AllegroGraphException("Server connection problem", e);
        }
        
        // Create a new triplestore
        AllegroGraph ts = ags.renew("sparqlselect", AGPaths.TRIPLE_STORES);
        
        // Register a namespace
        ts.registerNamespace("ex","http://example.org/");
        
        // Add a few triples to the store
        ts.addStatement("!ex:a","!ex:p", "!ex:b");
        ts.addStatement("!ex:b","!ex:p", "!ex:c");
        
        // A simple SPARQL SELECT query
        String query = "SELECT * {?s ?p ?o}";
        
        // Create a SPARQLQuery object
        SPARQLQuery sq = new SPARQLQuery();
        sq.setTripleStore(ts);
        sq.setQuery(query);

        // Do the SELECT query and show results
        doSparqlSelect(sq);
    }
    public static void doSparqlSelect(SPARQLQuery sq) throws AllegroGraphException {
        if (sq.isIncludeInferred()) {
            System.out.println("\nQuery (with RDFS++ inference):");
        } else {
            System.out.println("\nQuery:");         
        }
        System.out.println("  " + sq.getQuery());
        ValueSetIterator it = sq.select();
        AGUtils.showResults(it);
    }
}