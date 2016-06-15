/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author just4b
 */
public class FileService {
    
    private static String filePath = "";
    public static void setFilePath (String filePath ) {
        FileService.filePath = filePath;
        String[] fileParts = filePath.split("/");
        int length = fileParts.length;
        FileService.fileName = fileParts[fileParts.length - 1];
        fileParts[fileParts.length - 1] = "";
        StringBuilder fileDir = new StringBuilder();
        for (int i = 0; i < fileParts.length; i++) {
             fileDir.append(fileParts[i]);
             fileDir.append("/");
        }
        FileService.lastDir = fileDir.toString().replaceAll("//", "/");
        
    }
    public static String getFilePath () {
        return FileService.filePath;
    }
    
    private static String lastDir = "";
    public static String getLastDir () {
        return FileService.lastDir;
    }
    
    private static String fileName = "";
    public static String getFileName () {
        return FileService.fileName;
    }
    
    public static String readFile () {
        FileReadWrite fileRW = new FileReadWrite(filePath);
        fileRW.readFile();
        return fileRW.getFileContent().toString();
    }
    
    public static boolean writeFile (String content) {
        FileReadWrite fileRW = new FileReadWrite(filePath);
        return fileRW.writeFile(content);
    }
    
    
    
    
}
