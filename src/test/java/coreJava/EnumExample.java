package coreJava;

import org.testng.annotations.Test;

public class EnumExample {

    enum parameterINT{  //enum defined with parameters (can avoid it)
        PENNY(1),
        NICKLE(5),
        DIME(10),
        QUOTER(25);

        private int argument; //var for keeping value of argument

        parameterINT(int argument){ //constructor to initialize var argument by passing constant
            this.argument = argument;
        }

        public int getArgument(){ //method to return argument
            return argument;
        }
    }

    @Test
    void testEnum() {
        System.out.println("Parameter can be send as argument: "+parameterINT.PENNY.getArgument());
        parameterINT ar = parameterINT.valueOf("DIME");
        System.out.println("Value can be send as argument: "+ ar);
    }
}

