package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabberCubeControl extends CommandGroup {

    public GrabberCubeControl() {
        addSequential(new GrabberOpen());
        addSequential(new GrabberDown());
        addSequential(new GrabberIntakeTilCube());
        addSequential(new WaitForButtonPressed(CommandBase.oi.driverLB5));
        addSequential(new GrabberIntakeOut());
    }
}
