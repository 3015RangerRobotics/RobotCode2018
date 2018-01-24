package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Intake Subsystem, intakes cubes into the grabber
 */
public class Intake extends Subsystem {
    private VictorSP intakeMotor;

    private final double INTAKE_IN_SPEED = 1.0;
    private final double INTAKE_OUT_SPEED = -1.0;
    
    public Intake() {
    	intakeMotor = new VictorSP(Constants.intakeMotor);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeStop());
    }
    
    public void intake() {
    	intakeMotor.set(INTAKE_IN_SPEED);
    }
    
    public void reverseIntake() {
    	intakeMotor.set(INTAKE_OUT_SPEED);
    }
    
    public void stopIntake() {
    	intakeMotor.set(0);
    }
}

