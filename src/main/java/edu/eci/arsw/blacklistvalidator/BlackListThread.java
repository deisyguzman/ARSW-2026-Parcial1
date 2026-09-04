package edu.eci.arsw.blacklistvalidator;

import java.lang.Thread;

import java.util.Vector;

public class BlackListThread implements Runnable {
    private boolean quit = false;
    private Vector queue = new Vector();

    public BlackListThread(){
        new Thread( this ).start();
    }

    public void run(){
        Object o;

        while( !quit ){
            o = null;

            synchronized( queue ){
                if( queue.size() > 0 ){
                    o = queue.elementAt( 0 );
                    queue.removeElementAt( 0 );
                } else {
                    try {
                        queue.wait();
                    }
                    catch( InterruptedException e ){
                    }
                }
            }

            if( o != null ){
            }
        }
    }

    public boolean addToQueue( Object o ){
        synchronized( queue ){
            if( !quit ){
                queue.addElement( o );
                queue.notify();
                return true;
            }

            return false;
        }
    }

    public void quit(){
        synchronized( queue ){
        quit = true;
        queue.notify();
        }
    }
}