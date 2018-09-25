package checksum.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MD5 {

    public String getSalt() {
        String salt=new String();
		
		salt = new String()+(1000+(int)(Math.random()*8000-1));
		
		return salt;
		
	}
	

	public String toMD5(String password) {	         
	    String myHash=new String();
	    String salt = getSalt();
	  
	    password = password+salt;
	   
	    System.out.println("Password:"+password);
	    System.out.println("Salt:"+salt);
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			 md.update(password.getBytes());
			    byte[] digest = md.digest();
			    myHash = DatatypeConverter
			      .printHexBinary(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return myHash;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(new MD5().toMD5("iamnoteasytobreak"));
//" AAF8A6D56D4A0AB1E2D1D49329AB6956"
//		aa();
		tryPwd();
	}

	private static void aa(){
    	char c='a';
    	for(int i=0;i<26;i++){
			System.out.println(c);
			c++;
		}
	}

	private static String getmd5(String password){
		MessageDigest md;
		String myHash="";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter
					.printHexBinary(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myHash;
	}

	private static boolean tryPwd(){
		for(int i = 1000;i<9001;i++){
//			String nstr= ""+pwd+i;
			String md5=getmd5("iamnoteasytobreak"+i);
			if("AAF8A6D56D4A0AB1E2D1D49329AB6956".equals(md5)){
				System.out.println(i);
				return true;
			}
		}
		return false;
	}

}
