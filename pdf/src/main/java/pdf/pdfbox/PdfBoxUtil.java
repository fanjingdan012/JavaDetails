package pdf.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import pdf.model.m1.RegulationBO;

import java.io.File;
import java.io.IOException;

public class PdfBoxUtil {
  /**
   * @param page
   * @param contentStream
   * @param y             the y-coordinate of the first row
   * @param margin        the padding on left and right of table
   * @param content       a 2d array containing the table data
   * @throws IOException
   */
  public static void drawTable(PDPage page, PDPageContentStream contentStream, float y,
      float margin, String[][] content) throws IOException {
    final int rows = content.length;
    final int cols = content[0].length;
    final float rowHeight = 20f;
    final float tableWidth = page.getMediaBox().getWidth() - margin - margin;
    final float tableHeight = rowHeight * rows;
    final float colWidth = tableWidth / (float) cols;
    final float cellMargin = 5f;

    //draw the rows
    float nexty = y;
    for (int i = 0; i <= rows; i++) {
      contentStream.drawLine(margin, nexty, margin + tableWidth, nexty);
      nexty -= rowHeight;
    }

    //draw the columns
    float nextx = margin;
    for (int i = 0; i <= cols; i++) {
      contentStream.drawLine(nextx, y, nextx, y - tableHeight);
      nextx += colWidth;
    }

    //now add the text
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

    float textx = margin + cellMargin;
    float texty = y - 15;
    for (int i = 0; i < content.length; i++) {
      for (int j = 0; j < content[i].length; j++) {
        String text = content[i][j];
        contentStream.beginText();
        contentStream.moveTextPositionByAmount(textx, texty);
        contentStream.drawString(text);
        contentStream.endText();
        textx += colWidth;
      }
      texty -= rowHeight;
      textx = margin + cellMargin;
    }
  }

  //

  /**
   * 获取空的pdf文档对象
   *
   * @return PDDocument
   */
  public PDDocument getPdDocument() {
    PDDocument document = new PDDocument();
    return document;
  }

  /**
   * 通过文件名加载文档
   *
   * @param fileName
   * @return PDDocument
   * @throws IOException
   */
  public PDDocument getPdDocument(String fileName) throws IOException {
    PDDocument document = PDDocument.load(new File(fileName));
    return document;
  }

  /**
   * 获取空的pdf页面对象
   *
   * @return PDPage
   */
  public PDPage getPdPage() {
    PDPage page = new PDPage();
    return page;
  }

  public void generatePdf(RegulationBO bo, String filePath) throws IOException {

    PDDocument document = getPdDocument();
    PDPage page = getPdPage();
    document.addPage(page);

    PDFont font = PDType1Font.HELVETICA_BOLD;

    PDPageContentStream contentStream = new PDPageContentStream(document, page);
    ;

    try {
      contentStream.beginText();
      contentStream.setFont(font, 25);
      contentStream.newLineAtOffset(250.00f, 700.0f);
      contentStream.showText(bo.getName());
      contentStream.endText();

      contentStream.beginText();
      contentStream.setFont(font, 16);
      contentStream.newLineAtOffset(250.00f, 680.0f);
      contentStream.showText(bo.getCategory());
      contentStream.endText();

      contentStream.beginText();
      contentStream.setFont(font, 14);
      contentStream.newLineAtOffset(244.00f, 660.0f);
      contentStream.showText("Status: " + bo.getStatus());
      contentStream.endText();

      //add a hyperlink
      PDBorderStyleDictionary borderULine = new PDBorderStyleDictionary();
      borderULine.setStyle(PDBorderStyleDictionary.STYLE_UNDERLINE);
      PDAnnotationLink txtLink = new PDAnnotationLink();
      txtLink.setBorderStyle(borderULine);
      PDActionURI action = new PDActionURI();
      action.setURI("https://pdfbox.apache.org/");
      txtLink.setAction(action);
      PDRectangle position = new PDRectangle();
      position.setLowerLeftX(50.0f);
      position.setLowerLeftY(620.0f);
      position.setUpperRightX(400.0f);
      position.setUpperRightY(640.0f);
      txtLink.setRectangle(position);
      page.getAnnotations().add(txtLink);
      contentStream.beginText();
      contentStream.setFont(font, 20);
      contentStream.newLineAtOffset(50f, 620f);
      contentStream.showText("PDF box url hyperlink");
      contentStream.endText();

      contentStream.beginText();
      float x = 50.0f;
      contentStream.setFont(font, 20);
      contentStream.newLineAtOffset(x, 600.0f);
      contentStream.showText("General Information");
      contentStream.endText();

      contentStream.beginText();
      float y = 580.0f;
      contentStream.setFont(font, 14);
      contentStream.newLineAtOffset(x, y);
      contentStream.showText("Category");
      contentStream.endText();
      contentStream.beginText();
      contentStream.newLineAtOffset(x, y - 20);
      contentStream.showText(bo.getCategory());
      contentStream.endText();

      contentStream.beginText();
      y = y - 40f;
      contentStream.setFont(font, 14);
      contentStream.newLineAtOffset(x, y);
      contentStream.showText("Validity Period");
      contentStream.endText();
      contentStream.beginText();
      contentStream.newLineAtOffset(x, y - 20);
      contentStream.showText(bo.getValidPeriod());
      contentStream.endText();

      contentStream.beginText();
      y = y - 40f;
      contentStream.setFont(font, 14);
      contentStream.newLineAtOffset(x, y);
      contentStream.showText("Description");
      contentStream.endText();
      contentStream.beginText();
      contentStream.newLineAtOffset(x, y - 20);
      contentStream.showText(bo.getDescription().substring(0, 76));
      contentStream.endText();
      contentStream.beginText();
      contentStream.newLineAtOffset(x, y - 40);
      contentStream.showText(bo.getDescription().substring(76, bo.getDescription().length() - 1));
      contentStream.endText();

      contentStream.beginText();
      y = y - 80f;
      contentStream.setFont(font, 20);
      contentStream.newLineAtOffset(x, y);
      contentStream.showText("Requirements");
      contentStream.endText();
    } catch (Exception e) {
      e.printStackTrace();
    }

    String[][] content =
        {{"Num", "Name", "Status"}, {"1", "requirement1 long long text long long text", "Draft"}, {"2", "requirement1", "Draft"},
            {"3", "requirement1", "Draft"}, {"4", "requirement1", "Draft"}};

    drawTable(page, contentStream, 400f, 50f, content);
    String companyLogoPath = PdfBoxUtil.class.getResource("/").getPath() + "pic/company.png";
    System.out.println(companyLogoPath);
    PDImageXObject pdImage =
        PDImageXObject.createFromFileByContent(new File(companyLogoPath), document);
    contentStream.drawImage(pdImage, 20f, 20f);
    contentStream.close();

    document.save(filePath);
    document.close();

    System.out.println("Successfully created pdf: "+filePath);
  }


}
