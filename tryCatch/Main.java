package tryCatch;

public class Main {
	public static  void a (){
		try{
			int a = Integer.parseInt("1");
			return;
		}catch(Exception e){
			
		}finally{
			//before return finally will be executed
			System.out.print("aaaaa");
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		a();
	}


}
