package coreJava;

import org.testng.annotations.Test;

public class BinaryManipulation {
    public void convertInt(int i){
        String binary = Integer.toString(i, 2);
        String octal = Integer.toString(i, 8);
        String hex = Integer.toString(i, 16);
        System.out.println("Int = "+i+"\nconverted to binary: "+binary+
                "\nconverted to octal: "+octal+
                "\nconverted to hex: "+hex);
        System.out.println("Binary "+binary+" " +
                "corresponded to int = "+Integer.parseInt(binary, 2));
    }

    @Test
    public void getConverted(){
        convertInt(11);
        convertInt(21);
    }

    @Test
    public void bitwise_AND (){
        //return 1 if both bits are '1', else it return 0
        System.out.printf("11 & 21   = %8d",11 & 21);
        System.out.printf("\nBinary 11 = %8s",Integer.toBinaryString(11));
        System.out.printf("\nBinary 21 = %8s",Integer.toBinaryString(21));
        System.out.printf("\nBinary  1 = %8s",Integer.toBinaryString(11 & 21));
    }

    @Test
    public void bitwise_OR_inclusive (){
        //return 1 if either bit is '1', else it return 0
        System.out.printf("11 | 21   = %8d",11 | 21);
        System.out.printf("\nBinary 11 = %8s",Integer.toBinaryString(11));
        System.out.printf("\nBinary 21 = %8s",Integer.toBinaryString(21));
        System.out.printf("\nBinary 31 = %8s",Integer.toBinaryString(11 | 21));
    }

    @Test
    public void bitwise_OR_exclusive (){
        //return 0 if both bit is '1', else it return 1
        System.out.printf("11 ^ 21   = %8d",11 ^ 21);
        System.out.printf("\nBinary 11 = %8s",Integer.toBinaryString(11));
        System.out.printf("\nBinary 21 = %8s",Integer.toBinaryString(21));
        System.out.printf("\nBinary 30 = %8s",Integer.toBinaryString(11 ^ 21));
    }

    @Test
    public void bitwise_complement (){
        //invert all bits
        System.out.printf(" ~21     = %8d",~ 21);
        System.out.printf("\nBinary 21 = %8s",Integer.toBinaryString(21));
        System.out.printf("\nBinary -22 = %8s",Integer.toBinaryString(~21));
    }

    @Test
    public void bitwise_shift (){
        //shift bits left
        System.out.printf("Binary 21 = %8s",Integer.toBinaryString(21));
        System.out.printf("\n21 << 2   = %8s",Integer.toBinaryString(21 << 2));
        System.out.println("\nShifted 21 << 2 corresponded to int = "+
                Integer.parseInt(Integer.toBinaryString(21 << 2), 2));

        //shifted right
        System.out.printf("\n21 >> 2   = %8s",Integer.toBinaryString(21 >> 2));
        System.out.println("\nShifted 21 >> 2 corresponded to int = "+
                Integer.parseInt(Integer.toBinaryString(21 >> 2), 2));
    }

}
