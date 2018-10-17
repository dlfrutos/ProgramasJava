package mutex;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mutex2 {

    /**
     * referencia
     * https://www.mkyong.com/java/java-thread-mutex-and-semaphore-example/
     */
    // max 1 people
    static Semaphore semaphore = new Semaphore(1);
    static ArrayList<String> lista = new ArrayList<>();

    static class MyLockerThread extends Thread {

        String name = "";

        MyLockerThread(String name) {
            this.name = name;
        }

        public void run() {
            Random r = new Random();
            int n;
            for (;;) {
                n=r.nextInt(2000 - 500 + 1) +500;
                try {
                    System.out.println(name + " : aguardando recurso...");
                    // System.out.println(name + " : available Semaphore permits now: "
                    // + semaphore.availablePermits());

                    semaphore.acquire();
                    //System.out.println(name + " : got the permit!");

                    try {
                        //System.out.println(name + " : está utilizando o recurso.");
                        lista.add(name + "usando recurso" + java.time.LocalDateTime.now().toString());
                        System.out.println(name + " usando recurso em " + java.time.LocalDateTime.now().toString());
                        // sleep 1 second
                        Thread.sleep(n);

                    } finally {
                        // calling release() after a successful acquire()
                        // System.out.println(name + " : releasing lock...");
                        semaphore.release();
                        //System.out.println(name + " : available Semaphore permits now: "
                        //      + semaphore.availablePermits());
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Total available Semaphore permits : "
                + semaphore.availablePermits());

        MyLockerThread t1 = new MyLockerThread("App1");
        t1.start();

        MyLockerThread t2 = new MyLockerThread("App2");
        t2.start();

        MyLockerThread t3 = new MyLockerThread("App3");
        t3.start();

        MyLockerThread t4 = new MyLockerThread("App4");
        t4.start();

        MyLockerThread t5 = new MyLockerThread("App5");
        t5.start();

        MyLockerThread t6 = new MyLockerThread("App6");
        t6.start();

    }
}
