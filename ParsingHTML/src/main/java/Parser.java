import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.URL;

public class Parser {

  private static final String PATH_FOR_IMG = "src/main/resources/";

  public void parsingHTML(String URL) throws IOException {
    long timeToStart = System.currentTimeMillis();
    Document doc = Jsoup.connect(URL).get();

    for (Element element : doc.select("img")) {
      URL imageUrl = null;
      String imageUrlString = element.absUrl("src");
      String[] fragmentsImage = imageUrlString.split("\\/");
      String fileName = fragmentsImage[fragmentsImage.length - 1].replace(":", "").replace("?", "");
      if (fragmentsImage[fragmentsImage.length - 1].contains("jpg")) {
        imageUrl = new URL(imageUrlString);
        InputStream in = imageUrl.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(PATH_FOR_IMG + fileName));
        for (int i; (i = in.read()) != -1; ) {
          out.write(i);
        }
        out.close();
        in.close();
      }
    }
    System.out.println(
        "Парсинг завершён за " + (System.currentTimeMillis() - timeToStart) + " м/с");
  }
}
