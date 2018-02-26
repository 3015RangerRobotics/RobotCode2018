package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GrabberCubeControl extends CommandGroup {

    public GrabberCubeControl() {
        addSequential(new GrabberOpen());
        addSequential(new GrabberEjectorIn());
//        addSequential(new WaitCommand(.1));
        addSequential(new GrabberDown(false));
        addSequential(new GrabberIntakeTilCube());
        addSequential(new DriverStartVibration());
        addSequential(new GrabberUp());
        addSequential(new GrabberClose());
//        addSequential(new WaitForButtonPressed());
//        addSequential(new GrabberIntakeOut());
    }
}
