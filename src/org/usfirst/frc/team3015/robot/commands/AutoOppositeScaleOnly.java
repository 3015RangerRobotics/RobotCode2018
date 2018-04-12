
package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoOppositeScaleOnly extends CommandGroup {

    public AutoOppositeScaleOnly(boolean isRightStart) {
    	addSequential(new GrabberClose());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.5, 14, 10, 60, false)));
        addParallel(new ElevatorToScaleDelayed(3.75));
        addSequential(new DriveMotionProfile("wallToRightScale", isRightStart));
        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? 45 : -45, true));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 10, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? 115 : -115));
        addSequential(new ObtainCube(5));
        addParallel(new ElevatorToScale());
        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? -155 : 155));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}