package pdf.aspose;


import com.aspose.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;


public class AsposeUtil {
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("licence/Aspose.Pdf.162356.lic"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void html2pdf1(String header, String footer, String htmlData, OutputStream outputStream) throws IOException {
        if (!getLicense()) {
            System.out.println("licence fail");
            return;
        }
        HtmlLoadOptions options = new HtmlLoadOptions();
        options.setInputEncoding("UTF-8");
        PageInfo pageInfo = new PageInfo();
        MarginInfo marginInfo = new MarginInfo(50, 50, 50, 30);
        pageInfo.setMargin(marginInfo);
        options.setPageInfo(pageInfo);
        com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(new ByteArrayInputStream(htmlData.getBytes(StandardCharsets.UTF_8)), options);

        Iterator<Page> iter = pdfDocument.getPages().iterator();
        while (iter.hasNext()) {
            Page p = iter.next();


            com.aspose.pdf.HeaderFooter footer1 = new com.aspose.pdf.HeaderFooter();
            MarginInfo marginInfoFooter = new MarginInfo();
            marginInfoFooter.setLeft(57);
            marginInfoFooter.setTop(15);
            footer1.setMargin(marginInfoFooter);

            BaseParagraph paragraph = new TextFragment(footer + " Page " + p.getNumber() + " / " + pdfDocument.getPages().size());
            footer1.getParagraphs().add(paragraph);
            p.setFooter(footer1);
        }
        pdfDocument.save(outputStream);


    }


}