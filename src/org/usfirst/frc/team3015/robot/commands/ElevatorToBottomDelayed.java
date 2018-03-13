package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ElevatorToBottomDelayed extends CommandGroup {
	
    public ElevatorToBottomDelayed(double delay) {
        addSequential(new WaitCommand(delay));
        addSequential(new ElevatorToBottom());
    }
}
