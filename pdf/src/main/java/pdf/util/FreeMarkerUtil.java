package pdf.util;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FreeMarkerUtil {

  private static final String UTF_8 = "UTF-8";

  private static Map<String, FileTemplateLoader> fileTemplateLoaderCache = new ConcurrentHashMap();

  private static Map<String, Configuration> configurationCache = new ConcurrentHashMap();

  private static Configuration getConfiguration(String templateFilePath) {
    if (null != configurationCache.get(templateFilePath)) {
      return configurationCache.get(templateFilePath);
    }
    Configuration config = new Configuration(Configuration.VERSION_2_3_25);
    config.setDefaultEncoding(UTF_8);
    config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    config.setLogTemplateExceptions(false);
    FileTemplateLoader fileTemplateLoader = null;
    if (null != fileTemplateLoaderCache.get(templateFilePath)) {
      fileTemplateLoader = fileTemplateLoaderCache.get(templateFilePath);
    }
    try {
      fileTemplateLoader = new FileTemplateLoader(new File(templateFilePath));
      fileTemplateLoaderCache.put(templateFilePath, fileTemplateLoader);
    } catch (IOException e) {
      throw new RuntimeException("fileTemplateLoader init error!", e);
    }
    config.setTemplateLoader(fileTemplateLoader);
    configurationCache.put(templateFilePath, config);
    return config;
  }

  public static String getContent(String fileName, Object data) {
    String classpath = FreeMarkerUtil.class.getClassLoader().getResource("").getPath();
    System.out.println(classpath);
    String templatePath = classpath + "templates/";

    try {
      Template template = getConfiguration(templatePath).getTemplate(fileName);
      StringWriter writer = new StringWriter();
      template.process(data, writer);
      writer.flush();
      return writer.toString();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new RuntimeException("FreeMarkerUtil process fail", ex);
    }
  }
}
