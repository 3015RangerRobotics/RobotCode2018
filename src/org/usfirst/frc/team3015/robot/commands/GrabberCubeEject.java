package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GrabberCubeEject extends CommandGroup {

    public GrabberCubeEject() {
        addSequential(new GrabberOpen());
        addSequential(new GrabberDown());
        addSequential(new GrabberIntakeOut());
        addSequential(new GrabberEjectorOut());
        addSequential(new WaitCommand(1));
        addSequential(new GrabberEjectorIn());
    }
}
