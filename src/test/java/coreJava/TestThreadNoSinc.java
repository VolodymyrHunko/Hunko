package coreJava;

class PrintDemo2 {
    void printCount2(String threadName) {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(threadName+"Counter   ---   "  + i );
                Thread.sleep(500);
            }
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class ThreadDemo2 extends Thread {
    private Thread t;
    private String threadName;
    private PrintDemo2  PD;

    ThreadDemo2( String name,  PrintDemo2 pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        PD.printCount2(threadName);
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

public class TestThreadNoSinc {
    public static void main(String args[]) {

        PrintDemo2 PD = new PrintDemo2();

        ThreadDemo2 T1 = new ThreadDemo2( "Thread - 1 ", PD );
        ThreadDemo2 T2 = new ThreadDemo2( "Thread - 2 ", PD );

        T1.start();
        T2.start();
    }
}