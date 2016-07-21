import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Author Fan, Jingdan
 *
 */
public class ExcelTest
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        HashMap<String,String> list = new HashMap<String,String>();
        list.put("aa", "1");
        list.put("bb", "2");
        writeExcel(new File("material/example.xlsx"),list);
        readExcel(new File("material/example.xlsx"));
    }
    
    
	
	
	public static void readExcel(File file) throws IOException {
		String fName = file.getName();
		String extension = fName.lastIndexOf(".") == -1 ? "" : fName
				.substring(fName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {// 2003
			System.out.println("读取excel2003文件内容");
			read2003Excel(file);
		} else if ("xlsx".equals(extension)) {// 2007
			System.out.println("读取excel2007文件内容");
			read2007Excel(file);
		} else {
			throw new IOException("不支持的文件类型:" + extension);
		}
	}
	
	/**
	 * read 2003excel
	 * 
	 * @param file
	 * @return
	 */
	private static void read2003Excel(File file)
			throws IOException {
		
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		HSSFCell cell = null;
		Object val = null;
		DecimalFormat df = new DecimalFormat("0");// 格式化数字
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
		for (int i = sheet.getFirstRowNum(); i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			System.out.println(row.getCell(0).getStringCellValue());
			System.out.println(row.getCell(1).getStringCellValue());
	        
		}
		return ;
	}
	
	/**
	 * read 2007excel
	 * 
	 * @param file
	 * @return
	 */

	private static void read2007Excel(File file)
			throws IOException {
        
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row = null;
		for (int i = sheet.getFirstRowNum(); i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			
			System.out.println(row.getCell(0).getStringCellValue());
			System.out.println(row.getCell(1).getStringCellValue());
			
		}
		return;
	}
	public static void writeExcel(File file,HashMap<String,String> list) throws IOException {
		String fName = file.getName();
		String extension = fName.lastIndexOf(".") == -1 ? "" : fName
				.substring(fName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {// 2003
			System.out.println("write excel2003");
			write2003Excel(file,list);
		} else if ("xlsx".equals(extension)) {// 2007
			System.out.println("write excel2007");
			write2007Excel(file,list);
		} else {
			throw new IOException("不支持的文件类型:" + extension);
		}
	}

	private static void write2007Excel(File file,HashMap<String,String> list) {
		XSSFWorkbook wb = new XSSFWorkbook();  
		XSSFSheet sheet = wb.createSheet();  
        int i = 0;
        for (String label:list.keySet())  
        {  
        	
        	XSSFRow row = sheet.createRow((int) i);  
        	String stu = list.get(label);  
            row.createCell((short) 0).setCellValue(label);  
            row.createCell((short) 1).setCellValue(stu);  
            
            i++;
        }  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(file);  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
	

	private static void write2003Excel(File file,HashMap<String,String> list) {
        HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet();  
        int i = 0;
        for (String label:list.keySet())  
        {  
        	
        	HSSFRow row = sheet.createRow((int) i);  
            String stu = list.get(label);  
            row.createCell((short) 0).setCellValue(label);  
            row.createCell((short) 1).setCellValue(stu);  
            i++;
        }  
        try  
        {  
            FileOutputStream fout = new FileOutputStream(file);  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
	}
}
