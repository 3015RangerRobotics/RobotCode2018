package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Grabber extends Subsystem {
	private DoubleSolenoid grabberSolenoid;
	private DoubleSolenoid ejector;
    private VictorSP intakeMotor1;
    private VictorSP intakeMotor2;
    private VictorSP intakeAngler;
    private DigitalInput anglerPosUp;
    private DigitalInput anglerPosDown;
    private AnalogPotentiometer cubeDetector1;
    private AnalogPotentiometer cubeDetector2;
    
    private final double ANGLER_UP_SPEED = -1.0;
    private final double ANGLER_DOWN_SPEED = 1.0;
    private final double INTAKE_SPEED = 0.75;
    private final double INTAKE_SPEED_SLOW = 0.3;
    private final double OUTTAKE_SPEED = -0.85;
    private final double OUTTAKE_SPEED_SLOW = -.65;
    private final double CUBE_PRESENT_VALUE = 0.4;

	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(Constants.grabberSolenoid1, Constants.grabberSolenoid2);
		ejector = new DoubleSolenoid(Constants.grabberEjectorSolenoid1, Constants.grabberEjectorSolenoid2);
    	intakeMotor1 = new VictorSP(Constants.intakeMotor1);
    	intakeMotor2 = new VictorSP(Constants.intakeMotor2);
    	intakeAngler = new VictorSP(Constants.intakeAngler);
    	cubeDetector1 = new AnalogPotentiometer(Constants.cubeDetector1);
    	cubeDetector2 = new AnalogPotentiometer(Constants.cubeDetector2);
    	anglerPosUp = new DigitalInput(Constants.anglerPosUp);
    	anglerPosDown = new DigitalInput(Constants.anglerPosDown);
	}
	
    public void initDefaultCommand() {
        
    }
    
    @Override
    public void periodic() {
    	SmartDashboard.putNumber("Cube Detector 1", cubeDetector1.get());
    	SmartDashboard.putNumber("Cube Detector 2", cubeDetector2.get());
    	SmartDashboard.putBoolean("Intake Down", isIntakeDown());
    	SmartDashboard.putBoolean("Intake Up", isIntakeUp());
    	SmartDashboard.putBoolean("Cube Present", isCubePresent());
    }
    
    /**
     * Close the grabber
     */
    public void closeGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Open the grabber
     */
    public void openGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Pull the ejector in
     */
    public void ejectorIn() {
    	ejector.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Push the ejector out
     */
    public void ejectorOut() {
    	ejector.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * @return Is a cube in the intake
     */
    public boolean isCubePresent() {
    	return cubeDetector1.get() >= CUBE_PRESENT_VALUE && cubeDetector2.get() >= CUBE_PRESENT_VALUE;
    }
    
    /**
     * Set the speed of the intake angler
     * @param speed Speed of the motor
     */
    public void setIntakeAngler(double speed) {
    	intakeAngler.set(speed);
    }
    
    /**
     * @return Is the intake in the up position
     */
    public boolean isIntakeUp() {
    	return !anglerPosUp.get();
    }
    /**
     * @return Is the intake in the down position
     */
    public boolean isIntakeDown() {
    	return !anglerPosDown.get();
    }
    
    /**
     * Bring the intake up unless it is at the up position
     */
    public void intakeUp() {
    	if(!isIntakeUp()) {
    		setIntakeAngler(ANGLER_UP_SPEED);
    	}else {
    		intakeAnglerStop();
    	}
    }
    
    /**
     * Bring the intake down unless it is at the down position
     */
    public void intakeDown() {
    	if(!isIntakeDown()) {
    		setIntakeAngler(ANGLER_DOWN_SPEED);
    	}else {
    		intakeAnglerStop();
    	}
    }
    
    /**
     * Stop moving the intake up/down
     */
    public void intakeAnglerStop() {
    	intakeAngler.set(0);
    }
    
    /**
     * Intake in
     */
    public void intakeIn() {
    	intakeMotor1.set(INTAKE_SPEED);
    	intakeMotor2.set(-INTAKE_SPEED);
    }
    
    /**
     * Intake in slowly
     */
    public void intakeInSlowly() {
    	intakeMotor1.set(INTAKE_SPEED_SLOW);
    	intakeMotor2.set(-INTAKE_SPEED_SLOW);
    }
    
    /**
     * Intake out
     */
    public void intakeOut() {
    	intakeMotor1.set(OUTTAKE_SPEED);
    	intakeMotor2.set(-OUTTAKE_SPEED);
    }
    
    /**
     * Intake out slowly
     */
    public void intakeOutSlowly() {
    	intakeMotor1.set(OUTTAKE_SPEED_SLOW);
    	intakeMotor2.set(-OUTTAKE_SPEED_SLOW);
    }
    
    /**
     * Stop the intake
     */
    public void intakeStop() {
    	intakeMotor1.set(0);
    	intakeMotor2.set(0);
    }
}
