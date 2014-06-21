package domtreegraphmlbuilder;

import java.io.FileNotFoundException;


public class Main {
	
	public static void main(String[] args) {
		
		HtmlImporter importer = new HtmlImporter("index.html");	
		Graph graph = new Graph(importer.getTagStack());
		
		try {
			GraphmlExporter.doExport(graph, "DOMtree.graphml");
			System.out.println("created DOMtree.graphml, open in yEd and run Layout > Hierarchical");
		} catch (FileNotFoundException e) {e.printStackTrace();}	
	}
	
}
