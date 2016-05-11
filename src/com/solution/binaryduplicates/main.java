package com.solution.binaryduplicates;


import java.nio.file.Path;

/**
 *
 * @author Leon
 */
public class main {
     public static void main(String[] args) {
        Path directory;
        BinaryAnalyzer ba = new BinaryAnalyzer();
        
        
        
        directory = ba.getPath();
        
        ba.readFile(directory);    
    }
}

//C:/Users/Leon/Documents/Reading

