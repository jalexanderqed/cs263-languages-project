import com.amazonaws.services.lambda.runtime.Context;

public class Pi {
    public static class RequestClass {
        public int inputInt;
        public String inputString;
    }

    public static class ResponseClass {
        public int outputInt;
        public String outputString;
    }

    public static ResponseClass handler(RequestClass request, Context context){
        ResponseClass response = new ResponseClass();
        response.outputString = "input int is " + request.inputInt;
        return response;
    }

    public static double calcPi(int iter) {
        double currentVal = 1;
        double sign = -1;
        double denom = 3;

        for (int i = 0; i < iter; i++) {
            currentVal = currentVal + sign / denom;
            denom += 2;
            sign *= -1;
        }

        return 4 * currentVal;
    }
}
