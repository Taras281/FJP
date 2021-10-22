import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class TreeListProcessor extends RecursiveTask<Set<String>> {
  private final Node node;

  public TreeListProcessor(Node node) {
    this.node = node;
  }

  @Override
  protected Set<String> compute() {
    Set<String> weather = new HashSet<>();
    weather.addAll(node.getWeathers());

    List<TreeListProcessor> subTasks = new ArrayList<>();

    for (Node child : node.getChildren()) {
      TreeListProcessor task = new TreeListProcessor(child);
      task.fork();
      subTasks.add(task);
    }

    for (TreeListProcessor task : subTasks) {
      weather.addAll(task.join());
    }

    return weather;
  }
}
