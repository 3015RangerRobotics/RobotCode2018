package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class WaitForButtonPressed extends CommandBase {

    public WaitForButtonPressed() {
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return oi.getDriverLB();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}