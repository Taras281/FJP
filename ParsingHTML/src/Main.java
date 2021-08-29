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
    try {

      Document doc = Jsoup.connect(path).get();

      for (Element element : doc.select("img")) {
        String imagine = element.absUrl("src");
        String[] fragments = imagine.split("\\/");
        String fileName = fragments[fragments.length - 1].replace(":" , "").replace("?", "");

try {
          URL url = new URL(imagine);
          InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(PATH_FOR_IMG + fileName));
            for (int b; (b = in.read()) != -1; ) {
              out.write(b);
            }
              out.close();
              in.close();
        }
catch (Exception exception) {
  exception.printStackTrace();
}

//        if (format[format.length-1].equals("png") || format[format.length-1].equals("jpg") || format[format.length-1].equals("jpeg")) {
//          URL url = new URL(imagine);
//          InputStream in = url.openStream();
//            OutputStream out = new BufferedOutputStream(new FileOutputStream(PATH_FOR_IMG + fileName));
//
//            for (int b; (b = in.read()) != -1; ) {
//              out.write(b);
//            }
//            out.close();
//            in.close();
//        }
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return null;
  }
}
