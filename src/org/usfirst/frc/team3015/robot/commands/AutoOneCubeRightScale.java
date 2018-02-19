package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeRightScale extends CommandGroup {

    public AutoOneCubeRightScale() {
    	addSequential(new GrabberClose());
    	addParallel(new ElevatorToScale());
    	addSequential(new DriveMotionProfile("wallToRightScale"));
    	addSequential(new GrabberOpen());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(-1, -10, -5)));
    	addSequential(new ElevatorToBottom());
    }
}
