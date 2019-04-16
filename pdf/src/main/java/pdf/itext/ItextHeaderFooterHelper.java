package pdf.itext;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ItextHeaderFooterHelper extends PdfPageEventHelper {

  private static Logger log = LoggerFactory.getLogger(ItextHeaderFooterHelper.class);
  private static final int FONT_SIZE = 10;

  private PdfTemplate template;

  private Object data;
  Image image;

  public ItextHeaderFooterHelper(Object data) {
    this.data = data;
  }

  public void onOpenDocument(PdfWriter writer, Document document) {
    template = writer.getDirectContent().createTemplate(50, 50);
    try {

      String uri = ItextUtil.class.getClassLoader().getResource("").getPath() + "pic/company.png";
      System.out.println(uri);
      image = Image.getInstance(uri);
      image.setAbsolutePosition(12, 12);
    } catch (BadElementException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void onEndPage(PdfWriter writer, Document document) {
    this.addPage(writer, document);
    try {
      writer.getDirectContent().addImage(image);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void addPage(PdfWriter writer, Document document) {
    BaseFont bf = initFront();
    Font font = new Font(bf, FONT_SIZE, Font.NORMAL);
    writeHeader(writer, document, font);
    writeFooter(writer, document, font, template);
  }

  public void onCloseDocument(PdfWriter writer, Document document) {
    template.beginText();
    BaseFont bf = initFront();
    template.setFontAndSize(bf, FONT_SIZE);
    String replace = getReplaceOfTemplate(writer);
    template.showText(replace);
    template.endText();
    template.closePath();
  }

  private BaseFont initFront() {
    try {
      String classpath = ItextHeaderFooterHelper.class.getClassLoader().getResource("").getPath();
      String fontPath = classpath + "fonts/ping_fang_light.ttf";
      BaseFont bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
      return bf;
    } catch (DocumentException e) {
      log.error("initFront DocumentException", e);
      throw new RuntimeException("initFront DocumentException", e);
    } catch (IOException e) {
      log.error("initFront IOException", e);
      throw new RuntimeException("initFront IOException", e);
    }
  }

  private void writeFooter(PdfWriter writer, Document document, Font font, PdfTemplate template) {

    int pageS = writer.getPageNumber();
    int currentPage = pageS - 1;
    if (currentPage <= 0) {
      return;
    }
    Phrase footer1 = new Phrase("Footer", font);
    Phrase footer2 = new Phrase("页脚" + "    " + currentPage + "/", font);

    PdfContentByte cb = writer.getDirectContent();
    ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, footer1, (document.left() + 10),
        document.bottom() - 20, 0);
    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer2, (document.right() - 30),
        document.bottom() - 20, 0);

    //设置模板位置
    cb.addTemplate(template, document.right() - 30, document.bottom() - 20);
  }

  private void writeHeader(PdfWriter writer, Document document, Font font) {
    ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT,
        new Phrase("Header页眉", font), document.left(), document.top() + 20, 0);
  }

  private String getReplaceOfTemplate(PdfWriter writer) {
    int total = writer.getPageNumber() - 2;
    return total + "";
  }
}