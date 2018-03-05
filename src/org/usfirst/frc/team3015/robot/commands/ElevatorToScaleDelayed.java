package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ElevatorToScaleDelayed extends CommandGroup {

    public ElevatorToScaleDelayed(double delay) {
        addSequential(new WaitCommand(delay));
        addSequential(new ElevatorToScale());
    }
}
