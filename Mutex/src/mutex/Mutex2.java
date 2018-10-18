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
            for (int i=0;;i++) {
                n = r.nextInt(3000 - 500 + 1) + 500;
                try {
                    Thread.sleep(n);
                    System.out.println("Solicitação >> \t" + name + " solicitação de recurso #"+i+ " \t" + java.time.LocalTime.now().toString());
                    semaphore.acquire();
                    try {
                        lista.add(name + "completando requisição #"+i +" "+ java.time.LocalDateTime.now().toString());
                        System.out.println("Execução >> \t"+name + " completando requisição #"+i +" \t"+ java.time.LocalTime.now().toString());
                        n = r.nextInt(3000 - 500 + 1) + 500;

                        Thread.sleep(n);

                    } catch (Exception e) {
                        System.out.println("  NEGADO");
                    } finally {
                        semaphore.release();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

//        System.out.println("Total available Semaphore permits: "
//                + semaphore.availablePermits());
        MyLockerThread t1 = new MyLockerThread("App1");
        t1.start();
        Thread.sleep(700);

        MyLockerThread t2 = new MyLockerThread("App2");
        t2.start();
        Thread.sleep(700);

        MyLockerThread t3 = new MyLockerThread("App3");
        t3.start();
        Thread.sleep(700);

        MyLockerThread t4 = new MyLockerThread("App4");
        t4.start();
        Thread.sleep(700);

        MyLockerThread t5 = new MyLockerThread("App5");
        t5.start();
        Thread.sleep(700);

        MyLockerThread t6 = new MyLockerThread("App6");
        t6.start();
        Thread.sleep(700);

    }
}
