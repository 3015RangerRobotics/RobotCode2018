package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeInBothRight extends CommandGroup {

    public AutoOneCubeInBothRight() {
        addSequential(new DriveMotionProfile("wallToRightScale"));
    	addParallel(new ElevatorToScale());
    	addSequential(new DriveTurnToAngle(175));
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveToCube(.5));
    	addSequential(new ElevatorToSwitch());
    	addSequential(new GrabberEject());
    }
}
