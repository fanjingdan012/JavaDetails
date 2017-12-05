package io;
import java.io.*; 
public class ReadWriteTxt {
	public static void main(String [] args){
		for(int i = 11;i<99;i+=3){
			writeASet(i);
		}
		
		
	}
	public static void writeASet(int portnum){
		File f=new File("D:\\ports\\"+portnum); 
    	f.mkdir();  
		for(int i = 0;i<3;i++){			
	        try{ 	        	
	        	File f1=new File("D:\\ports\\"+portnum+"\\node"+i+".conf"); 
	        	//System.out.print(f1.getName());
	            f1.createNewFile();  
	            PrintWriter o = new PrintWriter(f1);
	            if(i==0){
	            	String s = "";
	            	s+="1 localhost";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+portnum+"0"+j;
		            }
		            s+="\n0 localhost";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+(portnum+1)+"0"+j;
		            }
		            s+="\n2 localhost ";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+(portnum+2)+"0"+j;
		            }
		            //System.out.println(s);
		            o.print(s);
	            }
	            else if(i==1){
	            	String s = "";
	            	s+="1 localhost";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+portnum+"0"+j;
		            }
		            s+="\n0 localhost";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+(portnum+1)+"0"+j;
		            }
		            o.print(s);
	            }
	            else if(i==2){
	            	String s = "";
	            	
	            	s+="2 localhost";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+(portnum+2)+"0"+j;
		            }
		            s+="\n0 localhost";
		            for(int j = 0;j<3;j++){
		            	s+=" 2"+(portnum+1)+"0"+j;
		            }
		            o.print(s);
	            }
	            o.close();
	            
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
		
	}
}
 
