package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTwoCubesLeftScale extends CommandGroup {

    public AutoTwoCubesLeftScale() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 8, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngle(115));
        addParallel(new ObtainCube());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 12, 8, 60, false)));
    }
}
