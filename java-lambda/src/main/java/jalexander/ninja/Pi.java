package jalexander.ninja;

import com.amazonaws.services.lambda.runtime.Context;

public class Pi {
    public static ResponseClass handler(RequestClass request, Context context) {
        ResponseClass response = new ResponseClass();
        response.outputString = "(java) calculated Pi with " + request.inputInt + " iterations";

        long start = System.currentTimeMillis();
        response.outputDouble = calcPi(request.inputInt);
        long end = System.currentTimeMillis();
        response.longTime1 = end - start;

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

    public static void main(String[] args){
        RequestClass request = new RequestClass();
        request.inputInt = Integer.parseInt(args[0]);
        request.inputString = args[1];
        ResponseClass response = handler(request, null);
        System.out.println(response.csvString());
    }
}
