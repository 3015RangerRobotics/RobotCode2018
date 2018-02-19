package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeInBothLeft extends CommandGroup {

    public AutoOneCubeInBothLeft() {
        addSequential(new DriveMotionProfile("wallToLeftScale"));
    	addParallel(new ElevatorToScale());
    	addSequential(new DriveTurnToAngle(175));
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveToCube(.5));
    	addSequential(new ElevatorToSwitch());
    	addSequential(new GrabberEject());
    }
}
