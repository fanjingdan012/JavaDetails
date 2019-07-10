import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZIP {
  public static void main(String[] argv) throws Exception {
    ZipFile zf =
        new ZipFile("C:\\fjd\\mySoftware.zip");
    for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
      String zipEntryName = ((ZipEntry) entries.nextElement()).getName();
      System.out.println(zipEntryName);
    }
  }
}