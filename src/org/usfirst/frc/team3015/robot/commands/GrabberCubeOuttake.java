package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GrabberCubeOuttake extends CommandGroup {

    public GrabberCubeOuttake() {
        addSequential(new GrabberOpenIfDown());
        addSequential(new GrabberDown(false));
        addSequential(new GrabberIntakeOut());
//        addSequential(new GrabberEjectorOut());
//        addSequential(new WaitCommand(0.25));
//        addSequential(new GrabberEjectorIn());
    }
}
