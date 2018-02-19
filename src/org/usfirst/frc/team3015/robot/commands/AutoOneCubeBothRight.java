package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeBothRight extends CommandGroup {

    public AutoOneCubeBothRight() {
    	addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToRightScale"));
        addSequential(new GrabberEject());
        addParallel(new ElevatorToBottom());
    	addSequential(new DriveTurnToAngle(175));
    	addSequential(new DriveToCube(.5));
    	addSequential(new ElevatorToSwitch());
    	addSequential(new GrabberEject());
    }
}
