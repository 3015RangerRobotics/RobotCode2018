package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * a button that returns true when 2 buttons are pressed
 * @author ngiano
 *
 */
public class DoubleButton extends Button{
	Button button1;
	Button button2;
	
	/**
	 * Creates a double button, which is a button that returns true when 2 buttons are pressed
	 * @param button1 Button 1
	 * @param button2 Button 2
	 */
	public DoubleButton(Button button1, Button button2) {
		this.button1 = button1;
		this.button2 = button2;
	}

	/**
	 * @return if button1 and button2 are both pressed
	 */
	@Override
	public boolean get() {
		return (button1.get() && button2.get());
	}
}
