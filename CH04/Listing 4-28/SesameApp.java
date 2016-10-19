package sesamePackage;

import info.aduna.iteration.Iterations;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Model;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.Rio;

public class SesameApp {

	public static void main(String[] args) throws RepositoryException, RDFHandlerException {
		
		Repository rep = new SailRepository(new MemoryStore());
		rep.initialize();
		
		String namespace = "http://example.com/";
		ValueFactory f = rep.getValueFactory();
		
		URI leslie = f.createURI(namespace, "leslie");
		
		RepositoryConnection conn = rep.getConnection();
		try {
			conn.add(leslie, RDF.TYPE, FOAF.PERSON);
			conn.add(leslie, RDFS.LABEL, f.createLiteral("Leslie", XMLSchema.STRING));
			
			RepositoryResult<Statement> statements = conn.getStatements(null,  null,  null,  true);
			
			Model model = Iterations.addAll(statements, new LinkedHashModel());
			model.setNamespace("rdf", RDF.NAMESPACE);
			model.setNamespace("rdfs", RDFS.NAMESPACE);
			model.setNamespace("xsd", XMLSchema.NAMESPACE);
			model.setNamespace("foaf", FOAF.NAMESPACE);
			model.setNamespace("ex", namespace);
			
			Rio.write(model,  System.out, RDFFormat.TURTLE);
		}
		finally {
			conn.close();
		}

	}

}
