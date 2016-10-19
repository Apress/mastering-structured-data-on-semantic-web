package JenaPackage;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

public class Main {
	public static void main(String args[])
	{
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("C:/develop/eclipse/workspace/jenaapp/src/jenapackage/foaf.rdf");
		model.write(System.out,"TURTLE");
	}
}
