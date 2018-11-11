interface oneInterface{
    void method_A(int a);
    void method_B();
    void method_C(long b);
    void method_D();
}

abstract class oneAbstractClass implements oneInterface{ //abstract class does not implement all methods from interface
    private int z;
    oneAbstractClass(int z) {
        this.z = z;
    }

    public void method_B(){
        System.out.println("Method B"+z);
    }
    abstract void method_F();
}

class oneRealClass extends oneAbstractClass{
    oneRealClass(int z) {//must implement constructor if no default constructor in supper class
        super(z);
    }

    //must implement all abstract methods except 'method_B' was implemented in abstract class
    @Override
    public void method_F() {

    }

    @Override
    public void method_A(int a) {
        System.out.println(a);
    }

    @Override
    public void method_C(long b) {

    }

    @Override
    public void method_D() {

    }
}


public class abstractClassExample {
    public static void main(String [] args){
        oneInterface foo = new oneRealClass(7);
        foo.method_B();
        foo.method_A(6);
    }
}
