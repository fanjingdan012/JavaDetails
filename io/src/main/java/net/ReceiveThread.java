//package a;
//
//    //说明使用java的HttpConnection 模拟发送post请求。
//
//   //该程序用来抓成绩公布网站上的指定范围准考证号的成绩情况。
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Vector;
//public class ReceiveThread {
//    private static String URL = "http://202.121.151.2:8080/petss2008/petss2008.jsp";
//    private static String url2="http://202.121.151.2:8080/petss2008/petss2008.htm";
//
//    public static void main(String[] args) {
//    	//Post Data 为Form提交的内容。
//    	String postData="zkzh=01040810"+l+i+j+k+"&tijiao.x=26&tijiao.y=8";
//    	String html=GetResponseDataByID(URL,postData);
//    	String[] data=getNeedData(html);
//    }
//
//    public static void PrintStrs(String[] str){
//    	for(String s:str)  {
//    		System.out.print(s+",");
//        }
//        System.out.println();
//   }
//
//
//   //通过url和postdata获取返回值。该函数为主要函数可以copy使用返回值为一个Html页面的数据。
//   public static String GetResponseDataByID(String url,String postData)
//   {
//   String data=null;
//   try {
//   URL dataUrl = new URL(url);
//   HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
//   con.setRequestMethod("POST");
//   con.setRequestProperty("Proxy-Connection", "Keep-Alive");
//   con.setDoOutput(true);
//   con.setDoInput(true);
//
//   OutputStream os=con.getOutputStream();
//   DataOutputStream dos=new DataOutputStream(os);
//   dos.write(postData.getBytes());
//   dos.flush();
//   dos.close();
//
//   InputStream is=con.getInputStream();
//   DataInputStream dis=new DataInputStream(is);
//   te d[]=new byte[dis.available()];
//   dis.read(d);
//   data=new String(d);
//   //System.out.println(data);
//   con.disconnect();
//   } catch (Exception ex) {
//   ex.printStackTrace();
//   }
//   return data;
//   }
//
//   /**Get Needed Data Form Responsed Html File**/
//   static String I1="准考证号";
//   static String I2="姓 名";
//   static String I3="级别";
//   static String I4="笔试成绩";
//   static String I5="口试成绩";
//
//   public static String[] getNeedData(String html) {
//	   if(html==null)
//   return null;
//   String[] str=new String[];
//   str[0]=getPart(html,I1);
//   str[1]=getPart(html,I2);
//   str=getPart(html,I3);
//   str=getPart(html,I4);
//   str=getPart(html,I5);
//
//   return str;
//   }
//
//   public static String getPart(String source,String type)
//   {
//   if(source==null)
//   return null;
//   if(source.indexOf("准考证号输入错误")!=-1)
//   return null;
//   if(source.indexOf(type)!=-1)
//   {
//   source=source.substring(source.indexOf(type)+type.length());
//   source=source.substring(0,source.indexOf("</TD></TR>"));
//   source=source.replace("</TD>", "");
//   source=source.replace("<TD>", "");
//   source=source.replace("<TD align=center >", "");
//   source=source.replace("<TD align=center>", "");
//   source=source.replace("“n","");
//   return source.trim();
//   }
//   else
//   return null;
//   }
//   }
//
//
