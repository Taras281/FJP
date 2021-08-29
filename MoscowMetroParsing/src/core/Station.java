package core;

public class Station {

  private String name;
  private String lineString;
  private Line line;

  public Station(String name, Line line) {
    this.name = name;
    this.line = line;
  }


  public Station(String name, String line) {
    this.lineString = line;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getLine() {
    return lineString;
  }
}
