import java.util.*; 
  
public class Graph  
{ 
    private static Map<Node, List<Node>> adjNodes = new HashMap<Node, List<Node>>();

    //Add a node and create an empty list for it to hold adjacent nodes in the future
    public List<Node> initNodes(Node source) {
        List<Node> list = new LinkedList<Node>();
        adjNodes.put(source, list);
        return list;
    }
    

    public void addEdges(Node source, Node mapped, GameArena arena, boolean directed, String side, double pos) 
    { 
        if (directed == true && source == mapped) {
            if (side.equals("right")) {

                List<Node> l = adjNodes.get(source);
                l.add(mapped);

                Ball b1 = new Ball(source.ball.getXPosition()+30, source.ball.getYPosition(), 30, "WHITE");
                Ball ring = new Ball(source.ball.getXPosition()+30, source.ball.getYPosition(), 26, "BLACK");

                arena.addBall(b1);
                arena.addBall(ring);
            }

            else if (side.equals("left")) {

                List<Node> l = adjNodes.get(source);
                l.add(mapped);

                Ball b1 = new Ball(source.ball.getXPosition()-30, source.ball.getYPosition(), 30, "WHITE");
                Ball ring = new Ball(source.ball.getXPosition()-30, source.ball.getYPosition(), 26, "BLACK");                

                arena.addBall(b1);
                arena.addBall(ring);
            }

            else if (side.equals("up")) {

                List<Node> l = adjNodes.get(source);
                l.add(mapped);

                Ball b1 = new Ball(source.ball.getXPosition(), source.ball.getYPosition() +30, 30, "WHITE");
                Ball ring = new Ball(source.ball.getXPosition(), source.ball.getYPosition()+30, 26, "BLACK");

                
                arena.addBall(b1);
                arena.addBall(ring);
            }

            else if (side.equals("down")) {

                List<Node> l = adjNodes.get(source);
                l.add(mapped);

                Ball b1 = new Ball(source.ball.getXPosition(), source.ball.getYPosition()-30, 30, "WHITE");
                Ball ring = new Ball(source.ball.getXPosition(), source.ball.getYPosition()-30, 26, "BLACK");

                arena.addBall(b1);
                arena.addBall(ring);
            }
        }

        else if (directed == true && source != mapped) {
            List<Node> l = adjNodes.get(source);
            l.add(mapped);

            Arrow arrow = new Arrow(source.ball.getXPosition(), source.ball.getYPosition(), mapped.ball.getXPosition(),  mapped.ball.getYPosition(), 4, "WHITE", arena);
            arrow.setArrowHeadPosition(pos);
            source.arr = arrow;
        }
            
        else if (directed == false) {

            List<Node> l = adjNodes.get(source);
            l.add(mapped);

            List<Node> l2 = adjNodes.get(mapped);
            l2.add(source);

            Line line = new Line(source.ball.getXPosition(), source.ball.getYPosition(), mapped.ball.getXPosition(), mapped.ball.getYPosition(), 4, "WHITE");
            arena.addLine(line);
            source.ln = line;
        }
    }

    public void addNodes(GameArena arena) {

        for (Node n: adjNodes.keySet()) {

            arena.addBall(n.ball);
            arena.addText(n.text);
        }
    }

    public String getConnections(Node n) {
        List<String> node = new LinkedList<String>();
        String s = "This is node " + n.label + ", it has connections with the following nodes:\n";

        for (Node k : adjNodes.get(n))
            node.add(k.label);

        for(String b : node) {
            String str = "["+b+"] ";
            s = s+str; 
        }
        return s;
    }

    public void removeNodes(GameArena arena) {

        for (Node n: adjNodes.keySet()) {

            arena.removeBall(n.ball);
            arena.removeText(n.text);
        }
    }

    public void printGraph() {
        for (Node n : adjNodes.keySet()) {
            System.out.print("<" + n.label + ">");
            for (Node k : adjNodes.get(n)) 
                System.out.print(" ===> " + k.label);
            System.out.println("\n");
        }
        System.out.println("\n\n");
    }

    public void closeGraph() {
        adjNodes.clear();
        SelectionScreen select = new SelectionScreen();
        select.showSelectScreen();
    }
}