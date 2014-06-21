package domtreegraphmlbuilder;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class GraphmlExporter {

    private static PrintWriter output;

    public static void doExport(Graph graph, String filename) throws FileNotFoundException {
        ArrayList<Node> nodes = graph.getNodes();
        ArrayList<Edge> edges = graph.getEdges();

        output = new PrintWriter(filename);

        output.println("<graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:y=\"http://www.yworks.com/xml/graphml\" xmlns:yed=\"http://www.yworks.com/xml/yed/3\" xsi:schemaLocation=\"http://graphml.graphdrawing.org/xmlns http://www.yworks.com/xml/schema/graphml/1.1/ygraphml.xsd\">");
        output.println("\t<key attr.name=\"description\" attr.type=\"string\" for=\"node\" id=\"d5\"/>");
        output.println("\t<key for=\"node\" id=\"d6\" yfiles.type=\"nodegraphics\"/>");
        output.println("\t<key attr.name=\"description\" attr.type=\"string\" for=\"edge\" id=\"d9\"/>");
        output.println("\t<key for=\"edge\" id=\"d10\" yfiles.type=\"edgegraphics\"/>");
        output.println("<graph>");

        for (Node node : nodes)
            node(node.id, node.type, node.domId, node.wholeTag);
        for (Edge edge : edges)
            edge(edge.hashCode(), edge.source.id, edge.target.id); //generating hash-code just here, not needed before. but yED needs it to read the graphml-file

        output.println("</graph>");
        output.println("</graphml>");
        output.close();

    }

    private static void node(String id, String type, String domId, String wholeTag) {
       
    	if(domId.equals(""))   	
	    	output.println("<node id=" + '"' + id + '"' + ">" +
	                "<data key=\"d5\"><![CDATA[" + wholeTag + "]]></data>" +
	                "<data key=\"d6\">" +
	                "<y:ShapeNode><y:Fill color=\"#FFCC00\" transparent=\"false\"/>" +
	                "<y:BorderStyle color=\"#FF0000\" type=\"line\" width=\"1.0\"/>" +
	                "<y:NodeLabel textColor=\"#000000\">" + type + "</y:NodeLabel>" +
	                "<y:Shape type=\"ellipse\"/></y:ShapeNode></data></node>");
    	else {
	    	output.println("<node id=" + '"' + id + '"' + ">" +
	                "<data key=\"d5\"><![CDATA[" + wholeTag + "]]></data>" +
	                "<data key=\"d6\">" +
	                "<y:ShapeNode><y:Fill color=\"#FFCC00\" transparent=\"false\"/>" +
	                "<y:BorderStyle color=\"#FF0000\" type=\"line\" width=\"1.0\"/>" +
	                "<y:NodeLabel textColor=\"#000000\">" + type);
	    	output.println(domId + "</y:NodeLabel>" +
	                "<y:Shape type=\"ellipse\"/></y:ShapeNode></data></node>");	
    	}
    }

    private static void edge(int ID, String sourceID, String targetID) {
        output.println("<edge id=" + '"' + ID + '"' + " source=" + '"' + sourceID + '"' + " target=" + '"' + targetID + '"' + ">" +
                "<data key=\"d9\"><![CDATA[Edge ID: " + ID + "]]></data>" +
                "<data key=\"d10\"><y:BezierEdge>" +
                "<y:LineStyle color=\"#000000\" type=\"line\" width=\"1.0\"/>" +
                "<y:Arrows source=\"none\" target=\"standard\"/>" +
                "</y:BezierEdge></data></edge>");
        output.println("");
    }
}
