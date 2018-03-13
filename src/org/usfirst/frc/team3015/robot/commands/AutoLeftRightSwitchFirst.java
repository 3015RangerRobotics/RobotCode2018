package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoLeftRightSwitchFirst extends CommandGroup {

    public AutoLeftRightSwitchFirst() {
    	addParallel(new ElevatorToSwitch());
    	addSequential(new DriveMotionProfile("wallToLeftSwitch"));
    	addSequential(new DriveTurnToAngleEncoders(59));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, false)));
    	addSequential(new GrabberCubeEject());
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
    	addSequential(new ObtainCube(1));
    	addSequential(new WaitCommand(.5));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
    	addParallel(new ElevatorToScaleDelayed(2));
    	addSequential(new DriveTurnToAngleEncoders(-56));
    	addSequential(new DriveMotionProfile("leftSwitchToRightScale"));
    	addSequential(new GrabberCubeEject());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
    	addSequential(new ElevatorToBottom());
    }
}
