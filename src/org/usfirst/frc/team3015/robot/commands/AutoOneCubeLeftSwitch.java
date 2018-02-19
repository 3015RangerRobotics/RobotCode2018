package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneCubeLeftSwitch extends CommandGroup {

    public AutoOneCubeLeftSwitch() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("wallToLeftSwitch"));
        addSequential(new GrabberOpen());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(-1, -10, -5, 0.01)));
        addSequential(new ElevatorToBottom());
    }
}
