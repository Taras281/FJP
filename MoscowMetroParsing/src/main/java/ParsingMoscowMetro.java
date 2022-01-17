import java.io.IOException;

/**
 * Автор: https://github.com/VlasovM
 * В данной задачке будем парсить сайт Московского метрополитена. Задача вытащить название станций, линий и положить в JSON файл.
 * Задачка была частично переделана, статьи немного устаревшие.
 * Но суть и кое-какие вещи понять можно. В случае вопросов пишите.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-parsing-html-faila-i-sozdanie-json-ch1
 * https://vk.com/@javlasov-parsing-html-faila-i-sozdanie-json-ch2
 */


public class ParsingMoscowMetro {
  private static final String URL_TO_HTML =
      "data/Карта метро Москвы со станциями МЦК и МЦД 2021.html";

  public static void main(String[] args) {
    JsonCreator jsonCreator = new JsonCreator();
    try {
      jsonCreator.jsonParser(URL_TO_HTML);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
