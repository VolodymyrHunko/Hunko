package coreJava;

import java.io.*;

public class FileWrightExample {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        File file;
        File file2;
        String myContent = "This is my Data which needs to be written into the file";
        try {
            //Specify the file path here
            file = new File("src\\test\\resources\\myFile.txt");
            file2 = new File("src\\test\\resources\\myFile2.txt");

            /* This logic will check whether the file
             * exists or not. If the file is not found
             * at the specified location it would create
             * a new file*/
            if (!file.exists()) {
                file.createNewFile();
            }
            /*
             * first method by using File Output Stream
             *String content cannot be directly written into
             * a file. It needs to be converted into bytes
             */
            fos = new FileOutputStream(file);
            byte[] bytesArray = myContent.getBytes();
            fos.write(bytesArray);
            fos.flush();
            fos.close();
            System.out.println("File Written Successfully");
            //Here true is to append the content to file
            FileWriter fw1 = new FileWriter(file,true);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            PrintWriter pw = new PrintWriter(bw1);
            //This will add a new line to the file content
            pw.println("");
            /* Below three statements would add three
             * mentioned Strings to the file in new lines.
             */
            pw.println("This is first line");
            pw.println("This is the second line");
            pw.println("This is third line");
            pw.close();

            /*
             *Second method by using FileWriter and append content to the file
             */
            FileWriter fw = new FileWriter(file2);
            bw = new BufferedWriter(fw);
            bw.write(myContent);
            bw.close();
            System.out.println("File 2 written Successfully");
            //Here true is to append the content to file
            FileWriter fw2 = new FileWriter(file2,true);
            //BufferedWriter writer give better performance
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("\n"+myContent+" one more time...");
            //Closing BufferedWriter Stream
            bw2.close();
            System.out.println("Data successfully appended at the end of file");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
