import java.util.ArrayList;
import java.util.List;

public class Node {

  List<Node> children;
  List<String> weathers;

  public Node(List<String> weathers) {
    this.children = new ArrayList<>();
    this.weathers = weathers;
  }

  public Node addChild(Node child) {
    children.add(child);
    return child;
  }

  public List<String> getWeathers() {
    return weathers;
  }

  public List<Node> getChildren() {
    return children;
  }
}
