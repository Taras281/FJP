import core.Line;
import core.Station;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsonCreator {

  private final String REGEX = "[0-9][0-9]?.";
  private final String REGEX_FOR_STATIONS = "[\\s][\\s]";
  private final String STATIONS = "stations";
  private final String LINES = "lines";
  List<Line> lines;
  List<Station> stations;

  public void jsonParser(String path) throws IOException {
    File input = new File(path);
    Document doc = Jsoup.parse(input, "UTF-8");
    Element metrodata = doc.getElementById("metrodata");
    Elements elementsOfLines = metrodata.getElementsByClass("js-metro-line");
    addLines(elementsOfLines);

    Elements elementsOfStations = metrodata.getElementsByClass("js-metro-stations");
    addStations(elementsOfStations);

    Elements elementsOfConnections = metrodata.getElementsByClass("t-icon-metroln");
    addConnection(elementsOfConnections);

    createJson();

    Map<Line, List<Station>> lineAndStations = new TreeMap<>();

    for (int i = 0; i < lines.size(); i++) {
      List<Station> test = new ArrayList<>();
      String[] fragments = stations.get(i).getName().split(REGEX_FOR_STATIONS);
      for (int j = 0; j < fragments.length; j++) {
        test.add(new Station(fragments[j], new Line(lines.get(i).getName(), lines.get(i).getNumber())));
      }
      lineAndStations.put(new Line(lines.get(i).getName(), lines.get(i).getNumber()), test);
    }

  }

  public void addLines(Elements elements) {
    lines = new ArrayList<>();
    elements
        .forEach(line -> lines.add(new Line(line.text(), line.attr("data-line"))));
  }

  public void addStations(Elements elements) {
    stations = new ArrayList<>();
    elements.forEach(
        st -> stations.add(new Station(st.text().replaceAll(REGEX, ""), st.attr("data-line"))));
  }

  public void addConnection(Elements elements) {
    for (Element link : elements) {
      String transitions = link.attr("title");
      if (transitions.contains("переход на")) {
        int firstQuotes = transitions.indexOf("«");
        int secondQuotes = transitions.lastIndexOf("»");
        String transitionStationName = transitions.substring(firstQuotes + 1, secondQuotes);
      }
    }
  }

  public void createJson() throws IOException {
    JSONObject jsonObject = new JSONObject();

    JSONObject object = new JSONObject();
    for (int i = 0; i < lines.size(); i++) {
      JSONArray array = new JSONArray();
      String str = stations.get(i).getName();
      String[] fragments = str.split(REGEX_FOR_STATIONS);
      for (String fragment : fragments) {
        array.add(fragment.trim());
      }
      object.put(lines.get(i).getNumber(), array);
    }
    jsonObject.put(STATIONS, object);

    JSONArray jsonArray = new JSONArray();
    for (int i = 0; i < lines.size(); i++) {
      JSONObject obj = new JSONObject();
      obj.put("number", lines.get(i).getNumber());
      obj.put("line", lines.get(i).getName());
      jsonArray.add(obj);
    }
    jsonObject.put(LINES, jsonArray);

    String PATH_TO_JSON = "src/resources/Map.json";
    FileWriter file = new FileWriter(PATH_TO_JSON);
    file.write(jsonObject.toJSONString());
  }


  public void printLines() {
    for (Line line : lines) {
      System.out.println(line.getNumber() + " - " + line.getName());
    }
  }

  public void printStationsOnLines() {
    for (Station station : stations) {
      System.out.println("Линия: " + station.getLine() + ", станции: " + station.getName());
    }
  }
}
