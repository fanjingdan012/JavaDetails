package pdf;

import org.junit.Test;
import pdf.itext.ItextUtil;
import pdf.itext2.ItextUtil2;
import pdf.model.ChooseBO;
import pdf.model.RegulationBO;
import pdf.model.TextBO;
import pdf.pdfbox.PdfBoxUtil;
import pdf.util.DataUtil;
import pdf.util.FreeMarkerUtil;

import java.io.File;

public class PdfTest {

  @Test
  public void testExportToFile() throws Exception {
    RegulationBO bo = DataUtil.prepareData();
    for (int i = 0; i < bo.getQuestions().size(); i++) {
      if (bo.getQuestions().get(i) instanceof ChooseBO) {
        String htmlData = FreeMarkerUtil.getContent("choose.ftl", bo.getQuestions().get(i));
        bo.getQas().add(htmlData);
      } else if (bo.getQuestions().get(i) instanceof TextBO) {
        String htmlData = FreeMarkerUtil.getContent("text.ftl", bo.getQuestions().get(i));
        bo.getQas().add(htmlData);
      }
    }

    ItextUtil itextUtil = new ItextUtil();
    String htmlData = FreeMarkerUtil.getContent("record.ftl", bo);
    itextUtil.exportToFile("itext.pdf", bo, htmlData);
  }

  // simple itext, no freemarker
  @Test
  public void testItext2() throws Exception {
    File file = new File("itext2.pdf");
    file.createNewFile();
    new ItextUtil2(file).generatePDF();
    System.out.println("done");
  }

  @Test
  public void testGeneratePdfByPdfBox() throws Exception {
    PdfBoxUtil pdfUtil = new PdfBoxUtil();
    RegulationBO bo = DataUtil.prepareData();
    pdfUtil.generatePdf(bo, "pdfbox.pdf");
  }
}

