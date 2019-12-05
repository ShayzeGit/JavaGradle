package com.cas.picfg;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.*;
import org.bytedeco.opencv.opencv_core.*;


import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

public class App {

    File f = new File("imgs");

    public static void main(String[] args) {

        final Options options = new Options();
        options.addOption(new Option("f", "filters", true, "Entrez le filtre que vous souhaitez !"));

         CommandLineParser parser = new DefaultParser();
         CommandLine cmd = null;

         try {
            cmd = parser.parse( options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String filterchoice = cmd.getOptionValue("blur");


            System.out.println(Arrays.toString(args));

            List<Filter> filters = new ArrayList<>();
            String filterArg = "blur|grayscale";

        String[] split = filterArg.split("\\|"); // blur, grayscale

        for (String s : split) {
                  switch (s) {
                      case "blur":                                 // add Filtres par choix
                             filters.add(new FilterBlur());
                          break;
                      case "grayscale":
                           filters.add(new FilterGrayscale()) ;         //  add Filtres par choix
                          break;

                  }

        }


     Mat image = imread("imgs/inspi.jpg");

     for (Filter f : filters) {
         image = f.process(image);
     }

     //FilterBlur filterBlur = new FilterBlur();
     // image = filterBlur.filterBlur(image);

     // FilterGrayscale f = new FilterGrayscale();
     // image = f.filterGrayscale(image);
     imwrite("imgsoutput/inspi_new.jpg", image);

    }
}
