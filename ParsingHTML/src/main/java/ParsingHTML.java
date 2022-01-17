import java.io.IOException;

/**
 * Автор: https://github.com/VlasovM
 * В данной задаче поймем, что такое парсинг и как скачать картинки с разных сайтов.
 * Приложение было обновлено enum, поэтому в статье могут быть чуть не точная информация.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-rabota-s-html-failami-s-pomoschu-jsoup-api
 */

public class ParsingHTML {

  public static void main(String[] args) {
    try {
      Parser parser = new Parser();
      parser.parsingHTML(URL.RIA.getUrl());
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }
}
