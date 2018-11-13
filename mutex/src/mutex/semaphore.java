/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutex;

/**
 *
 * @author Daniel
 */
// CS237 Concurrent Programming
// ===== ========== ===========
// A simplified version of the semaphore implementation from 
// Stephen Hartley's book.
class semaphore {

    protected int value = 0;

    protected semaphore() {
        value = 0;
    }

    protected semaphore(int initial) {
        value = initial;
    }

    public synchronized void P() {
        value--;
        if (value < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void V() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}
