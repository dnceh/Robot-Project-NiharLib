package com.palyrobotics.frc2017.config;

import com.palyrobotics.frc2017.subsystems.*;
import com.palyrobotics.frc2017.util.archive.DriveSignal;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Commands represent the desired setpoints and subsystem states for the robot. <br />
 * Store Requests (enum) for each subsystem and setpoints {@link Setpoints} <br />
 * Directly stores real or mock Joystick input in {@link JoystickInput} <br />
 * Variables are public and have default values to prevent NullPointerExceptions
 * @author Nihar
 *
 */
public class Commands {

	private static Commands instance = new Commands();
	public static Commands getInstance() {
		return instance;
	}

	private Commands() {}

	// Store WantedStates for each subsystem state machine
	public Drive.DriveState wantedDriveState = Drive.DriveState.NEUTRAL;
	public Slider.SliderState wantedSliderState = Slider.SliderState.IDLE;
	public Climber.ClimberState wantedClimberState = Climber.ClimberState.IDLE;

	public static void reset() {
		instance = new Commands();
	}

	/**
	 * Stores numeric setpoints
	 * @author Nihar
	 */
	// All robot setpoints
	
	/**
	 * Class to store Joystick input
	 * @author Nihar
	 */
	public static class JoystickInput {

		public class XboxInput extends JoystickInput {
			public double leftX, leftY, rightX, rightY;
			public XboxInput(double leftX, double leftY, double rightX, double rightY) {
				super(leftX, leftY, false);
				this.leftX = leftX;
				this.leftY = leftY;
				this.rightX = rightX;
				this.rightY = rightY;
			}
		}
		public double x,y;
		public boolean triggerPressed;
		public JoystickInput(double x, double y, boolean triggerPressed) {
			this.x = x; this.y = y; this.triggerPressed = triggerPressed;
		}
		@Override
		public String toString() {
			return "Joystick X: "+this.x+" Y: "+ this.y;
		}
	}
	// Stores Joystick values
	public JoystickInput leftStickInput = new JoystickInput(0,0, false);
	public JoystickInput rightStickInput = new JoystickInput(0,0, false);
	public JoystickInput sliderStickInput = new JoystickInput(0, 0, false);
	public JoystickInput climberStickInput = new JoystickInput(0, 0, false);

	// Allows you to cancel all running routines
	public boolean cancelCurrentRoutines = false;

	/**
	 * @return a copy of these commands
	 */
	public Commands copy() {
		Commands copy = new Commands();
		copy.wantedDriveState = this.wantedDriveState;
		copy.wantedSliderState = this.wantedSliderState;
		copy.wantedClimberState = this.wantedClimberState;

		copy.cancelCurrentRoutines = this.cancelCurrentRoutines;
		copy.leftStickInput = this.leftStickInput;
		copy.rightStickInput = this.rightStickInput;
		copy.sliderStickInput = this.sliderStickInput;
		copy.climberStickInput = this.climberStickInput;
		
		// Copy robot setpoints
		// Copy optionals that are present
		return copy;
	}

	@Override
	public String toString() {
		String log = "";
		log+="Wanted Drive State: "+wantedDriveState.toString()+"\n";
		log+="Wanted Slider State: "+wantedSliderState.toString()+"\n";
		log+="Wanted Climber State: "+wantedClimberState.toString()+"\n";
		log+="Left Driver Stick: "+leftStickInput;
		log+="Right Driver Stick: "+rightStickInput+"\n";
		log+="Slider Stick: "+ sliderStickInput+"\n";
		log+="Climber Stick: "+sliderStickInput+"\n";
		String wantedRoutineName = "";
		log+="Wanted Routines: "+wantedRoutineName+"\n";

		return log;
	}
}