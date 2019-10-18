
import java.io.*; 


import java.net.*; 
import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.util.Iterator;

import java.util.Set;



/*import org.apache.commons.httpclient.Cookie;

import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.HttpException;

import org.apache.commons.httpclient.NameValuePair;

import org.apache.commons.httpclient.methods.PostMethod;

*/



public class Net { 
	public static void main(String args[]) throws Exception {		

		URL tirc = new URL("https://bbs.fudan.edu.cn/bbs/top10"); 

		BufferedReader in = new BufferedReader(new InputStreamReader(tirc.openStream()));
		//BufferedWriter out = new BufferedWriter(out);

	    String inputLine;
	    while ((inputLine = in.readLine()) != null) {
	    	System.out.println(inputLine); 
	    }
	    	
	    in.close(); 
       


/*Socket s = new Socket("www.sina.com", 8080); 


//PrintWriter pw = new PrintWriter(s.getOutputStream()); 

System.out.print("connected");
BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 





System.out.println(">> Connection with server established"); 


//pw.println("Hello World!"); 


//pw.flush(); 





// Print input 


System.out.println(">> Reading output from server"); 


System.out.print(br.readLine()); 


System.out.println(">> Done!"); 





// close sockets 


//pw.close(); 


br.close(); 


s.close(); */

		
		
		
		
		
		
		
		
		
	/*	HttpClient client = new HttpClient();

		client.getHostConfiguration().setHost("bbs.baiyou100.com", 8080);

		PostMethod post = new PostMethod("http://bbs.baiyou100.com/login.aspx");

		NameValuePair username = new NameValuePair("username", name);

		NameValuePair password = new NameValuePair("password", pas);

		post.setRequestBody(new NameValuePair[] { username, password });

		try {

		System.out.println("准备登陆");

		client.executeMethod(post);

		System.out.println("开始登陆");

		} catch (HttpException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		} catch (IOException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}

		String responseString = null;

		try {

		responseString = new String(post.getResponseBodyAsString()

		.getBytes("gb2312"));

		} catch (UnsupportedEncodingException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		} catch (IOException e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}

		Cookie[] cookies = client.getState().getCookies();

		client.getState().addCookies(cookies);

		post.releaseConnection();

		Set set = new UserList().getUserList();



		// 遍历集合

		Iterator it = set.iterator();

		for (; it.hasNext();) {

		String sName = (String) it.next();

		// 提交短

		System.out.println("提交短消息");

		post = new UTF8PostMethod("http://bbs.baiyou100.com/usercppostpm.aspx");

		NameValuePair names = new NameValuePair("msgto", sName);

		NameValuePair biaoti = new NameValuePair("subject", "“"+sName+"标题");

		NameValuePair neirong = new NameValuePair("message","内容 ");

		NameValuePair sendmsg = new NameValuePair("sendmsg","%E7%AB%8B%E5%8D%B3%E5%8F%91%E9%80%81");

		post.setRequestBody(new NameValuePair[] { names, biaoti, neirong,sendmsg });

		post.setRequestHeader("Referer","http://bbs.baiyou100.com/usercppostpm.aspx");

		post.setRequestHeader("Accept-Language", "zh-cn");

		try {

		client.getState().addCookies(cookies);

		client.executeMethod(post);

		responseString = new String(post.getResponseBodyAsString().getBytes("gb2312"));

		if(responseString.lastIndexOf("发送完毕")>=0){

		System.out.println(sName+" - OK");

		}

		} catch (HttpException e1) {

		// TODO Auto-generated catch block

		e1.printStackTrace();

		} catch (IOException e1) {

		// TODO Auto-generated catch block

		e1.printStackTrace();

		}

		}

		return null;*/


	} 


} 



