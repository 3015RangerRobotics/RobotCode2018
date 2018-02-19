package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LEFT SWITCH RIGHT SCALE
 */
public class AutoOneCubeBothLeftToRight extends CommandGroup {

    public AutoOneCubeBothLeftToRight() {
    	addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("wallToLeftSwitch"));
        addSequential(new GrabberEject());
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("leftSwitchToRightScale"));
        addSequential(new GrabberEject());
        addSequential(new ElevatorToBottom());
    }
}
