package jalexander.ninja;

import com.amazonaws.services.lambda.runtime.Context;

import java.util.Arrays;

public class Merge {
    public static class RequestClass {
        public int inputInt;
        public String inputString;
    }

    public static class ResponseClass {
        public int outputInt;
        public double time1;
        public double time2;
        public long longTime1;
        public long longTime2;
        public double outputDouble;
        public String outputString;
    }

    private static class MergeSorter extends Thread {
        public int[] arr;
        public int depth;
        public static int started;
        final static int THREAD_DEPTH = 3;

        public MergeSorter(int[] a, int d) {
            arr = a;
            depth = d;
        }

        public void run() {
            if (arr.length <= 1) {
                return;
            }

            if (arr.length == 2) {
                if (arr[1] < arr[0]) {
                    int t = arr[1];
                    arr[1] = arr[0];
                    arr[0] = t;
                }
                return;
            }

            int split = arr.length / 2;
            MergeSorter left = new MergeSorter(Arrays.copyOfRange(arr, 0, split), depth + 1);
            if (depth <= THREAD_DEPTH) {
                left.start();
                started++;
            } else {
                left.run();
            }
            MergeSorter right = new MergeSorter(Arrays.copyOfRange(arr, split, arr.length), depth + 1);
            right.run();

            if (depth <= THREAD_DEPTH) {
                try {
                    left.join();
                } catch (InterruptedException e) {
                    System.err.println("Interrupted in MergeSorter");
                    System.exit(1);
                }
            }

            int lPos = 0;
            int rPos = 0;
            int cPos = 0;

            while (lPos < left.arr.length || rPos < right.arr.length) {
                if (rPos >= right.arr.length) {
                    arr[cPos] = left.arr[lPos];
                    lPos++;
                } else if (lPos >= left.arr.length) {
                    arr[cPos] = right.arr[rPos];
                    rPos++;
                } else if (left.arr[lPos] < right.arr[rPos]) {
                    arr[cPos] = left.arr[lPos];
                    lPos++;
                } else {
                    arr[cPos] = right.arr[rPos];
                    rPos++;
                }
                cPos++;
            }
        }
    }

    public static void mergeSort(int[] arr) {
        MergeSorter m = new MergeSorter(arr, 0);
        m.start();
        try {
            m.join();
        } catch (InterruptedException e) {
            System.err.println("Interrupted in mergeSort");
            System.exit(1);
        }

        /*
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("Started: " + MergeSorter.started);
        */
    }

    public static ResponseClass handler(RequestClass request, Context context){
        ResponseClass response = new ResponseClass();
        response.outputString = "perfomed merge sort with " + request.inputInt + " elements";

        long start2 = System.currentTimeMillis();
        int[] arr = new int[request.inputInt];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * Integer.MAX_VALUE);
        }

        long start = System.currentTimeMillis();
        mergeSort(arr);
        long end = System.currentTimeMillis();

        response.longTime1 = end - start;
        response.longTime2 = end - start2;
        return response;
    }
}
