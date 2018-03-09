
package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightScaleOnly extends CommandGroup {

    public AutoRightScaleOnly() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScaleDelayed(3.75));
        addSequential(new DriveMotionProfile("wallToRightScale", false));
        addParallel(new GrabberCubeEject());
//        addParallel(new ElevatorToBottomDelayed(2));
        addSequential(new DriveTurnToAngle(155, true));
        addParallel(new ObtainCubeToScale());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 10, 60, false)));
        addSequential(new DriveTurnToAngle(160, false));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}