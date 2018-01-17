package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.TripleCIM;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Prototype3CIM extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP cim1;
	VictorSP cim2;
	VictorSP cim3;
	public Prototype3CIM() {
		cim1 = new VictorSP(13);
		cim2 = new VictorSP(14);
		cim3 = new VictorSP(15);		
	}
    public void initDefaultCommand() {
        setDefaultCommand(new TripleCIM());
    }
    public void driveSpeed(double speed1) {
    	cim1.set(speed1);
    	cim2.set(speed1);
    	cim3.set(speed1);
    }
}

