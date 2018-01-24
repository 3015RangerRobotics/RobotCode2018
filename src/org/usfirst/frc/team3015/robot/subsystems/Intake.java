package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.VictorSP;
/**
 *
 */
public class Intake extends Subsystem {

    private VictorSP intakeMotor;

    private final double INTAKE_IN_SPEED = 1.0;
    private final double INTAKE_OUT_SPEED = 1.0;
    
    public Intake() {
    	intakeMotor = new VictorSP(Constants.intakeMotor);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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

