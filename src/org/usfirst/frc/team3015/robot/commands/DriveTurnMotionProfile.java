package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * Follows a motion profile
 */
public class DriveTurnMotionProfile extends CommandBase {
	private volatile boolean isFinished = false;
	private double[][] motion;
	private volatile int i =  0;
	private volatile double prevError = 0;

    public DriveTurnMotionProfile(double[][] motionProfile) {
    	requires(drive);
    	this.motion = motionProfile;
    }

    protected void initialize() {
    	drive.resetGyro();
    	Timer.delay(.02);
    	isFinished = false;
    	i = 0;
    	prevError = 0;
    	
    	new Thread(() -> {
    		double lastTime = 0;
    		
    		while(!isFinished && Robot.isEnabled) {
    			if(Timer.getFPGATimestamp() >= lastTime + 0.01) {
    				lastTime = Timer.getFPGATimestamp();
    				threadedExecute();
    			}
    			try {
    				Thread.sleep(2);
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}).start();
    }
    
    protected synchronized void threadedExecute() {
    	if(i < motion.length) {
			double goalPos = motion[i][0];
			double goalVel = motion[i][1];
			double goalAcc = motion[i][2];
			
			double error = goalPos - drive.getAngle();
			double errorDeriv = ((error - prevError) / Constants.kPeriod) - goalVel;
			
			double pwm = (drive.kTurnP * error) + (drive.kTurnI * errorDeriv) + (drive.kTurnV * goalVel) + (drive.kTurnA * goalAcc);

			System.out.println(goalPos + ", " + drive.getAngle());
			
			prevError = error;
			
			drive.arcadeDrive(0, pwm, false);
			i++;
		}else {
			isFinished = true;
		}
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    }

    protected void interrupted() {
    	isFinished = true;
    	end();
    }
}
