public enum MovementList {
  List("src/main/resources/movementList.csv"),
  ListFloat("src/main/resources/movementListFloat.csv"),
  ListInteger("src/main/resources/movementListInteger.csv");

  private final String PATH;

  MovementList(String PATH) {
    this.PATH = PATH;
  }

  public String getPATH() {
    return PATH;
  }
}
