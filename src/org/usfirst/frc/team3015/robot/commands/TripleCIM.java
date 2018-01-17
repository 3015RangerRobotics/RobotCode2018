package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.Prototype3CIM;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TripleCIM extends CommandBase {

    public TripleCIM() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	requires(prototype3cim);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	prototype3cim.driveSpeed(oi.getRightStick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
