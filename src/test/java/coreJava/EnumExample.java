package coreJava;

import org.testng.annotations.Test;

public class EnumExample {

    enum parameterINT{  //enum defined
        FIRST(10),
        SECOND(1),
        THERD(44),
        FORTH(999);

        int argument; //var for keeping value of argument

        parameterINT(int argument){ //constructor to initialize var argument by passing constant
            this.argument = argument;
        }

        int getArgument(){ //method to return argument
            return argument;
        }
    }

    @Test
    public void testEnum() {
        EnumExample.parameterINT param;
        System.out.println("Parameter can be send as argument: "+parameterINT.FIRST.getArgument());
        parameterINT ar = parameterINT.valueOf("SECOND");
        System.out.println("Value can be send as argument: "+ ar);
    }
}

