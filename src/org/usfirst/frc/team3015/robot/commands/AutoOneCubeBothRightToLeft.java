package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LEFT SCALE RIGHT SWITCH
 */
public class AutoOneCubeBothRightToLeft extends CommandGroup {

    public AutoOneCubeBothRightToLeft() {
    	addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addSequential(new GrabberEject());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("leftScaleToRightSwitch"));
        addSequential(new GrabberEject());
        addSequential(new ElevatorToBottom());
    }
}
