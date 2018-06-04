package jalexander.ninja;

import com.amazonaws.services.lambda.runtime.Context;

public class NthPrime {
    public static ResponseClass handler(RequestClass request, Context context) {
        ResponseClass response = new ResponseClass();
        response.outputString = "(java) calculated " + request.inputInt + "th prime";

        long start = System.currentTimeMillis();
        response.outputInt = nthPrime(request.inputInt);
        long end = System.currentTimeMillis();
        response.longTime1 = end - start;

        return response;
    }

    public static int nthPrime(int n) {
        if (n == 1) {
            return 2;
        }
        int count = 1;
        int num = 3;
        while (count < n) {
            if (isPrime(num)) {
                count += 1;
            }
            num += 2;
        }
        return num - 2;
    }

    public static boolean isPrime(int num) {
        int factor = 2;
        while (factor * factor <= num) {
            if (num % factor == 0) {
                return false;
            }
            factor++;
        }
        return true;
    }

    public static void main(String[] args){
        RequestClass request = new RequestClass();
        request.inputInt = Integer.parseInt(args[0]);
        request.inputString = args[1];
        ResponseClass response = handler(request, null);
        System.out.println(response.csvString());
    }
}
