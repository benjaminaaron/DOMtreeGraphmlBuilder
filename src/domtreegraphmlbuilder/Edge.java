package domtreegraphmlbuilder;

public class Edge {

    Node source;
    Node target;

    public Edge(Node source, Node target) {
        this.source = source;
        this.target = target;
    }

    public String show() {
        return "Edge: [" + source.id + "] -> [" + target.id + "]";
    }
}
