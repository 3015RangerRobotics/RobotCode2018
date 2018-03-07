package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GrabberReset extends CommandGroup {

    public GrabberReset() {
    	addSequential(new WaitCommand(0.5));
        addSequential(new GrabberEjectorIn());
        addSequential(new GrabberUp());
    }
}
