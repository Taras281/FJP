import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

  private static final String PATH_FOR_IMG = "ParsingHTML/images/";

  public static void main(String[] args) {


    String htmlFile = parseFile("https://lenta.ru/");
    //    String htmlFile = parseFile("https://pikabu.ru/");
    //    String htmlFile = parseFile("https://ria.ru/");
  }

  public static String parseFile(String path) {

}
