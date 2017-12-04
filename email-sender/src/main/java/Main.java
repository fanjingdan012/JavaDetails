

import util.Email;
import util.Sender;


public class Main {
	public static void main(String[] args){  

		Email mailInfo = new Email();   
		mailInfo.setMailServerHost("smtp.live.com");   
		mailInfo.setMailServerPort("25");   
		mailInfo.setValidate(true);   
		mailInfo.setUserName("****@hotmail.com");
		mailInfo.setPassword("****");
		mailInfo.setFrom("****@hotmail.com");
		mailInfo.setTo("****@gmail.com");
		mailInfo.setSubject("TEST - PAA error notification - Action required");   
		mailInfo.setContent("*** SYSTEM GENERATED - DO NOT REPLY TO THIS EMAIL ***\n\nDear PAA Administrator,\n\nFollowing PAA error occured during processing:\n\n${attChangeTime} ${atpErrorData}\n${atpFileName}");   

		Sender sms = new Sender();  
		sms.sendTextMail(mailInfo);
         
        
   }  

		  
	  

}
