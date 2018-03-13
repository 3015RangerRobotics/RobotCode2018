package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBaseline extends CommandGroup {

    public AutoBaseline() {
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(10, 12, 8, 60, false)));
    }
}
