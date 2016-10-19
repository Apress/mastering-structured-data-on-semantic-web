// Listing 9-6. Test Connection to DBpedia’s SPARQL Endpoint

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;

public class QueryTest {
  public static void main(String[] args) {
    String service = "http://dbpedia.org/sparql";
    String query = "ASK { }";
    QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
    try {
      if (qe.execAsk()) {
        System.out.println(service + " is UP");
        }
      } catch (QueryExceptionHTTP e) {
        System.out.println(service + " is DOWN");
      } finally {
      qe.close();
    }
  }
}

// Listing 9-7. A SPARQL Query to Run on DBpedia from Jena

String service="http://dbpedia.org/sparql";
String query="PREFIX dbo:<http://dbpedia.org/ontology/>"
 + "PREFIX : <http://dbpedia.org/resource/>"
 + "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
 + "select ?person ?name where {?person dbo:birthPlace : Eisenach."
 + "?person foaf:name ?name}";
QueryExecution qe=QueryExecutionFactory.sparqlService(service, query);
ResultSet rs=qe.execSelect();
while (rs.hasNext()){
  QuerySolution s=rs.nextSolution();
  Resource r=s.getResource("?person");
  Literal name=s.getLiteral("?name");
  System.out.println(s.getResource("?person").toString());
  System.out.println(s.getLiteral("?name").getString());
}
