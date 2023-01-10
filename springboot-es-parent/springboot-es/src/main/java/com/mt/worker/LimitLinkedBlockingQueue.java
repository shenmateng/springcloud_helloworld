package com.mt.worker;

import java.util.concurrent.LinkedBlockingQueue;


public class LimitLinkedBlockingQueue<E> extends LinkedBlockingQueue<E> {

    /**
     * 
     */
    private static final long serialVersionUID = -2174562587048362507L;

    public LimitLinkedBlockingQueue(int capacity) {
        super(capacity);
    }
    
    @Override
    public boolean offer(E e) {
        // turn offer() and add() into a blocking calls (unless interrupted)
        try {
            put(e);
            return true;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return false;
    }
}
