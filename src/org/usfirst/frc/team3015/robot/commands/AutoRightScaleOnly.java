
package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightScaleOnly extends CommandGroup {

    public AutoRightScaleOnly() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScaleDelayed(3.75));
        addSequential(new DriveMotionProfile("wallToRightScale", false));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 10, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngle(130, true));
        addParallel(new ObtainCubeToScale());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4.35, 14, 10, 60, false)));
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveTurnToAngle(160, false));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}