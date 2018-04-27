package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSameScaleOnly extends CommandGroup {

    public AutoSameScaleOnly(boolean isRightStart) {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScaleDelayed(0.25));
        addSequential(new DriveMotionProfile("wallToLeftScale", isRightStart));
        addParallel(new GrabberCubeEject());
        
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 100, true)));
//        addParallel(new ElevatorToBottom());
//        addSequential(new DriveTurnToAngleEncoders(-50));
//        addParallel(new GrabberDown());
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 14, 100, true)));
//        addSequential(new DriveTurnToAngleEncoders(70));
        
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.5, 14, 14, 100, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? -91 : 91));
        addSequential(new ObtainCube(5));
        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? 125 : -125));
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 8, 100, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 14, 100, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(180));        
    }
}