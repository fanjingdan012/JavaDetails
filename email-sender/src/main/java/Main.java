

import util.Email;
import util.Sender;


public class Main {
	public static void main(String[] args){  

		Email mailInfo = new Email();   
		mailInfo.setMailServerHost("smtp.live.com");   
		mailInfo.setMailServerPort("25");   
		mailInfo.setValidate(true);   
		mailInfo.setUserName("fanjingdan@hotmail.com");   
		mailInfo.setPassword("Ch3ch2oh");
		mailInfo.setFrom("fanjingdan@hotmail.com");   
		mailInfo.setTo("fanjingdan@gmail.com");   
		mailInfo.setSubject("TEST - PAA error notification - Action required");   
		mailInfo.setContent("*** SYSTEM GENERATED - DO NOT REPLY TO THIS EMAIL ***\n\nDear PAA Administrator,\n\nFollowing PAA error occured during processing:\n\n${attChangeTime} ${atpErrorData}\n${atpFileName}");   

		Sender sms = new Sender();  
		sms.sendTextMail(mailInfo);
         
        
   }  

		  
	  

}
