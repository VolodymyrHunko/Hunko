package coreJava;

class PrintDemo {
    void printCount(String threadName) {
        try {
            for(int i = 5; i > 0; i--) {
                System.out.println(threadName+"Counter   ---   "  + i );
                Thread.sleep(500); //when use .sleep - have to handle Exception
            }
        } catch (InterruptedException e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    private final PrintDemo  PD;

    ThreadDemo( String name,  PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        synchronized(PD) {
            PD.printCount(threadName);
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            System.out.println("Name of Thread: "+t);
            t.start ();
        }
    }
}

public class TestThreadSinc {

    public static void main(String args[]) {
        PrintDemo PD = new PrintDemo();

        ThreadDemo T1 = new ThreadDemo( "Thread_S1 ", PD );
        ThreadDemo T2 = new ThreadDemo( "Thread_S2 ", PD );
        ThreadDemo T3 = new ThreadDemo( "Thread_S3 ", PD );
        System.out.println("Go...1");
        T1.start();
        System.out.println("Go...2");
        T2.start();
        System.out.println("Go...3");
        T3.start();

        // wait for threads to end
        try {
            System.out.println("Join...1");
            T1.join();
            System.out.println("Join...2");
            T2.join();
            System.out.println("Join...3");
            T3.join();
        } catch ( Exception e) { //.join has to handle an exception
            System.out.println("Interrupted");
        }
    }
}