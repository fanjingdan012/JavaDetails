package pdf.util;

import com.google.common.collect.Maps;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class FontUtil {

  private static final Map<String, Font> fontCache = Maps.newConcurrentMap();

  public static String getFontPath(String fontName) {
    String classPath = FontUtil.class.getClassLoader().getResource("").getPath();
    return classPath + "/fonts/" + fontName;
  }

  public static Font loadFont(String fontFileName, int style, float fontSize) throws IOException {
    FileInputStream fis = null;
    String key = fontFileName + "|" + style;
    Font dynamicFont = fontCache.get(key);
    if (dynamicFont == null) {
      try {
        File file = new File(fontFileName);
        fis = new FileInputStream(file);
        dynamicFont = Font.createFont(style, fis);
        fontCache.put(key, dynamicFont);
      } catch (Exception e) {
        return new Font("宋体", Font.PLAIN, 14);
      } finally {
        fis.close();
      }
    }
    Font dynamicFontPt = dynamicFont.deriveFont(fontSize);

    return dynamicFontPt;
  }

  public static Font getFont(int style, float fontSize) {
    try {
      String fontPath = FontUtil.getFontPath("SIMLI.TTF");
      Font font = loadFont(fontPath, style, fontSize);
      return font;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
