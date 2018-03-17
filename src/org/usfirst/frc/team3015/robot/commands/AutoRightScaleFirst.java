
package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightScaleFirst extends CommandGroup {

    public AutoRightScaleFirst() {
    	addSequential(new GrabberClose());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 14, 10, 60, false)));
        addParallel(new ElevatorToScaleDelayed(3.75));
        addSequential(new DriveMotionProfile("wallToRightScale"));
        addSequential(new DriveTurnToAngleEncoders(-15));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 10, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(-127));
        addSequential(new ObtainCube(5));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}