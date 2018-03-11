package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ZAutoRightSwitchOnly extends CommandGroup {

    public ZAutoRightSwitchOnly() {
//    	addParallel(new ElevatorToScaleDelayed(3));
    	addSequential(new DriveMotionProfile("wallToRightSwitch", false));
//    	addSequential(new DriveTurnToAngle(65, false));
//    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 12, 8, 60, false)));
//    	addSequential(new GrabberCubeEject());
//    	addParallel(new ElevatorToBottom());
//    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 12, 8, 60, true)));
//    	addParallel(new ObtainCubeToSwitch());
//    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 12, 8, 60, false)));
//    	addSequential(new WaitCommand(3));
//    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 12, 8, 60, false)));    	
//    	addSequential(new GrabberCubeEject());
//    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 12, 8, 60, true)));
//    	addSequential(new ElevatorToBottom());
    }
}
