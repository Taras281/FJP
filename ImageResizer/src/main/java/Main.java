import java.io.File;

public class Main {

  private static final String SOURCE_FOLDER = "C:\\Users\\79153\\Desktop\\Source folder";
  private static final String DESTINATION_FOLDER = "C:\\Users\\79153\\Desktop\\Destination folder";

  private static final int newWidth = 300;

  public static void main(String[] args) {

    File sourceFile = new File(SOURCE_FOLDER);
    File[] files = sourceFile.listFiles();

    long start = System.currentTimeMillis();

    File[] files1 = new File[2];
    System.arraycopy(files, 0, files1, 0, files1.length);
    ImageResizer threadOne = new ImageResizer(files1, newWidth, DESTINATION_FOLDER, start, 1);
    new Thread(threadOne).start();

    File[] files2 = new File[2];
    System.arraycopy(files, 2, files2, 0, files2.length);
    ImageResizer threadTwo = new ImageResizer(files2, newWidth, DESTINATION_FOLDER, start, 2);
    new Thread(threadTwo).start();

    File[] files3 = new File[2];
    System.arraycopy(files, 4, files3, 0, files3.length);
    ImageResizer threadThree = new ImageResizer(files3, newWidth, DESTINATION_FOLDER, start, 3);
    new Thread(threadThree).start();

    File[] files4 = new File[2];
    System.arraycopy(files, 6, files4, 0, files4.length);
    ImageResizer threadFour = new ImageResizer(files4, newWidth, DESTINATION_FOLDER, start, 4);
    new Thread(threadFour).start();
  }
}
