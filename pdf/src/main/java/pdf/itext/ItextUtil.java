package pdf.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.io.IOUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pdf.util.FreeMarkerUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.servlet.http.HttpServletResponse;

public class ItextUtil {



  /**
   * @param fileName 输出PDF文件名
   * @param data     模板所需要的数据
   * @description 导出pdf到文件
   */
  public void exportToFile(String fileName, Object data, String htmlData) {


    File file = new File(fileName);

    FileOutputStream outputStream = null;
    try {
      //设置输出路径
      outputStream = new FileOutputStream(fileName);
      //设置文档大小
      Document document = new Document(PageSize.A4);
      PdfWriter writer = PdfWriter.getInstance(document, outputStream);

      //设置页眉页脚
      ItextHeaderFooterHelper builder = new ItextHeaderFooterHelper( data);
      writer.setPageEvent(builder);

      //输出为PDF文件
      convertToPDF(writer, document, htmlData);
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      IOUtils.closeQuietly(outputStream);
    }
    System.out.println("itext generate pdf: "+file.getAbsolutePath());
  }

  /**
   * 生成PDF到输出流中（ServletOutputStream用于下载PDF）
   *
   * @param ftlPath  ftl模板文件的路径（不含文件名）
   * @param data     输入到FTL中的数据
   * @param response HttpServletResponse
   * @return
   */
  public OutputStream exportToResponse(String ftlPath, Object data, HttpServletResponse response)
      throws Exception {

    String html = FreeMarkerUtil.getContent(ftlPath, data);

    try {
      OutputStream out = null;
      ITextRenderer render = null;
      out = response.getOutputStream();
      //Set size A4
      Document document = new Document(PageSize.A4);
      PdfWriter writer = PdfWriter.getInstance(document, out);
      //set header footer
      ItextHeaderFooterHelper builder = new ItextHeaderFooterHelper( data);
      writer.setPageEvent(builder);
      convertToPDF(writer, document, html);
      return out;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
  }

  private void convertToPDF(PdfWriter writer, Document document, String htmlString)
      throws IOException {
    String fontPath = getFontPath();
    document.open();
    try {
      XMLWorkerHelper.getInstance()
          .parseXHtml(writer, document, new ByteArrayInputStream(htmlString.getBytes()),
              XMLWorkerHelper.class.getResourceAsStream("/default.css"), Charset.forName("UTF-8"),
              new XMLWorkerFontProvider(fontPath));
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    } finally {
      document.close();
    }
  }



  private static String getFontPath() {
    String classpath = ItextUtil.class.getClassLoader().getResource("").getPath();
    String fontpath = classpath + "fonts";
    return fontpath;
  }



}