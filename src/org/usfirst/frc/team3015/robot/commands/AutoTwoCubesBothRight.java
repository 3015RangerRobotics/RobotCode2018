
package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoTwoCubesBothRight extends CommandGroup {

    public AutoTwoCubesBothRight() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScaleDelayed(3.75));
        addSequential(new DriveMotionProfile("wallToRightScale", false));
        addSequential(new GrabberCubeEject());
//        addParallel(new ElevatorToBottomDelayed(2));
//        addParallel(new ObtainCubeToSwitch());
        addSequential(new DriveTurnToAngle(157, true));
//        addSequential(new ElevatorToBottom());
//        addSequential(new ElevatorToBottom());
        addParallel(new ObtainCubeToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 10, 60, false)));
        addSequential(new WaitCommand(1));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}