package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	TalonSRX climber1;
	VictorSPX climber2;
	VictorSP climberLock;
//	DigitalInput diSensorBottom;
//	DigitalInput diSensorMiddle;
//	DigitalInput diSensorTop;
	
	final double CLIMBER_PERCENT = 1.0;
	
	public Climber() {
		climber1 = new TalonSRX(Constants.climberParentTalonSRX);
		climber2 = new VictorSPX(Constants.climberChildVictorSPX);
		climberLock = new VictorSP(Constants.climberLock);
//		diSensorBottom = new DigitalInput(Constants.climberDigitalInputBottom);
//		diSensorMiddle = new DigitalInput(Constants.climberDigitalInputMiddle);
//		diSensorTop = new DigitalInput(Constants.climberDigitalInputTop);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public int getLocation() {
//    	if(!diSensorBottom.get()) {
//    		return 0;
//    	} else if (!diSensorTop.get()) {
//    		return 2;
//    	} else if (!diSensorMiddle.get()) {
//    		return 1;
//    	} else {
//    		return -1;
//    	}
    	return -1;
    }
    
    public void climberHold() {
    	climber1.configVoltageCompSaturation(13.0, 10);
    	climber1.enableVoltageCompensation(true);
    	climber1.set(ControlMode.PercentOutput, -1.5/13);
    	climber2.configVoltageCompSaturation(13.0, 10);
    	climber2.enableVoltageCompensation(true);
    	climber2.set(ControlMode.PercentOutput, 1.5/13);
    }
    
    public void climbUp() {
    	climber1.enableVoltageCompensation(false);
    	climber1.set(ControlMode.PercentOutput, CLIMBER_PERCENT);
    	climber2.enableVoltageCompensation(false);
    	climber2.set(ControlMode.PercentOutput, -CLIMBER_PERCENT);
    }
    
    public void retractClimber() {
    	climber1.enableVoltageCompensation(false);
    	climber1.set(ControlMode.PercentOutput, -CLIMBER_PERCENT);
    	climber2.enableVoltageCompensation(false);
    	climber2.set(ControlMode.PercentOutput, CLIMBER_PERCENT);
    }
    
    public void stopClimber() {
    	climber1.enableVoltageCompensation(false);
    	climber1.set(ControlMode.PercentOutput, 0);
    	climber2.enableVoltageCompensation(false);
    	climber2.set(ControlMode.PercentOutput, 0);
    }
    
    public void climberLockOut() {
    	climberLock.set(-1);
    }
    
    public void climberLockStop() {
    	climberLock.set(0);
    }
}

