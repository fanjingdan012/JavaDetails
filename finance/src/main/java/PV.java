
public class PV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a = 500*(PV(0.1,3)+PV(0.1,4)+PV(0.1,5)+PV(0.1,6)+PV(0.1,7));
		System.out.println(g(0.21875));
	}
	public static double PV(double rate,int n){
		
		double result = 1/Math.pow(1+rate, n);
		return result;
	}
	public static  double g(double x){
		double result=x/(1-x);
		return result;
	}
	

}
