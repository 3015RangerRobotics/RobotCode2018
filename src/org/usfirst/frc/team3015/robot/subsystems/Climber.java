package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private TalonSRX climber1;
	private VictorSPX climber2;
	private DoubleSolenoid climberLock;
	
	private final double CLIMBER_SPEED = 1.0;
	
	public Climber() {
		climber1 = new TalonSRX(Constants.climberParentTalonSRX);
		climber2 = new VictorSPX(Constants.climberChildVictorSPX);
		climberLock = new DoubleSolenoid(Constants.climberLock1, Constants.climberLock2);
	}
	
    public void initDefaultCommand() {
        
    }
    
    /**
     * Give the climber a small amount of power to hold it when climbing
     */
    public void climberHold() {
    	climber1.configVoltageCompSaturation(13.0, 10);
    	climber1.enableVoltageCompensation(true);
    	climber1.set(ControlMode.PercentOutput, -1.5/13);
    	climber2.configVoltageCompSaturation(13.0, 10);
    	climber2.enableVoltageCompensation(true);
    	climber2.set(ControlMode.PercentOutput, 1.5/13);
    }
    
    /**
     * Drive the climber up
     */
    public void climbUp() {
    	climber1.enableVoltageCompensation(false);
    	climber1.set(ControlMode.PercentOutput, CLIMBER_SPEED);
    	climber2.enableVoltageCompensation(false);
    	climber2.set(ControlMode.PercentOutput, -CLIMBER_SPEED);
    }
    
    /**
     * Drive the climber down
     */
    public void retractClimber() {
    	climber1.enableVoltageCompensation(false);
    	climber1.set(ControlMode.PercentOutput, -CLIMBER_SPEED);
    	climber2.enableVoltageCompensation(false);
    	climber2.set(ControlMode.PercentOutput, CLIMBER_SPEED);
    }
    
    /**
     * Stop the climber motors
     */
    public void stopClimber() {
    	climber1.enableVoltageCompensation(false);
    	climber1.set(ControlMode.PercentOutput, 0);
    	climber2.enableVoltageCompensation(false);
    	climber2.set(ControlMode.PercentOutput, 0);
    }
    
    /**
     * Deploy the anti-sway bar
     */
    public void climberLockOut() {
    	climberLock.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Retract the anti-sway bar
     */
    public void climberLockIn() {
    	climberLock.set(DoubleSolenoid.Value.kReverse);
    }
}

