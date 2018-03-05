package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoTwoCubesRightLeft extends CommandGroup {

    public AutoTwoCubesRightLeft() {
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addParallel(new GrabberCubeEject());
        addParallel(new ElevatorToBottomDelayed(2));
        addSequential(new DriveMotionProfile("test"));
        addParallel(new ObtainCubeToScale());
        addSequential(new DriveTurnToAngle(120, false));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, false)));
        addSequential(new WaitCommand(1));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}