package com.cas.picfg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Logger {

    private FileWriter stylo;
    private File logger;

    public void write(String message){
        try {
            stylo = new FileWriter("logger.log", true);
            stylo.write(message+"\n");
            stylo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){
        try {
            logger = new File("logger.log");
            Scanner reader = new Scanner(logger);
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleted(){
        try {
            stylo = new FileWriter("logger.log", false);
            stylo.write("");
            stylo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
