
package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoOppositeScaleOnly extends CommandGroup {

    public AutoOppositeScaleOnly(boolean isRightStart) {
    	addSequential(new GrabberClose());
//    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(.25, 14, 10, 60, false)));
//        addParallel(new ElevatorToScaleDelayed(3.75));
//        addSequential(new DriveMotionProfile("wallToRightScale", isRightStart));
    	addSequential(new DriveMotionProfile("wallToRightScale2"));
//        addSequential(new DriveMotionProfile("wallToLeftSwitch", isRightStart));
//    	addSequential(new DriveTurnToAngleEncoders((isRightStart) ? -88 : 88, true));
        addParallel(new ElevatorToScaleDelayed(4));
//    	addSequential(new DriveMotionProfile("leftSwitchToRightScale", isRightStart));
//        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? 45 : -45, true));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 14, 100, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? 90 : -90));
//        addSequential(new ObtainCube(4.75));
//        addSequential(new DriveTurnToAngleEncoders((isRightStart) ? -145 : 145));
//        addParallel(new ElevatorToScale());
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 14, 8, 100, false)));
//        addSequential(new GrabberCubeEject());
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 14, 100, true)));
//        addParallel(new ElevatorToBottom());
//        addSequential(new DriveTurnToAngleEncoders(180));
    }
}