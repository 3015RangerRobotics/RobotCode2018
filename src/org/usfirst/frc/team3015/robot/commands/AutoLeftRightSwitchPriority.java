package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoLeftRightSwitchPriority extends CommandGroup {

    public AutoLeftRightSwitchPriority() {
    	addParallel(new ElevatorToScaleDelayed(3));
    	addSequential(new DriveMotionProfile("wallToLeftSwitch", false));
    	addSequential(new DriveTurnToAngle(65, false));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 14, 10, 60, false)));
    	addSequential(new GrabberCubeEject());
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 14, 10, 60, true)));
    	addParallel(new ObtainCube());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 14, 10, 60, false)));
    	addSequential(new WaitCommand(1));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 14, 10, 60, true)));
    	addParallel(new ElevatorToScaleDelayed(2));
    	addSequential(new DriveTurnToAngle(65, true));
    	addSequential(new DriveMotionProfile("leftSwitchToRightScale", false));
    	addSequential(new GrabberCubeEject());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
    	addSequential(new ElevatorToBottom());
    }
}
