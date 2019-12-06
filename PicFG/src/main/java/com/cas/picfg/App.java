package com.cas.picfg;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.cli.*;
import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

public class App {

    File f = new File("imgs");


    public static void main(String[] args) {

        Logger log = new Logger();
        log.deleted();

        final Options options = new Options();
        options.addOption(new Option("f", "filters", true, "Entrez le filtre que vous souhaitez !"));
        options.addOption(new Option("i", "input-dir", true, "Entrez le dossier d'image à traiter"));
        options.addOption(new Option("o", "output-dir", true, "Entrez le dossier d'image pour enregistrer"));

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String inputchoice = cmd.getOptionValue("i");
        String filterchoice = cmd.getOptionValue("f");
        String outputchoice = cmd.getOptionValue("o");

        log.write("\033[0;36mfichier d'origine : \033[0;35m"+inputchoice+"\033[0m");
        log.write("\033[0;36mfiltres appliqué : \033[0;35m"+filterchoice+"\033[0m");
        log.write("\033[0;36mfichier de sauvgarde : \033[0;35m"+outputchoice+"\033[0m\n");

        File inputdir = new File(inputchoice);

        File[] files = inputdir.listFiles();

        ArrayList<String> list = new ArrayList<>();

        for (File f : files) {
            list.add(f.getAbsolutePath());
        }


        List<Filter> filters = new ArrayList<>();
        String filterArg = cmd.getOptionValue("filters");

        String[] split = filterArg.split(Pattern.quote("|")); // blur, grayscale

        for (String s : split) {
            switch (s) {
                case "blur":                                 // add Filtres par choix
                    filters.add(new FilterBlur());
                    break;
                case "grayscale":
                    filters.add(new FilterGrayscale());         //  add Filtres par choix
                    break;
                case "dilate":
                    filters.add(new FilterDilate());         // add Filtres par choix
                    break;
            }

        }


        //Parcourir toutes les images pour appliquer un filtre sur chaque image
        for (String filePath : list){
            Mat image = imread(filePath);

            for (Filter f : filters) {
                image = f.process(image);
            }
            String output = cmd.getOptionValue("output-dir");
            File outputDir = new File(output);
            outputDir.mkdirs();

            File f = new File(filePath);
            File outputFile = new File(outputDir, f.getName());

            imwrite(outputFile.getAbsolutePath(), image);


        }

        log.read();

    }
}

