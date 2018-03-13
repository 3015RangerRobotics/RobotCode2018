package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Grabber that can open and close to contain power cubes, Intake that rolls the power cubes in
 * Operates on a double solenoids. Intake runs on 1 victorSP
 */
public class Grabber extends Subsystem {
	private DoubleSolenoid grabberSolenoid;
	private DoubleSolenoid ejector;
    private VictorSP intakeMotor1;
    private VictorSP intakeMotor2;
    private VictorSP angler;
//    private DigitalInput cubeDetector1;
//    private DigitalInput cubeDetector2;
    private DigitalInput anglerPosUp;
    private DigitalInput anglerPosDown;
    private AnalogPotentiometer cubeDetector;
    
    private final double ANGLER_UP_SPEED = -1.0;
    private final double ANGLER_DOWN_SPEED = 1.0;
    private final double INTAKE_SPEED = 0.85;
    private final double INTAKE_SPEED_SLOW = 0.3;
    private final double OUTTAKE_SPEED = -0.85;
    private final double OUTTAKE_SPEED_SLOW = -.65;
    private final double CUBE_PRESENT_VALUE = 0.35;

	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(Constants.grabberSolenoid1, Constants.grabberSolenoid2);
		ejector = new DoubleSolenoid(Constants.grabberEjectorSolenoid1, Constants.grabberEjectorSolenoid2);
    	intakeMotor1 = new VictorSP(Constants.intakeMotor1);
    	intakeMotor2 = new VictorSP(Constants.intakeMotor2);
    	angler = new VictorSP(Constants.intakeAngler);
//    	cubeDetector1 = new DigitalInput(Constants.cubeDetector1);
//    	cubeDetector2 = new DigitalInput(Constants.cubeDetector2);
    	cubeDetector = new AnalogPotentiometer(Constants.cubeDetector);
    	anglerPosUp = new DigitalInput(Constants.anglerPosUp);
    	anglerPosDown = new DigitalInput(Constants.anglerPosDown);
	}
	
    public void initDefaultCommand() {
        
    }
    
    @Override
    public void periodic() {
//    	System.out.println(cubeDetector.get());
    	SmartDashboard.putNumber("Cube Detector", cubeDetector.get());
    	SmartDashboard.putBoolean("Intake Down", isIntakeDown());
    	SmartDashboard.putBoolean("Intake Up", isIntakeUp());
    }
    
    public void closeGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void ejectorIn() {
    	ejector.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void ejectorOut() {
    	ejector.set(DoubleSolenoid.Value.kForward);
    }
    
    public boolean isCubePresent() {
//    	return !cubeDetector1.get() && !cubeDetector2.get();
    	return cubeDetector.get() > CUBE_PRESENT_VALUE;
    }
    
//    public boolean isOneCubeDetectorPressed() {
//    	return !cubeDetector1.get() || !cubeDetector2.get();
//    }
    
    public void setAngler(double speed) {
    	angler.set(speed);
    }
    
    public boolean isIntakeUp() {
    	return !anglerPosUp.get();
    }
    
    public boolean isIntakeDown() {
    	return !anglerPosDown.get();
    }
    
    public void intakeUp() {
    	if(!isIntakeUp()) {
    		setAngler(ANGLER_UP_SPEED);
    	}else {
    		anglerStop();
    	}
    }
    
    public void intakeDown() {
    	if(!isIntakeDown()) {
    		setAngler(ANGLER_DOWN_SPEED);
    	}else {
    		anglerStop();
    	}
    }
    
    public void anglerStop() {
    	angler.set(0);
    }
    
    public void intakeIn() {
    	intakeMotor1.set(INTAKE_SPEED);
    	intakeMotor2.set(-INTAKE_SPEED);
    }
    
    public void intakeInSlowly() {
    	intakeMotor1.set(INTAKE_SPEED_SLOW);
    	intakeMotor2.set(-INTAKE_SPEED_SLOW);
    }
    
    public void intakeOut() {
    	intakeMotor1.set(OUTTAKE_SPEED);
    	intakeMotor2.set(-OUTTAKE_SPEED);
    }
    
    public void intakeOutSlowly() {
    	intakeMotor1.set(OUTTAKE_SPEED_SLOW);
    	intakeMotor2.set(-OUTTAKE_SPEED_SLOW);
    }
    
    public void intakeStop() {
    	intakeMotor1.set(0);
    	intakeMotor2.set(0);
    }
    
}

