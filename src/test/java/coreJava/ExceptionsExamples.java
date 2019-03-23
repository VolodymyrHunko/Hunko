package coreJava;

import java.io.IOException;

//superclass 1 with low level of Exceptions
class ExceptionsExamples {
    void msg()throws ArithmeticException{
        System.out.println("parent 1");}
}

//superclass 2 with high level of exceptions
class ExceptionsExamples2 {
    void msg()throws Exception{
        System.out.println("parent 2");}
}

//superclass 3 with no exceptions
class ExceptionsExamples3 {
    void msg(){
        System.out.println("parent 3");}
}

class childClass extends ExceptionsExamples{
    // CAN declare the same exception as super class
    void msg()throws ArithmeticException{
        System.out.println("child 1");
    }
}

class childClass2 extends ExceptionsExamples{
    //CAN not declare exception (use the super exception)
    void msg(){
        System.out.println("child 2");
    }
}

class childClass3 extends ExceptionsExamples{
    // can NOT throws hirer level of Exceptions than super class
//    void msg()throws Exception{
//        System.out.println("child 3");
//    }
}

class childClass4 extends  ExceptionsExamples2{
    //CAN throw a less level of exception
    void msg() throws IOException {
        System.out.println("child 4");
    }
}

class childClass5 extends ExceptionsExamples3{
    // can NOT throws checked exception
//    void msg() throws IOException {
//        System.out.println("child 5");
//    }
    // but can throws unchecked exception
    void msg()throws ArithmeticException{
        System.out.println("child 5");
    }
}