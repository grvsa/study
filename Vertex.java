public class Vertex implements Comparable<Vertex> {
    private String name;
    private boolean visited;
    private Integer distance;
    private Vertex previousVertex;

    public Vertex(String name) {
        this.name = name;
        visited = false;
        distance = Integer.MAX_VALUE;
        previousVertex = null;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public void visit(){
        visited = true;
    }

    public boolean isVisited(){
        return visited;
    }

    public void clear(){
        distance = Integer.MAX_VALUE;
        previousVertex = null;
        visited = false;
    }

    public String print(){
        return "[" + name + ": " + distance + " : " + (previousVertex == null ? "NULL]" : previousVertex.toString().substring(1));
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }

    public int compareTo(Vertex o) {
        return distance - o.getDistance();
    }
}
