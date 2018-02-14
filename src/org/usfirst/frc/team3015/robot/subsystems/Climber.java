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
	DigitalInput diSensorBottom;
	DigitalInput diSensorMiddle;
	DigitalInput diSensorTop;
	
	final double CLIMBER_CURRENT = 1.0;
	
	public Climber() {
		childClimber = new VictorSPX(Constants.climberChildVictorSPX);
		parentClimber = new TalonSRX(Constants.climberParentTalonSRX);
		diSensorBottom = new DigitalInput(Constants.climberDigitalInputBottom);
		diSensorMiddle = new DigitalInput(Constants.climberDigitalInputMiddle);
		diSensorTop = new DigitalInput(Constants.climberDigitalInputTop);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public int getLocation() {
    	if(!diSensorBottom.get()) {
    		return 0;
    	} else if (!diSensorTop.get()) {
    		return 2;
    	} else if (!diSensorMiddle.get()) {
    		return 1;
    	} else {
    		return -1;
    	}
    }
    
    public void extendClimber() {
    	childClimber.set(ControlMode.Follower, Constants.climberParentTalonSRX);
    	parentClimber.set(ControlMode.Current, CLIMBER_CURRENT);
    }
    
    public void retractClimber() {
    	childClimber.set(ControlMode.Follower, Constants.climberParentTalonSRX);
    	parentClimber.set(ControlMode.Current, 0);
    }
    
    while(!(diSensorBottom || diSensorMiddle || diSensorTop)) {
    	
    }
    
}

