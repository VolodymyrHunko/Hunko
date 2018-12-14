package ProtoBuffExample;

import generatedGoogleFormatMessages.AddressBookProtos.AddressBook;
import generatedGoogleFormatMessages.AddressBookProtos.Person;
import generatedGoogleFormatMessages.AddressBookProtos.Email;

import java.io.FileInputStream;

public class employeesOutput {

    /**
     * main class - reads and prints entire address book
     */
    public static void main(String[] args) throws Exception{
        //in case of launching from command line
//        if(args.length != 1){
//            System.err.println("Usage: employeesOutput ADDRESS_BOOK_FILE");
//            System.exit(-1);
//        }

        //read the file if it is exists
        AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(
                "/home/volodymyr/IdeaProjects/Hunko/src/test/resources/Address_Book_File.txt"));//args[0]));
        //print info
        printBoook(addressBook);
    }

    /**
     * iterate through all records in address book and print all info
     *
     * @param addressBook instance of adressBook class
     */
    static  void printBoook(AddressBook addressBook){
        for(Person somePerson : addressBook.getEmployeesList()){
            System.out.println("Person ID: "+ somePerson.getId());
            System.out.println("First Name: "+somePerson.getFirstName());
            System.out.println("Last name: " + somePerson.getLasName());
            //loop all emails
            for(Email oneEmail : somePerson.getEmailList()){
                String correctType = String.valueOf(oneEmail.getEmailType());
                switch (correctType.toUpperCase()){
                    case "PERSONAL":
                        System.out.print("Personal e-Mail: ");
                        break;
                    case "WORK":
                        System.out.print("Work e-Mail: ");
                        break;
                    default:
                        System.out.print("No e-Mail ");
                }
                System.out.println(oneEmail.getEmails());
            }
            //loop all phones
            for(Person.PhoneNumber oneNumber : somePerson.getPhonesList()){
                String correctPhoneType = String.valueOf(oneNumber.getPhoneType());
                switch (correctPhoneType.toUpperCase()){
                    case "MOBILE" :
                        System.out.print("Mobile #: ");
                        break;
                    case "HOME":
                        System.out.print("Home #: ");
                        break;
                    case "WORK":
                        System.out.print("Work #: ");
                        break;
                    default:
                        System.out.println("No phone #");
                        break;
                }
                System.out.println(oneNumber.getPhoneNumber());
            }

        }

    }
}
