package domtreegraphmlbuilder;

public class Node {

	String type = "";
	String domId = "";
	String classes = "";
	String wholeTag = "";
    
	String id = "";
    
    Node parent = null;
    
    public Node(String type, String domId, String classes, String wholeTag) {
        this.type = type;       
        this.domId = domId;
        this.classes = classes;
        this.wholeTag = wholeTag;
        this.id = "node_" + this.hashCode();
    }

    public String show() {   	   	
        return "Node: <" + type + ">, id: " + id + (domId.equals("") ? "" : ", domId: " + domId) + (classes.equals("") ? "" : ", classes: " + classes);
    }

}
