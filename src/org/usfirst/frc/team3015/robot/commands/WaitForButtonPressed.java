package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *
 */
public class WaitForButtonPressed extends CommandBase {
	private Button button;

    public WaitForButtonPressed(Button button) {
        this.button = button;
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return button.get();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
