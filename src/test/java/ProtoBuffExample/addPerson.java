package ProtoBuffExample;

import generatedGoogleFormatMessages.AddressBookProtos;
import generatedGoogleFormatMessages.AddressBookProtos.AddressBook;
import generatedGoogleFormatMessages.AddressBookProtos.Person;
import generatedGoogleFormatMessages.AddressBookProtos.Email;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class addPerson {

    /**
     * Main class - reads the entire address book from stored file
     * adds one instance of new person
     * saves a new record in the same file
     */
    public static  void main(String[] args) throws Exception{
        //in case if app launch from command line, pass the path to file as argument
//        if(args.length != 1){
//            System.err.println("Usage: AddPerson ADDRESS_BOOK_FILE, args=> "+ Arrays.toString(args));
//            System.exit(-1);
//        }

        //create an instance if AddressBook class
        AddressBook.Builder myAddressBook = AddressBook.newBuilder();

        //read the existing file
        try{
            myAddressBook.mergeFrom(new FileInputStream("/home/volodymyr/IdeaProjects/Hunko/src/test/resources/Address_Book_File.txt"));//args[0]));
        }catch (FileNotFoundException e){
            System.out.println(args[0] + " =>  No file exists, creating a new one...");
        }

        //add a new address
        myAddressBook.addEmployees(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),System.out));

        //write the updated object to file
        FileOutputStream output = new FileOutputStream("/home/volodymyr/IdeaProjects/Hunko/src/test/resources/Address_Book_File.txt");//args[0]));
        myAddressBook.build().writeTo(output);
        output.close();
    }

    /**
     * static class to fill up the Person message based on user input
     *
     * @param input read the user's input
     * @param output System.out.println
     * @return instance of Person class
     * @throws IOException check IO
     */
   static Person PromptForAddress(BufferedReader input, PrintStream output) throws IOException{
       //create a new instance of Person class
       Person.Builder nextPerson = Person.newBuilder();
       System.out.println("Enter Person's SSN:");
       nextPerson.setId(Integer.parseInt(input.readLine()));
       System.out.println("Enter First Name:");
       nextPerson.setFirstName(input.readLine());
       System.out.println("Enter Last name:");
       nextPerson.setLasName(input.readLine());
       //loop to enter all emails
       while (true){
           System.out.println("Enter email, or leave blank and hit Enter to finish:");
           String newEmail = input.readLine();
           if(newEmail.length() == 0){
               break;
           }
           //create a new instance of email class
           Email.Builder nextEmail = Email.newBuilder();
           nextEmail.setEmails(newEmail);
           System.out.println("Enter type as 'PERSONAL', 'WORK', or leave blank (by default it is 'PERSONAL')");
           String emailType = input.readLine();
           switch (emailType.toUpperCase()){
               case "WORK":
                   nextEmail.setEmailType(AddressBookProtos.EmailType.WORK);
                   break;
               default:
                   nextEmail.setEmailType(AddressBookProtos.EmailType.PERSONAL);
                   break;
           }
           nextPerson.addEmail(nextEmail);
       }
       //loop to enter all phones
       while (true){
           System.out.println("Enter phone number, or leave blank and hit Enter to finish:");
           String newPhone = input.readLine();
           if(newPhone.length() == 0){
               break;
           }
           //create a new instance of phone class
           Person.PhoneNumber.Builder nextPhone = Person.PhoneNumber.newBuilder();
           nextPhone.setPhoneNumber(newPhone);
           System.out.println("Enter type as 'MOBILE', 'WORK', 'HOME' or leave blank (by default it is 'MOBILE')");
           String phoneType = input.readLine();
           switch (phoneType.toUpperCase()){
               case "WORK":
                   nextPhone.setPhoneType(Person.PhoneNumber.PhoneType.WORK);
                   break;
               case "HOME":
                   nextPhone.setPhoneType(Person.PhoneNumber.PhoneType.HOME);
                   break;
               default:
                   nextPhone.setPhoneType(Person.PhoneNumber.PhoneType.MOBILE);
                   break;
           }
           nextPerson.addPhones(nextPhone);
       }
        // build a new object
       return nextPerson.build();
   }
}
