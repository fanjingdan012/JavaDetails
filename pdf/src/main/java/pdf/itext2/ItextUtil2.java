package pdf.itext2;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class ItextUtil2 {
  Document document = new Document();// ����һ��Document����
  private static Font headfont;// ���������С
  private static Font keyfont;// ���������С
  private static Font textfont;// ���������С
  PdfWriter writer;

  static {
    BaseFont bfChinese;
    try {
      bfChinese = BaseFont
          .createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
      Font chineseFont = new Font(bfChinese, 12, Font.NORMAL);
      PdfPCell cellReportSummary = new PdfPCell(new Phrase("֧������", chineseFont));
      headfont = new Font(bfChinese, 10, Font.BOLD);// ���������С
      keyfont = new Font(bfChinese, 8, Font.BOLD);// ���������С
      textfont = new Font(bfChinese, 8, Font.NORMAL);// ���������С
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ItextUtil2(File file) {
    document.setPageSize(PageSize.A4);// ����ҳ���С
    try {
      writer = PdfWriter.getInstance(document, new FileOutputStream(file));
      Rectangle rect = new Rectangle(36, 54, 559, 788);
      rect.setBorderColor(BaseColor.BLACK);
      writer.setBoxSize("art", rect);
      HeaderFooter header = new HeaderFooter();
      writer.setPageEvent(header);
      document.open();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void generatePDF() throws Exception {

    document.newPage();
    Paragraph par = new Paragraph("Hello world!\n");
    document.add(par);
    String picPath = ItextUtil2.class.getResource("/").getPath() + "pic/company.png";
    Image img = Image.getInstance(picPath);
    img.setAlignment(Image.ALIGN_CENTER);
    img.scaleToFit(500, 300);
    img.setAbsolutePosition(10, 50);
    PdfContentByte cb = writer.getDirectContent();
    PdfTemplate tp = cb.createTemplate(600, 600);
    tp.addImage(img);
    cb.addTemplate(tp, 100, 600);

    document.newPage();

    Paragraph par1 = new Paragraph("first paragraph");

    document.add(par1);

    document.newPage();

    Paragraph par2 = new Paragraph("second paragraph");

    document.add(par2);

    document.close();
  }
}
