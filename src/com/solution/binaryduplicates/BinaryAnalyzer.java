package com.solution.binaryduplicates;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This program takes in a directory and checks whether that directory contains
 * duplicate binary data. The objective of the program is to report these
 * duplicate files in a log file.
 *
 * @author Leon Africa, May 2016
 */
public class BinaryAnalyzer {

    String directoryPath; //The user entered path
    Path theDirectory; //After checking whether the entered directory(String directoryPath) was valid
    ArrayList byteList = new ArrayList(); //Holds bytes 
    Object[] obj;
    boolean watch = false;
    //2d ArrayList for holding specific file numbers of duplicate files
    List<List<Integer>> holdFileNums = new ArrayList<>();
    List<Integer> x = new ArrayList<>();

    /**
     * Takes in the path for the directory containing the files to be checked
     *
     * @param path
     */
    public void readFile(Path path) {

        try {
            Files.walk(Paths.get(path.toString())).forEach(filePath -> {
                //Collect files in directory to be checked
                if (Files.isRegularFile(filePath)) {

                    try {
                        byteList.add(Files.readAllBytes(filePath));

                    } catch (IOException ex) {
                        Logger.getLogger(BinaryAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        } catch (IOException ex) {
            Logger.getLogger(BinaryAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unable to read files, please enter directory path correctly.");
        } catch (NullPointerException ne) {
            System.out.println("Unable to read contents of a non existent directory.");
        }

        //Find Duplicates
        for (int i = 0; i < byteList.size(); i++) {

            for (int j = i + 1; j < byteList.size(); j++) {
                //Compare byte arrays in file
                //NOTE: The casting to byte[] is required because before runtime the compiler still sees bytelist as an Arraylist.
                if (Arrays.equals((byte[]) byteList.get(i), (byte[]) byteList.get(j))) {
                    //Add each specific file duplicate
                    x.add((i + 1));
                    x.add((j + 1));
                }
            }
        }

        //Numbers to duplicate files
        holdFileNums.add(x);

        //Convert to Array
        holdFileNums.stream().forEach((ls) -> {
            obj = ls.toArray();
        });

        //Create Log File
        if(watch == true){
            System.out.println("Please enter a valid directory BEFORE pressing enter.");
            //Prevent Logical error
            exit(0);
        }else{
            //Create the log file
            makeLogFile(obj);
        }
       

    }

    /**
     * Collects the user entered path to the files
     *
     * @return the directory in which the files are.
     */
    public Path getPath() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("This program takes in a directory and checks whether that directory contains duplicate binary data.");
        System.out.println("The objective of the program is to report these duplicate files in a log file.");
        System.out.println("NOTE: Use '/'  to enter a path, eg 'C:/Users/...'");
        
        System.out.print("Please enter the path to the directory: ");

        try {

            directoryPath = reader.readLine();

            //Check if the user entered path is a valid directory
            if (Files.isDirectory(Paths.get(directoryPath))) {
                //Set the path
                
                theDirectory = Paths.get(directoryPath);
                
                
            } else {
                System.out.println("The directory entered is invalid or does not exist");

            }

        } catch (IOException ex) {
            Logger.getLogger(BinaryAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidPathException ip) {
            System.out.println("The path you entered is invalid, please enter a valid path of a directory.");
        }catch(NullPointerException ne){
               System.out.println("Please pay attention while entering the path.");
        }
        
        //Prevent logical error
        try{
            if(theDirectory.toString().length() == 0){
            watch = true;
             
        }
        
        }catch(NullPointerException ne){
            System.out.println("Please pay attention when entering the path.");
            //Prevent logical error
            exit(0);
        }
        

        return theDirectory;
    }

    /**
     * Create the Log messages, takes in array containing file numbers of
     * duplicates.
     *
     * @param array
     */
    public void makeLogFile(Object[] array) {
        Logger logger = Logger.getLogger("MyLog");
        File logFile;
        FileHandler fh;

        try {
            //Create the Log File in the current directory
            File homedir = new File(System.getProperty("user.dir"));
            logFile = new File(homedir, "log.txt");

            // Configure the logger with handler and formatter 
            fh = new FileHandler(logFile.getPath());
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            //Remove console logging
            logger.setUseParentHandlers(false);

            // Log the message to the file 
            for (int h = 0; h < array.length; h++) {
                logger.log(Level.INFO,"file_000{0}" + " is a duplicate of " + "file_000{1}", new Object[]{array[h], array[h + 1]});
                h = h + 1;
            }

            System.out.println("Success! Check this current working directory to find the log file reporting duplicates.");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

    }

}
