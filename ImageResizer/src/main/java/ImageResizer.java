import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {

  private final File[] files;
  private final int newWidth;
  private final String destinationFolder;
  private final long start;
  private final int threadNumber;

  public ImageResizer(
      File[] files, int newWidth, String destinationFolder, long start, int threadNumber) {
    this.files = files;
    this.newWidth = newWidth;
    this.destinationFolder = destinationFolder;
    this.start = start;
    this.threadNumber = threadNumber;
  }

  @Override
  public void run() {
    try {
      for (File file : files) {
        BufferedImage image = ImageIO.read(file);

        if (image == null) {
          continue;
        }
        int newHeight =
            (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
        BufferedImage newImage = resize(image, newWidth, newHeight);
        File newFile = new File(destinationFolder + "/" + file.getName());
        ImageIO.write(newImage, "jpg", newFile);
        System.out.println("Поток номер " + threadNumber + " обработал файл: " + file.getName());
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    System.out.println(
        "Поток номер: "
            + threadNumber
            + " завершил свою работу за: "
            + (System.currentTimeMillis() - start)
            + "мс");
  }

  public static BufferedImage resize(BufferedImage image, int targetWidth, int targetHeight) {
    return Scalr.resize(
        image,
        Scalr.Method.AUTOMATIC,
        Scalr.Mode.FIT_EXACT,
        targetWidth,
        targetHeight,
        Scalr.OP_ANTIALIAS);
  }
}
