import java.io.File;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfTest {
	Document document = new Document();// 建立一个Document对象
	private static Font headfont;// 设置字体大小
	private static Font keyfont;// 设置字体大小
	private static Font textfont;// 设置字体大小
	PdfWriter writer;

	static {
		BaseFont bfChinese;
		try {
			 bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font chineseFont = new Font(bfChinese, 12, Font.NORMAL);
			PdfPCell cellReportSummary = new PdfPCell(new Phrase("支持中文",
					chineseFont));
			headfont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			keyfont = new Font(bfChinese, 8, Font.BOLD);// 设置字体大小
			textfont = new Font(bfChinese, 8, Font.NORMAL);// 设置字体大小
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PdfTest(File file) {
		document.setPageSize(PageSize.A4);// 设置页面大小
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			Rectangle rect = new Rectangle(36, 54, 559, 788);
	        rect.setBorderColor(BaseColor.BLACK);
	        writer.setBoxSize("art", rect);
	        HeaderFooter header=new HeaderFooter();
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
		
		Image img = Image.getInstance("C:\\Users\\I312177\\Desktop\\2.jpg");
		img.setAlignment(Image.ALIGN_CENTER);
		img.scaleToFit(500, 300);
		img.setAbsolutePosition(10, 50);
		PdfContentByte cb = writer.getDirectContent();
		PdfTemplate tp = cb.createTemplate(600, 600);
		tp.addImage(img);
		cb.addTemplate(tp,100,600);
		

        document.newPage();

        Paragraph par1 = new Paragraph("first paragraph");

        document.add(par1);



        document.newPage();

        Paragraph par2 = new Paragraph("second paragraph");
        

        document.add(par2);



        document.close();

	}

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\I312177\\Desktop\\text.pdf");
		file.createNewFile();
		new PdfTest(file).generatePDF();
		System.out.println("done");
	}

}
