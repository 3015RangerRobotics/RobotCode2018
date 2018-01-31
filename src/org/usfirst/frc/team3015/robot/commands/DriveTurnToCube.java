package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns to an angle using the imu
 */
public class DriveTurnToCube extends CommandBase {

    public DriveTurnToCube() {
        requires(drive);   
    }

    protected void initialize() {
    	Command command = new DriveTurnToAngle(drive.bestTarget.getXAngle());
    	command.start();	
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
