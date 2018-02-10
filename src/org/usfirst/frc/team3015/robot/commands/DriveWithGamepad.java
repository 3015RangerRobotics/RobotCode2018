package org.usfirst.frc.team3015.robot.commands;

/**
 * Driver control with gamepad
 */
public class DriveWithGamepad extends CommandBase {

    public DriveWithGamepad() {
    	requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	drive.arcadeDrive(oi.getLeftStickY(), oi.getRightStickX()/1.5, true);
//    	System.out.println(drive.leftEncoder.getRate());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    }

    protected void interrupted() {
    	end();
    }
}
