package org.usfirst.frc.team3015.lib.util;

public abstract class Threaded implements Runnable{

    @Override
    public void run() {
        try{
            runThreaded();
        }catch(Throwable t){
            System.err.println(t.getStackTrace());
        }
    }

    public abstract void runThreaded();
}
