package util;


import java.util.Properties;   
public class Email {   
	

    private String mailServerHost;   
    private String mailServerPort = "25";   
    private String from;
    private String to;
    private String userName;
    private String password;   
    private boolean validate = true;
    private String subject;
    private String content;
    private String[] attachFileNames;

    public Properties getProperties(){  
    	Properties p = new Properties();   
    	p.setProperty("mail.smtp.host", this.mailServerHost);  
    	p.put("mail.smtp.starttls.enable","true");
    	p.setProperty("mail.smtp.port", this.mailServerPort); 
    	p.setProperty("mail.smtp.auth", validate ? "true" : "false");  
    	p.setProperty("mail.debug", "true");
    	return p;   
    }
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getAttachFileNames() {
		return attachFileNames;
	}
	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}   
	public boolean isValidate() { 
		return validate;   
	}  
	public void setValidate(boolean validate) {   
	    this.validate = validate;   
	}  
    public String getMailServerPort() {   
        return mailServerPort;   
    }  
    public void setMailServerPort(String mailServerPort) {   
        this.mailServerPort = mailServerPort;   
    }
    public String getMailServerHost() {   
        return mailServerHost;   
    }   
    public void setMailServerHost(String mailServerHost) {   
        this.mailServerHost = mailServerHost;   
    } 
 
}   
