package com.vamsi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Classical Example:: Producer Consumer Problem:: Designing a Blocking Queue
 */

public class BlockingQueue {

    private Queue<Integer> q;
    private int capacity;
    public BlockingQueue(int capacity)
    {
        q = new LinkedList<>();
        this.capacity = capacity;
    }

    public boolean add(int item)
    {

        synchronized (q) {
//            if (q.size() == capacity) // do u know why we put *****while(q.size() == capacity)**** instead of *****if (q.size() == capacity)****
            while (q.size() == capacity)
            {
                try {
                    q.wait();//adder1 , adder2

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            q.add(item);
            q.notifyAll();
            return true;
        }
    }
/**

    when adder1 comes ------ size=5 ----- Blocked= {adder1}
    Now,when adder2 comes ------- size=5 ---- Blocked = {adder1,adder2}

    when popper1 comes ---- size = 4 and notify all means adder1 and adder2

    so adder1 comes out of wait/block state and adds element and now size = 5;
    now adder2 comes out of wait/block state(because notifyAll notifies to all adders/threads) and tries add element
    but queue size is 5 already.....which means we have to check again where queue size == capacity or not(so we have to
    use while loop instead of if condition )..if there
    is one more adder3 in block state and when its unblock and we have to check queue size = capacity or not
*/
    public int remove(){
        synchronized (q)
        {
//            if(q.size() == 0)
            while (q.size() == 0)
            {
                try {
                    q.wait();
                    /**
                     * In beginning both adder thread and removal thread tries access this class  and removal thread won bid.
                     * so removal thread gets lock and went inside remove function but queue size is zero...it doesn't have
                     * anything to remove...And even adder threads trying from outside to get lock and it can't get it beacuse
                     * removal thread have that lock...so to handle this case the removal thread goes to waiting state by giving
                     * up the lock...so that other threads(adders threads can add elements to queue)
                     *
                     * Note:
                     * Since every class is subclass of Object class in java.And Object already implements this wait() and notifyAll()
                     * already...we can use those methods using queue object(here q)
                     */
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            int element = q.poll();
            q.notifyAll();
            return element;
        }
    }
}
