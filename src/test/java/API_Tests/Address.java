package API_Tests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jetbrains.annotations.Contract;

/**
 * class to demonstrate the serialization with JACKSON
 * all fields MUST be public or getter/setter must be public
 */
public class Address {
    public String FirstName;
    public String LastName;
    public String UserName;
    public String Password;
    public String Email;
    @Contract(pure = true)
    public Address(String first, String last, String user, String pass, String mail){
        FirstName = first;
        LastName = last;
        UserName = user;
        Password = pass;
        Email = mail;
    }
}


