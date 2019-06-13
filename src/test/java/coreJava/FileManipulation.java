package coreJava;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Scanner;

public class FileManipulation{

    String base = System.getProperty("user.dir");

    FileWrightExample fwr = new FileWrightExample();
    @Test
    void testFirstwriting() {
        fwr.firstMethod();
    }

    @Test
    void testSecondwriting() throws IOException {
        fwr.secondMethod();
    }

    @Test
    void testAppendFile() throws IOException {
        fwr.appendFileDta();
    }

    @Test
    void testReadFileFirst(){
        FileRead fr = new FileRead();
        fr.readFirst();
    }

    @Test
    void testBufferReader(){
        FileRead fr = new FileRead();
        fr.readBufferReader();
    }


    @Test
    void testScanner() throws FileNotFoundException {
        FileRead fr = new FileRead();
        fr.readScanner();
    }

    @Test
    void testSerialization() throws IOException {
        PersonSerialization pr = new PersonSerialization(55, "VoloHu");
        pr.id = 212;
        ObjectOutputStream outObject = new ObjectOutputStream(
                new FileOutputStream(base+"/src/test/resources/Object_File.txt"));
        outObject.writeObject(pr);
        outObject.flush();
        outObject.close(); // .close() calls the .flash(), so .flash() is redundant

        System.out.println("Object saved as: "+ pr);
    }

    @Test
    void testDeserialization() throws Exception {
        ObjectInputStream inObject = new ObjectInputStream(
                new FileInputStream(base+"/src/test/resources/Object_File.txt"));
        PersonSerialization p = (PersonSerialization) inObject.readObject();
        System.out.println(p);
        inObject.close();
    }
}

 class FileWrightExample {
    String base = System.getProperty("user.dir");
    FileOutputStream fos;
    BufferedWriter bw;
    File file = new File(base+"/src/test/resources/myFile.txt");
    File file2 = new File(base+"/src/test/resources/myFile2.txt");
    String myContent = "OUTPUT: This is my Data which needs to be written into the file";

    /*
     * first method by using File Output Stream
     * String content cannot be directly written into
     * a file. It needs to be converted into bytes
     */
    void firstMethod() {

        try {
            /* This logic will check whether the file
             * exists or not. If the file is not found
             * at the specified location it would create
             * a new file
             */
            if (file.exists()) {
                System.out.println("File exists");
            } else {
                System.out.println("File created");
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            byte[] bytesArray = myContent.getBytes();
            fos.write(bytesArray);
            fos.flush();
            fos.close();
            System.out.println("File Written Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *Second method by using FileWriter and append content to the file
     */
    void secondMethod() throws IOException {
        FileWriter fw = new FileWriter(file2);
        bw = new BufferedWriter(fw);
        bw.write(myContent);
        bw.close();
        System.out.println("File 2 written Successfully");
        //Here true is to append the content to file
        FileWriter fw2 = new FileWriter(file2, true);
        //BufferedWriter writer give better performance
        BufferedWriter bw2 = new BufferedWriter(fw2);
        bw2.write("\n" + " one more line...");
        //Closing BufferedWriter Stream
        bw2.close();
        System.out.println("Data successfully appended at the end of file");
    }

    /*
     * Method to append data to existing file
     */
    void appendFileDta() throws IOException {
        //Here 'true' is to append the content to file
        FileWriter fw1 = new FileWriter(file, true);
        BufferedWriter bw1 = new BufferedWriter(fw1);
        PrintWriter pw = new PrintWriter(bw1);
        //This will add a new line to the file content
        pw.println("\n");

        /* Below three statements would add three
         * mentioned Strings to the file in new lines.
         */
        pw.println("This is first line added");
        pw.println("This is the second line added");
        pw.println("This is third line added");
        pw.close();

    }
}

class FileRead{
    //Specify the path of the file here
    String base = System.getProperty("user.dir");
    File file = new File(base+"/src/test/resources/myFile.txt");
    BufferedInputStream bis;
    FileInputStream fis;
    BufferedReader br;

    /*
     *read the file by using BufferedInputStream ()
     */
    void readFirst() {
        try {
            //FileInputStream to read the file
            fis = new FileInputStream(file);

            /*Passed the FileInputStream to BufferedInputStream
             *For Fast read using the buffer array.
             */
            bis = new BufferedInputStream(fis);

            /*available() method of BufferedInputStream
             * returns 0 when there are no more bytes
             * present in the file to be read
             */
            while (bis.available() > 0) {
                System.out.print((char) bis.read());
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("The specified file not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }finally {
            System.out.println("File read completely.");
        }
    }

    /*
     *read the file by using Buffer Reader
     */
    void readBufferReader(){
        try {
            br = new BufferedReader(new FileReader(file));
            //One way of reading the file
            System.out.println("\nReading the file using readLine() method:");
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

    //read the file using Scanner() start from Java 1.5
    void readScanner() throws FileNotFoundException {
        System.out.println("Reading the file using Scanner:");
        Scanner scan = new Scanner(file);
        int line = 1;
        while (scan.hasNext()){
            System.out.println(line++ +": "+scan.nextLine());
        }
    }
}


//class for test serialization
class PersonSerialization implements Serializable{
    int age;
    String name;
    transient int id; //Static will not serialise as well
    PersonSerialization (int age, String name){
        this.age = age;
        this.name = name;
    }

    public String toString(){
        return "Person name: " + name + "; age: " + age + "; id: " + id;
    }
}

