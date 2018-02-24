package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GrabberCubeControl extends CommandGroup {

    public GrabberCubeControl() {
        addSequential(new GrabberOpen());
        addSequential(new WaitCommand(.5));
        addSequential(new GrabberDown());
        addSequential(new GrabberIntakeTilCube());
        addSequential(new WaitForButtonPressed());
        addSequential(new GrabberIntakeOut());
    }
}
