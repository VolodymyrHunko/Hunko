package ProtoBuffExample;

import generatedGoogleFormatMessages.AddressBookProtosUpdated.AddressBook;
import generatedGoogleFormatMessages.AddressBookProtosUpdated.Person;
import generatedGoogleFormatMessages.AddressBookProtosUpdated.Email;

import java.io.FileInputStream;
import java.util.Scanner;

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
        printPersonInfo(addressBook);
    }

    /**
     * iterate through all records in address book and print all info
     *
     * @param addressBook instance of adressBook class
     */
    private static  void printBoook(AddressBook addressBook){
        for(Person somePerson : addressBook.getEmployeesList()){
            System.out.format("%-20s:%s","Person ID",somePerson.getId()+"\n");
            System.out.format("%-20s:%s","First Name",somePerson.getFirstName()+"\n");
            System.out.format("%-20s:%s","Last name",somePerson.getLasName()+"\n");
            //loop all emails
            for(Email oneEmail : somePerson.getEmailList()){
                String correctType = String.valueOf(oneEmail.getEmailType());
                switch (correctType.toUpperCase()){
                    case "PERSONAL":
                        System.out.format("%-20s:","Personal e-Mail");
                        break;
                    case "WORK":
                        System.out.format("%-20s:","Work e-Mail: ");
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
                        System.out.format("%-20s:","Mobile #");
                        break;
                    case "HOME":
                        System.out.format("%-20s:","Home #");
                        break;
                    case "WORK":
                        System.out.format("%-20s:","Work #");
                        break;
                    default:
                        System.out.println("No phone #");
                        break;
                }
                System.out.println(oneNumber.getPhoneNumber());
            }
            if(somePerson.hasLastId()) {
                System.out.format("%-20s:%s", "Order of input", somePerson.getLastId() + "\n");
            }else{
                System.out.format("%-20s:%s", "Order of input","Order was not assigned!\n");
            }
            System.out.println("\n");
        }

    }

    /**
     * print selected fields person's info
     */
    private static void printPersonInfo (AddressBook addressBook){
        int count = addressBook.getEmployeesList().size();
        int i = 0; //iterator
        System.out.println("What user's SNN would you like to get info?");
        Scanner scan = new Scanner(System.in);
        int input = Integer.parseInt(scan.nextLine());

        for (Person somePerson : addressBook.getEmployeesList()) {
            if (somePerson.getId() == input) {
                System.out.println("What info about this user you want to see?" +
                        "\nID - '1', First Name - '2', Last Name - '3', eMails - '4', phone numbers - '5', entered order - '6'.");
                int info = Integer.parseInt(scan.nextLine());
                switch (info) {
                    case 1:
                        System.out.format("%-20s:%s", "Person ID", somePerson.getId() + "\n");
                        break;
                    case 2:
                        System.out.format("%-20s:%s", "First Name", somePerson.getFirstName() + "\n");
                        break;
                    case 3:
                        System.out.format("%-20s:%s", "Last name", somePerson.getLasName() + "\n");
                        break;
                    case 4:
                        for (Email oneEmail : somePerson.getEmailList()) {
                            String correctType = String.valueOf(oneEmail.getEmailType());
                            switch (correctType.toUpperCase()) {
                                case "PERSONAL":
                                    System.out.format("%-20s:", "Personal e-Mail");
                                    break;
                                case "WORK":
                                    System.out.format("%-20s:", "Work e-Mail: ");
                                    break;
                                default:
                                    System.out.print("No e-Mail ");
                            }
                            System.out.println(oneEmail.getEmails());
                        }
                        break;
                    case 5:
                        for (Person.PhoneNumber oneNumber : somePerson.getPhonesList()) {
                            String correctPhoneType = String.valueOf(oneNumber.getPhoneType());
                            switch (correctPhoneType.toUpperCase()) {
                                case "MOBILE":
                                    System.out.format("%-20s:", "Mobile #");
                                    break;
                                case "HOME":
                                    System.out.format("%-20s:", "Home #");
                                    break;
                                case "WORK":
                                    System.out.format("%-20s:", "Work #");
                                    break;
                                default:
                                    System.out.println("No phone #");
                                    break;
                            }
                            System.out.println(oneNumber.getPhoneNumber());
                        }
                    case 6:
                        if (somePerson.hasLastId()) {
                            System.out.format("%-20s:%s", "Order of input", somePerson.getLastId() + "\n");
                        } else {
                            System.out.format("%-20s:%s", "Order of input", "Order was not assigned!\n");
                        }
                        break;
                    default:
                        System.out.println("Wrong input.");
                        break;
                }
                break;
            }
            i++;
            if(i == count){
                System.out.print("No user found with ID:"+input);
            }
        }
    }
}
