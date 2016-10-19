// Listing 4-1. Constant Declaration in Jena

static String personWebsite  = "http://www.lesliesikos.com";
static String personName     = "Leslie Sikos";

// Listing 4-2. Creating a Memory-Based Model

Model model = ModelFactory.createDefaultModel();
The resource will be created using the Model (Listing 4-3).

// Listing 4-3. Creating a Resource

Resource lesliesikos = model.createResource(personWebsite);
Finally, add a property to the resource using addProperty (Listing 4-4).

// Listing 4-4. Adding Property to a Resource

lesliesikos.addProperty(FOAF.Name, personName);
To retrieve statements from an RDF graph (Jena Model), the listStatements() method can be used (Listing 4-5).

// Listing 4-5. Extracting RDF Triples

StmtIterator iter = model.listStatements();
If you need more details, you can list all the predicated, subjects, and objects from the RDF graph as shown in Listing 4-6.

// Listing 4-6. Listing All Triple Components Individually

while (iter.hasNext()) {
  Statement stmt      = iter.nextStatement(); 
  Resource  subject   = stmt.getSubject();
  Property  predicate = stmt.getPredicate();
  RDFNode   object    = stmt.getObject();

  System.out.print(subject.toString());
  System.out.print(" " + predicate.toString() + " ");
  if (object instanceof Resource) {
    System.out.print(object.toString());
  } else {
    System.out.print(" \"" + object.toString() + "\"");
  }

  System.out.println(" .");
}