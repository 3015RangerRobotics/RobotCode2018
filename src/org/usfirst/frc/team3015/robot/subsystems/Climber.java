package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	VictorSPX childClimber;
	TalonSRX parentClimber;
	DigitalInput diSensor;
	
	final double CLIMBER_CURRENT = 1.0;
	
	public Climber() {
		childClimber = new VictorSPX(Constants.climberChildVictorSPX);
		parentClimber = new TalonSRX(Constants.climberParentTalonSRX);
		diSensor = new DigitalInput(Constants.climberDigitalInput);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public boolean getDigitalInputValue() {
    	return diSensor.get();
    }
    
    public void extendClimber() {
    	childClimber.set(ControlMode.Follower, Constants.climberParentTalonSRX);
    	parentClimber.set(ControlMode.Current, CLIMBER_CURRENT);
    }
    
    public void retractClimber() {
    	childClimber.set(ControlMode.Follower, Constants.climberParentTalonSRX);
    	parentClimber.set(ControlMode.Current, 0);
    }
}

