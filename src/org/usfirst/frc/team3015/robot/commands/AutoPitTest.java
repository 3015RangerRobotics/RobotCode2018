package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPitTest extends CommandGroup {

    public AutoPitTest() {
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 10, 3, 60, false)));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 10, 3, 60, true)));
    }
}
