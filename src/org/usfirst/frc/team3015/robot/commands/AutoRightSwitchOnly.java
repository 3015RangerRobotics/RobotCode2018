package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitchOnly extends CommandGroup {

    public AutoRightSwitchOnly() {
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(19, 14, 10, 60, false)));
    	addSequential(new DriveTurnToAngle(90, false));
    	addParallel(new ElevatorToSwitch());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(17, 14, 10, 60, false)));
    	addSequential(new DriveTurnToAngle(135, false));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 10, 60, false)));
    	addSequential(new GrabberCubeEject());
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
    	addSequential(new ObtainCube(2));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
    	addSequential(new ElevatorToBottom());
    }
}
