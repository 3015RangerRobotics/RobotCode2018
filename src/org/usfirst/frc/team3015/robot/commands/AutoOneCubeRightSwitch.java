package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeRightSwitch extends CommandGroup {

    public AutoOneCubeRightSwitch() {
    	addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("wallToRightSwitch"));
        addSequential(new GrabberEject());
    }
}
