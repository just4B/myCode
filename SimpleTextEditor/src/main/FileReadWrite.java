/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author just4b
 */
public class FileReadWrite {
    
    private StringBuilder fileContent;
    public StringBuilder getFileContent ( ){
        return this.fileContent;
    }
    
    private String filePath;
    public void setFilePath (String filePath) {
        this.filePath = filePath;
    }
    public String getFilePath () {
        return this.filePath;
    }
    
    public FileReadWrite (String filePath) {
        this.filePath = filePath;
        this.fileContent = new StringBuilder();
    }
    
    public boolean readFile ( ) {
        try {
            this.fileContent = new StringBuilder();
            File file = new File(this.filePath);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                   this.fileContent.append(scan.nextLine());
                   this.fileContent.append("\n");
            }
            return true;
        } catch (Exception exc) {
            return false;
        }
    }
    
    public boolean writeFile (String content) {
        try {
            PrintWriter pr = new PrintWriter(this.filePath);
            pr.print(content);
            pr.close();
            return true;
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
            return false;
        }
    }
    
}
