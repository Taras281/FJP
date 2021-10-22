import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    Node root = new Node(List.of("10", "40"));

    Node child1 = root.addChild(new Node(List.of("45", "31")));
    Node child2 = root.addChild(new Node(List.of("32", "90")));

    Node child3 = child1.addChild(new Node(List.of("34", "18")));
    Node child4 = child1.addChild(new Node(List.of("22", "7")));

    Node child5 = child2.addChild(new Node(List.of("42", "17")));
    Node child6 = child2.addChild(new Node(List.of("34", "19")));

    ForkJoinPool forkJoinPool = new ForkJoinPool();
    TreeListProcessor processor = new TreeListProcessor(root);

    forkJoinPool.execute(processor);

    Set<String> results = processor.join();

    System.out.println(results);
  }
}
