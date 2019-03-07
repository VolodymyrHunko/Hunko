package coreJava;

import org.testng.annotations.Test;

public class EnumExample {
    //enum defined with parameters (can avoid it).................................................
    enum parameterINT {
        PENNY(1),
        NICKLE(5),
        DIME(10),
        QUOTER(25);

        private int argument; //var for keeping value of argument

        parameterINT(int argument) { //constructor to initialize var argument by passing constant
            this.argument = argument;
        }

        public int getArgument() { //method to return argument
            return argument;
        }
    }

    @Test
    void testEnum() {
        System.out.println("Parameter can be send as argument: " + parameterINT.PENNY.getArgument()); // 1
        parameterINT ar = parameterINT.valueOf("DIME");
        System.out.println("Value can be send as argument: " + ar); //DIME
    }


    //example to pass enum to class.............................................................
    enum rank {
        JACK,
        QUIN,
        KING,
        ASS;
    }

    enum suit {
        DIAMOND,
        CLUB,
        HARD,
        SPADE;
    }

    private class Cards {
        private suit mySuit;
        private rank myRank;

        private Cards(rank r, suit s) {
            this.mySuit = s;
            this.myRank = r;
        }
    }

    @Test
    void testCard() {
        assert (rank.JACK == rank.valueOf("JACK"));
        assert (suit.CLUB == suit.valueOf("CLUB"));
        Cards myCard = new Cards(rank.ASS, suit.HARD);
        System.out.println("My card: " + myCard.myRank + ":" + myCard.mySuit);
        parameterINT ar = EnumExample.parameterINT.valueOf("DIME");
        System.out.println("Value can be send as argument: " + ar); //DIME
    }

}

