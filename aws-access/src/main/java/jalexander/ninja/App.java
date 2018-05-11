package jalexander.ninja;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import com.amazonaws.auth.*;
import com.amazonaws.services.lambda.*;
import com.amazonaws.services.lambda.model.*;

import com.google.gson.Gson;

public class App {
    final static String inFileName = "/file.txt";

    public static void main(String[] args) throws IOException {
        if (args.length != 5) {
            System.out.println("Arguments: <functionName> <numIterations> <iterWait> <inputInt> <inputString>");
            return;
        }

        String functionName = args[0];
        int numIterations = Integer.parseInt(args[1]);
        int iterWait = Integer.parseInt(args[2]);
        int inputInt = Integer.parseInt(args[3]);
        String inputString = args[4];

        File credentialFile = new File(getFile("secrets"), "credentials.txt");
        String accessKeyId;
        String secretKey;

        try (
                FileReader fileReader = new FileReader(credentialFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            accessKeyId = bufferedReader.readLine();
            secretKey = bufferedReader.readLine();
        }

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKey);
        AWSLambda client = AWSLambdaClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

        Gson gson = new Gson();
        RequestClass payload = new RequestClass();
        payload.inputInt = inputInt;
        payload.inputString = inputString;

        StringBuilder csvOutput = new StringBuilder();

        csvOutput.append("functionName,numIterations,iterWait,inputInt,inputString,localTime,outputInt,time1,time2,longTime1,longTime2,outputDouble,outputString,errorMessage\n");

        for (int i = 0; i < numIterations; i++) {
            InvokeRequest request = new InvokeRequest();
            request.setFunctionName(functionName);

            request.setPayload(gson.toJson(payload));

            long start = System.currentTimeMillis();
            InvokeResult resultObj = client.invoke(request);
            long end = System.currentTimeMillis();

            long localTime = end - start;

            String resultString = new String(resultObj.getPayload().array(), "UTF-8");
            System.out.println(resultString);

            ResponseClass result = gson.fromJson(resultString, ResponseClass.class);

            csvOutput.append(functionName + "," + numIterations + "," + iterWait + "," + inputInt + "," + inputString + "," + localTime + "," + result.csvString() + "\n");


            try {
                Thread.sleep(iterWait);
            } catch (InterruptedException e) {
                System.err.println("Wait interrupted");
                System.err.println(e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        System.out.println(csvOutput);
    }

    public static File getFile(String outFileName) {
        File inFile = new File(App.class.getResource(inFileName).getFile());
        File outFile = new File(inFile.getParentFile(), outFileName);
        return outFile;
    }
}
