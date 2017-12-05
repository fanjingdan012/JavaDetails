package io;
import java.io.*; 
import java.text.SimpleDateFormat; 
import java.util.*; 
import java.sql.*; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Map; 
//import jxl.*; 
public class Readxls { 
	private String fileName; 
	public Readxls(String fileName){ 
		this.fileName = fileName; 
	}
static Map tNames; 
static{ 
tNames = new HashMap(); 
} 
//下面是主要代码 

private void updateDb(){ 
//try{ 
/*Connection conn = DbPool.connectDB(); 
if(conn != null){ 
Statement stmt = conn.createStatement(); 
/********************************************* 
jxl.Workbook rwb = null; 
try{ 
//构建Workbook对象 只读Workbook对象 
//直接从本地文件创建Workbook 
//从输入流创建Workbook 
InputStream is = new FileInputStream(fileName); 
rwb = Workbook.getWorkbook(is); 
//Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中 
//Sheet的下标是从0开始的 
//获取第一张Sheet表 
Sheet rs = rwb.getSheet(0); 
//获取Sheet表中所包含的总列数 
int rsColumns = rs.getColumns(); 
//获取Sheet表中所包含的总行数 
int rsRows = rs.getRows(); 
//获取指这下单元格的对象引用 

String simNumber = "",termSeqId = ""; 
//指定SIM卡号及序列号 
for(int i=0;i<rsRows;i++){ 
for(int j=0;j<rsColumns;j++){ 
Cell cell = rs.getCell(j,i); 
if(j==0){ 
simNumber = cell.getContents();//这里是猎取你要的参数，和下面一样 
} 
if(j == 1){ 
termSeqId = cell.getContents(); 
} 
} 
String sql = "update ....";//SQL语句 
int isOk = stmt.executeUpdate(sql); 
if(isOk == 0){ 

String insertSql = "insert....";//SQL语句 
int isAdd = stmt.executeUpdate(insertSql); 
if(isAdd > 0){ 
System.out.println("成功插入第"+i+"条数据"); 
} 

} 

} 

//以下代码为写入新的EXCEL,这里不使用,所以注释 
/* 

//利用已经创建的Excel工作薄创建新的可写入的Excel工作薄 
jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(new File("D://Book2.xls"),rwb); 
//读取第一张工作表 
jxl.write.WritableSheet ws = wwb.getSheet(0); 

//获取第一个单元格对象 
jxl.write.WritableCell wc = ws.getWritableCell(0, 0); 
//决断单元格的类型，做出相应的转化 
if (wc.getType() == CellType.LABEL) { 
Label l = (Label) wc; 
l.setString("The value has been modified."); 
} 
//写入Excel对象 
wwb.write(); 
wwb.close(); 
*/ 
/*
}catch(Exception e){ 
e.printStackTrace(); 
} 
finally{ 
//操作完成时，关闭对象，翻译占用的内存空间 
rwb.close(); 

} 
/*********************************************/ 



/*} 
}catch(Exception e){ 
e.printStackTrace(); 
} */
} 


}