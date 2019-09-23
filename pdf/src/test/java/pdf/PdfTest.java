package pdf;

import org.junit.Test;
import pdf.aspose.AsposeUtil;
import pdf.itext.ItextUtil;
import pdf.itext2.ItextUtil2;
import pdf.model.m1.ChooseBO;
import pdf.model.m1.DataUtil;
import pdf.model.m1.RegulationBO;
import pdf.model.m1.TextBO;
import pdf.model.m2.RecordBO;
import pdf.pdfbox.PdfBoxUtil;
import pdf.util.FreeMarkerUtil;

import java.io.*;

public class PdfTest {

    @Test
    public void testItext5() throws Exception {
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
        itextUtil.exportToFile("itext5.pdf", bo, htmlData);
    }

    // simple itext, no freemarker
    @Test
    public void testItext5_2() throws Exception {
        File file = new File("itext5_2.pdf");
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

//    @Test
//    public void testGeneratePdfByAsposeWord() throws Exception {
//        RecordBO bo = pdf.model.m2.DataUtil.prepareData();
//        String recordFtl = getTemplateString();
////    pdfUtil.writePdfToStream("","Record ID: ${businessId} - Version: ${version}",recordFtl,bo,response.getOutputStream());
//        String htmlData = FreeMarkerUtil.getContent("record1.ftl", bo);
//        AsposeUtil.html2pdf(htmlData);
//    }

    @Test
    public void testGeneratePdfByAsposePdf() throws Exception {
        RecordBO bo = pdf.model.m2.DataUtil.prepareData();
        String recordFtl = getTemplateString();
//    pdfUtil.writePdfToStream("","Record ID: ${businessId} - Version: ${version}",recordFtl,bo,response.getOutputStream());
        String htmlData = FreeMarkerUtil.getContent("record1.ftl", bo);
        File file = new File("aspose1.pdf");  //新建一个空白pdf文档
        FileOutputStream os = new FileOutputStream(file);
//    String htmlData = FreeMarkerUtil.getContent("record1.ftl", bo);
        AsposeUtil.html2pdf1("", "Footer", htmlData, os);
        File file2 = new File("aspose2.pdf");  //新建一个空白pdf文档
        FileOutputStream os2 = new FileOutputStream(file);
        AsposeUtil.html2pdf1("", "Footer", htmlData, os2);
    }


    private static String getTemplateString() {
        String classpath = FreeMarkerUtil.class.getClassLoader().getResource("").getPath();
        System.out.println(classpath);
        String templatePath = classpath + "templates/record.ftl";
        String recordFtl = readFileByLines(templatePath);
        return recordFtl;
    }

    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
                line++;
            }
            reader.close();
            return new String(sb);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}

