import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jDemo
{
  private static final String DB_PATH = "target/neo4jdemodb";
  public String rdfstatement;
  GraphDatabaseService graphDb;
  Node firstNode;
  Node secondNode;
  Relationship relationship;

  private static enum RelTypes implements RelationshipType
  {
    WEBSITE_OF
  }

  public static void main(final String[] args)
  {
    Neo4jDemo dbsample = new Neo4jDemo();
    dbsample.createDb();
    dbsample.shutDown();
  }

  void createDb()
  {

    graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);

    try ( Transaction tx = graphDb.beginTx() )
    {
      firstNode = graphDb.createNode();
      firstNode.setProperty("uri", "http://dbpedia.org/resource/Leslie_Sikos");
	  firstNode.setProperty("label", "Leslie Sikos");
      secondNode = graphDb.createNode();
      secondNode.setProperty("uri", "http://www.lesliesikos.com");
	  secondNode.setProperty("label", "website address");
      relationship = firstNode.createRelationshipTo(secondNode, RelTypes.WEBSITE_OF);
      relationship.setProperty("uri", "http://schema.org/url");
	  relationship.setProperty("label", "website");

	  System.out.print(secondNode.getProperty("uri") + " is the ");
	  System.out.print(relationship.getProperty("label") + " of ");
      System.out.print(firstNode.getProperty("label"));
    
      tx.success();
    }
  }
   
  void shutDown()
  {
    System.out.println();
    System.out.println("Shutting down database…");
    graphDb.shutdown();
  }

}