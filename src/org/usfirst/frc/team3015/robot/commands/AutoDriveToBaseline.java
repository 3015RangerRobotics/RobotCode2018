package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveToBaseline extends CommandGroup {

    public AutoDriveToBaseline() {
    	addSequential(new DriveMotionProfile("wallToLine"));
    }
}
