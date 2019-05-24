package coreJava;

/**
 * to control object creation, limiting the number of objects to only ONE.
 * to read the .Properties file we have to call getReader() like:
 * SingletonClass.getInstance().getReader()
 */
public class SingletonClass {
    //private static variables - instances only this class.
    private static ConfigFileReader reader;
    private static SingletonClass fileReadManager = new SingletonClass();

    //private constructor to restrict instantiation from outer classes
    private SingletonClass(){}

    //method by calling we can instantiate this Singleton class
    public static SingletonClass getInstance(){
        return fileReadManager;
    }

    //method to read the properties, can be called only after instance of object
    public ConfigFileReader getReader(){
        return (reader == null) ? reader = new ConfigFileReader() : reader;
    }

}
