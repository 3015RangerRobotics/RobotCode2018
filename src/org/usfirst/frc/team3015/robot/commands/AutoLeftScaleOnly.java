package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftScaleOnly extends CommandGroup {

    public AutoLeftScaleOnly() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScaleDelayed(0.25));
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.5, 14, 10, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(99));
        addSequential(new ObtainCube(5.5));
        addParallel(new ElevatorToScale());
        addSequential(new DriveTurnToAngleEncoders(-140));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 12, 18, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}