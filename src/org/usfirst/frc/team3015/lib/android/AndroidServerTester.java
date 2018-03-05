package org.usfirst.frc.team3015.lib.android;

import org.usfirst.frc.team3015.lib.android.messages.TargetUpdateReceiver;
import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

public class AndroidServerTester {
    public static class TestReceiver implements TargetUpdateReceiver{
        @Override
        public void onUpdateReceived(TargetUpdate update) {
//            System.out.println("Number of targets: " + update.getTargets().size());
//            for(int i = 0; i < update.getTargets().size(); i++){
//                TargetInfo target = update.getTargets().get(i);
//                System.out.println("Target: " + target.getXAngle() + ", " + target.getYAngle() + ", " + target.getDistance());
//            }
        }
    }

    public static void main(String[] args){
    	
    	
    	double[][] ah = MotionProfiles.loadProfile("testPathLeft", false);
    	for(double[] no : ah) {
    		System.out.println(no[0] + ", " + no[1] + ", " + no[2]);
    	}
    	
//        AndroidServer server = AndroidServer.getInstance();
//        server.addTargetUpdateReceiver(new TestReceiver());
//        
//        try {
//        	Thread.sleep(1000);
//        }catch(Exception e) {
//        	e.printStackTrace();
//        }
//        
//        double[][] thing = server.generateMotion1D(10, 10, 5, 0.01);
//        for(int i =  0; i < thing.length; i++) {
//        	System.out.println(thing[i][0]);
//        }
//        while (true){
//            try{
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
    }
}
