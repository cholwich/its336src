import java.util.List;
import java.util.HashSet;
import java.util.PriorityQueue;

public class UCSGraph {
  public int nvisited;

  public Node search(State s0) {
    Node n0 = new Node(s0, null, null, 0, 0);
    nvisited = 0;
    PriorityQueue<Node> frontier = new PriorityQueue<Node>();
    HashSet<String> explored = new HashSet<String>();
    frontier.add(n0);
    while(true) {
      if (frontier.isEmpty()) {
        return null;
      }
      else {
        Node n = frontier.poll();
        explored.add(n.state.toString());
        nvisited++;
        if (n.state.isGoal()) {
          return n;
        }
        else {
          List<Successor> succs = n.state.successors();
          for(Successor s : succs) {
            if (!explored.contains(s.state.toString()) &&
                !containsState(s.state, frontier)) { 
              Node p = new Node(s.state, n, s.action, n.cost+s.cost, n.depth+1);
              frontier.add(p);
            }
          }
        }
      }
    }
  }

  public boolean containsState(State s, PriorityQueue<Node> l) {
    for(Node n : l) {
      if (n.state.toString().equals(s.toString())) {
        return true;
      }
    }
    return false;
  }
}
