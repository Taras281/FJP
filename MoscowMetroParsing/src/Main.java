import java.io.IOException;

public class Main {
  private static final String URL_TO_HTML = "data/Карта метро Москвы со станциями МЦК и МЦД 2021.html";

  public static void main(String[] args) throws IOException {
    JsonCreator jsonCreator = new JsonCreator();
    jsonCreator.jsonParser(URL_TO_HTML);
  }


}
