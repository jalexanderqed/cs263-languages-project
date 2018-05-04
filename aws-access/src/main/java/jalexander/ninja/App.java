package main.java.jalexander.ninja;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import com.amazonaws.auth.*;
import com.amazonaws.services.lambda.*;
import com.amazonaws.services.lambda.model.*;

public class App {
    final static String inFileName = "/file.txt";

    public static void main(String[] args) throws IOException {
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

        InvokeRequest request = new InvokeRequest();
        request.setFunctionName("pigo");
        request.setPayload("{\"inputInt\":10000000, \"inputString\":\"teststring\"}");

        long start = System.currentTimeMillis();
        InvokeResult result = client.invoke(request);
        long end = System.currentTimeMillis();

        System.out.println(new String(result.getPayload().array(), "UTF-8"));

        System.out.println("Invocation time: " + (end - start));
    }

    public static File getFile(String outFileName) {
        File inFile = new File(App.class.getResource(inFileName).getFile());
        File outFile = new File(inFile.getParentFile(), outFileName);
        return outFile;
    }
}
