package main.java.jalexander.ninja;

import java.io.File;

public class App {
    final static String inFileName = "/file.txt";

    public static void main(String[] args) {
        System.out.println("test test");
    }

    public static File getFile(String outFileName) {
        File inFile = new File(App.class.getResource(inFileName).getFile());
        File outFile = new File(inFile.getParentFile(), outFileName);
        return outFile;
    }
}
