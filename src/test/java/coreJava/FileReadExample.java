package coreJava;

import java.io.*;

public class FileReadExample {
    public static void main(String[] args) {
        //Specify the path of the file here
        File file = new File("src\\test\\resources\\myFile.txt");
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        try {
            //FileInputStream to read the file
            fis = new FileInputStream(file);

            /*Passed the FileInputStream to BufferedInputStream
             *For Fast read using the buffer array.*/
            bis = new BufferedInputStream(fis);

            /*available() method of BufferedInputStream
             * returns 0 when there are no more bytes
             * present in the file to be read*/
            while( bis.available() > 0 ){
                System.out.print((char)bis.read());
            }
            bis.close();
            fis.close();
        }catch(FileNotFoundException fnfe) {
            System.out.println("The specified file not found" + fnfe);
        }
        catch(IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }
        //read the file by using Buffer Reader
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src\\test\\resources\\myFile2.txt"));
            //One way of reading the file
            System.out.println("Reading the file using readLine() method:");
            String contentLine = br.readLine();
            while (contentLine != null) {
                System.out.println(contentLine);
                contentLine = br.readLine();
            }
            br.close();
        }catch (IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }
    }
}

