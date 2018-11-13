package mutex;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
class semaphore2 {

  private int value ;

  public semaphore2(int i) {
    value = i ;
  }

  synchronized void P() {
    while ( value <= 0 )
       try { wait() ; } catch (InterruptedException e) { } 
    value-- ;
  }

  synchronized void V() {
    value++ ;
    notify() ;
  }
}
