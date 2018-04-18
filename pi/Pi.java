public class Pi{
    public static void main(String[] args){
	System.out.println("Pi is (about) " + calcPi(1000));
    }

    public static double calcPi(int iter){
	double currentVal = 1;
	double sign = -1;
	double denom = 3;

	for(int i = 0; i < iter; i++){
	    currentVal = currentVal + sign / denom;
	    denom += 2;
	    sign *= -1;
	}

	return 4 * currentVal;
    }
}
