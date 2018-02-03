package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Grabber that can open and close to contain power cubes, Intake that rolls the power cubes in
 * Operates on a double solenoids. Intake runs on 1 victorSP
 */
public class Grabber extends Subsystem {
	DoubleSolenoid grabberSolenoid;
	DigitalInput grabberSwitch1;
	DigitalInput grabberSwitch2; //limit switch
    private VictorSP intakeMotor;

    private final double INTAKE_IN_SPEED = 1.0;
    private final double INTAKE_OUT_SPEED = -1.0;
    
	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(Constants.grabberSolenoid1, Constants.grabberSolenoid2);
		grabberSwitch1 = new DigitalInput(Constants.grabberLimitSwitch1);
		grabberSwitch2 = new DigitalInput(Constants.grabberLimitSwitch2);
    	intakeMotor = new VictorSP(Constants.intakeMotor);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public void closeGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public boolean isCubePresent() {
    	return (grabberSwitch1.get() && grabberSwitch2.get());
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

