package mutex;

class database {
}

class process extends Thread {

    static database d;
    int i;
    static semaphore mutex = new semaphore(1);
    static semaphore writing = new semaphore(1);
    static int readers = 0;

    public process(int i) {
        this.i = i;
    }

// The next method is a random number generator.
    private int random(int n) {
        return (int) Math.round(n * Math.random() - 0.5);
    }

// The next method is used to 'kill time' while reading or writing takes place.
    private void busy(int i, String s) {
        System.out.println(i + " is " + s);
        try {
            sleep(random(1000));
        } catch (InterruptedException e) {
        };
        System.out.println(i + s + " done");
    }

    public void run() {
        do {
            mutex.P();                              // Reading pre protocol
            readers++;                              //         |
            if (readers == 1) {
                writing.P();          //         |
            }
            mutex.V();                              //        \|/

            busy(i, " reading");                    // Reading

            mutex.P();                              // Reading post protocol
            readers--;                              //         |
            if (readers == 0) {
                writing.V();          //         |
            }
            mutex.V();                              //        \|/

            writing.P();                            // Writing pre protocol

            busy(i, " writing");                    // Writing

            writing.V();                            // Writing post protocol

        } while (true);
    }
}

class rw1 {
    static int N = 5;
    public static void main(String[] args) {

        process p[] = new process[N];

        for (int i = 0; i < N; i++) {
            p[i] = new process(i);
            p[i].start();
        }
    }

}
