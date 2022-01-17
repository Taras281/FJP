import core.Line;
import core.Station;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsonCreator {

  private static final String PATH_TO_JSON = "src/main/java/resources/Map.json";
  private final String REGEX_FOR_STATION = "[0-9][0-9]?.";
  private static Map<String, Line> lines;
  private static Map<Line, List<Station>> stations;

  public void jsonParser(String path) throws IOException {
    File input = new File(path);
    Document doc = Jsoup.parse(input, "UTF-8");
    Element metrodata = doc.getElementById("metrodata");

    Elements elementsOfLines = metrodata.getElementsByClass("js-metro-line");
    addLines(elementsOfLines);

    Elements elementsOfStations = metrodata.getElementsByClass("js-metro-stations");
    addStations(elementsOfStations);

    createJson();
  }

  public void addLines(Elements elements) {
    lines = new LinkedHashMap<>();
    for (Element element : elements) {
      Line line = new Line(element.text(), element.attr("data-line"));
      lines.put(line.getNumber(), line);
    }
  }

  public void addStations(Elements elements) {
    stations = new LinkedHashMap<>();
    for (Element element : elements) {
      Line line =
          new Line(
              lines.get(element.attr("data-line")).getName(),
              lines.get(element.attr("data-line")).getNumber());
      String[] arrayStation = element.text().replaceAll(REGEX_FOR_STATION, "").split("\\s\\s");
      List<Station> stationList = new ArrayList<>();
      for (int i = 0; i < arrayStation.length; i++) {
        Station station = new Station(arrayStation[i], line);
        stationList.add(station);
      }
      stations.put(line, stationList);
    }
  }

  public void createJson() throws IOException {
    FileWriter file = new FileWriter(PATH_TO_JSON);
    JSONObject mainJsonObject = new JSONObject();
    JSONObject jsonObjectStations = new JSONObject();
    JSONArray jsonArrayLines = new JSONArray();

    Set<Line> linesInMapStation = stations.keySet();
    for (Line line : linesInMapStation) {
      JSONArray innerJsonArrayStations = new JSONArray();
      List<Station> stationsList = stations.get(line);
      for (int j = 0; j < stationsList.size(); j++) {
        String station = stationsList.get(j).getName().trim();
        innerJsonArrayStations.add(station);
      }
      jsonObjectStations.put(line, innerJsonArrayStations);
    }

    Set<String> numbersLine = lines.keySet();
    for (String number : numbersLine) {
      JSONObject innerJsonObjectLines = new JSONObject();
      String nameLine = lines.get(number).getName();
      innerJsonObjectLines.put("Номер линии:", number);
      innerJsonObjectLines.put("Название линии:", nameLine);
      jsonArrayLines.add(innerJsonObjectLines);
    }
    mainJsonObject.put("Станции", jsonObjectStations);
    mainJsonObject.put("Линии", jsonArrayLines);
    file.write(mainJsonObject.toJSONString());
    file.close();
  }

  public void printLines() {
    for (Map.Entry entry : lines.entrySet()) {
      System.out.println("Номер линии: " + entry.getKey() + " Линия: " + entry.getValue());
    }
  }

  public void printStationsWithLines() {
    for (Map.Entry entry : stations.entrySet()) {
      System.out.println("Линия: " + entry.getKey() + " Станции: " + entry.getValue());
    }
  }
}
