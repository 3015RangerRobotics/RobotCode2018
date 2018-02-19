package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeBothLeft extends CommandGroup {

    public AutoOneCubeBothLeft() {
    	addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addParallel(new ElevatorToBottom());
    	addSequential(new DriveTurnToAngle(175));
    	addSequential(new DriveToCube(.5));
    	addSequential(new ElevatorToSwitch());
    	addSequential(new GrabberEject());
    }
}
