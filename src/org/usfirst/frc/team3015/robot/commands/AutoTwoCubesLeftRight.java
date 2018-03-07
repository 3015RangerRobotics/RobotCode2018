package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTwoCubesLeftRight extends CommandGroup {

    public AutoTwoCubesLeftRight() {
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(19, 12, 8, 60, false)));
        addSequential(new DriveTurnToAngle(137, false));
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.5, 12, 8, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 12, 8, 60, true)));
        addParallel(new ObtainCube());        
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 12, 8, 60, false)));
        
//        
    }
}
