package pdf.aspose;


import com.aspose.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;


public class AsposeUtil {
    public static boolean getLicense() {
        boolean result = false;
        try {
//            InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("licence/Aspose.Pdf.162356.lic"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("licence/ASPOSE-DE-CCO-211511--190625134255-DE-SCO-352230.lic"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void html2pdf1(String header, String footer, String htmlData, OutputStream outputStream) throws IOException {
        System.out.println("start:"+new Date(System.currentTimeMillis()));
        if (!getLicense()) {
            System.out.println("licence fail");
            return;
        }
        System.out.println("getLicence end:"+new Date(System.currentTimeMillis()));

        HtmlLoadOptions options = new HtmlLoadOptions();
        options.setInputEncoding("UTF-8");
        PageInfo pageInfo = new PageInfo();
        MarginInfo marginInfo = new MarginInfo(50, 50, 50, 30);
        pageInfo.setMargin(marginInfo);
        options.setPageInfo(pageInfo);
        System.out.println("get options end:"+new Date(System.currentTimeMillis()));

        ByteArrayInputStream bis = new ByteArrayInputStream(htmlData.getBytes(StandardCharsets.UTF_8));
        System.out.println("get byte input stream of html end:"+new Date(System.currentTimeMillis()));
        Arrays.asList()
        com.aspose.pdf.Document pdfDocument = new com.aspose.pdf.Document(bis, options);
        System.out.println("write stream to pdfDocument end:"+new Date(System.currentTimeMillis()));

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
        System.out.println("set pdf footer end:"+new Date(System.currentTimeMillis()));
        pdfDocument.save(outputStream);
        System.out.println("save pdf end:"+new Date(System.currentTimeMillis()));

    }


}