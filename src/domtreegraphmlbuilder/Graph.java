package domtreegraphmlbuilder;

import java.util.ArrayList;

import domtreegraphmlbuilder.Tag.TagCommands;

public class Graph {

	ArrayList<Node> nodes = new ArrayList<>();
	ArrayList<Edge> edges = new ArrayList<>();
	
	public Graph(ArrayList<Tag> tagStack){		
		Node root = new Node("root", "", "", "");
		nodes.add(root);
		
		Node lastNode = root;
		
		for(int i = 0; i < tagStack.size(); i++){
			Tag tag = tagStack.get(i);			
			System.out.println(tag.show());
			
			if(tag.tagCommand != TagCommands.CLOSING){
				Node nextNode = new Node(tag.type, tag.id, tag.classes, tag.wholeTag);
				nextNode.parent = lastNode;
				nodes.add(nextNode);
				edges.add(new Edge(lastNode, nextNode));
				lastNode = nextNode;	
			}
			if(tag.tagCommand != TagCommands.OPENING){
				lastNode = lastNode.parent;
			}		
		}
		
		
		for(Node n : nodes)
			System.out.println(n.show());
		
		for(Edge e : edges)
			System.out.println(e.show());	
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
