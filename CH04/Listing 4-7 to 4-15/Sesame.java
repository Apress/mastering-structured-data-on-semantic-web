// Listing 4-7. Creating a Basic Local Repository in Sesame

import org.openrdf.repository.Repository;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

// …

Repository repo = new SailRepository(new MemoryStore());
repo.initialize();

// Listing 4-8. Creating a Local Repository With File Storage in Sesame

import org.openrdf.repository.Repository;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.nativerdf.NativeStore;

// …

File dataDir = new File("/path/to/datadir/");
Repository repo = new SailRepository(new NativeStore(dataDir));
repo.initialize();

// Listing 4-9. Creating a Repository With RDF Schema Inferencing

import org.openrdf.repository.Repository;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;

// …

Repository repo = new SailRepository(
  new ForwardChainingRDFSInferencer(
  new MemoryStore()));
repo.initialize();

// Listing 4-10. Initializing a RemoteRepositoryManager

import org.openrdf.repository.manager.RemoteRepositoryManager;
 
String serverUrl = "http://localhost:8080/openrdf-sesame";
RemoteRepositoryManager manager = new RemoteRepositoryManager(serverUrl);
manager.initialize();

// Listing 4-11. Using a Default ValueFactory Implementation

ValueFactory factory = ValueFactoryImpl.getInstance();
Once you obtained your ValueFactory, you can create new URIs, literals, and triples (Listing 4-12).

// Listing 4-12. Adding URIs, Literals, and Triples to a ValueFactory Implementation

URI webstand = factory.createURI("http://yourbookdetaset.com/webstand");
URI title = factory.createURI("http://yourbookdetaset.com/title");
Literal webstandsTitle = factory.createLiteral("Web Standards");
Statement titleStatement = factory.createStatement(webstand, title, webstandsTitle);

// Listing 4-13. Creating an Empty Graph

Graph myGraph = new org.openrdf.model.impl.GraphImpl();

// Listing 4-14. Adding Triple Support to a Graph

ValueFactory myFactory = myGraph.getValueFactory();
String namespace = "http://www.foo.com/bar#";

URI mySubject = myFactory.createURI(namespace, "WebDesignBook");
URI myPredicate = myFactory.createURI(namespace, "Title");
Literal myObject = myFactory.createLiteral("Web Standards");

myGraph.add(mySubject, myPredicate, myObject);

// Listing 4-15. Using URIs Directly to Add Triples to a Graph

URI bookClass = myFactory.createURI(namespace, "Book");
URI rdfType = myFactory.createURI(org.openrdf.vocabulary.RDF.TYPE);
mySubject.addProperty(rdfType, bookClass);