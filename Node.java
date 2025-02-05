public class Node {
    String name;
    int commonLettersCounter = 0;
    boolean isVisited;
    Node prev;
    Node next;
    Boolean isHubStudent;

    public Node(String name, boolean isHubStudent) {
        this.name = name;
        this.isHubStudent =isHubStudent;
        this.isVisited=false;
    }

    public String getName(){
        return name;
    }
}