package pdf.aspose;

import com.aspose.pdf.License;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

//import com.aspose.pdf.Document;

public class AsposeWordUtil {
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("licence/Aspose.Pdf.162356.lic"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            com.aspose.pdf.License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void html2pdf(String htmlData) {

//        if (!getLicense()) {
//            return;
//        }
        try {
            long old = System.currentTimeMillis();
            File file = new File("aspose.pdf");  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(new ByteArrayInputStream(htmlData.getBytes()));                    //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doc2pdf(String Address) {

//        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
//            System.out.println("licence fail");
//            return;
//        }
        try {
            long old = System.currentTimeMillis();
            File file = new File("aspose.pdf");  //新建一个空白pdf文档
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(Address);                    //Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AsposeWordUtil.doc2pdf("aspose.html");
        AsposeWordUtil.doc2pdf("4.docx");

    }
}
