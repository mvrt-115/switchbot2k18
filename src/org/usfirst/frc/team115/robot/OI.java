/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team115.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick operaterConsole;
	JoystickButton elevateToSwitchLow;
	JoystickButton elevateToSwitchHigh;
	JoystickButton zeroElevator;
	JoystickButton levelTwoElevate;
	JoystickButton levelThreeElevate;
	JoystickButton manualElevate;
	public OI(){
		 operaterConsole = new Joystick(2);
			//switch
			elevateToSwitchLow = new JoystickButton(operaterConsole, 1);
			elevateToSwitchHigh = new JoystickButton(operaterConsole, 2);
			//ground operations	
			zeroElevator = new JoystickButton(operaterConsole, 3);
			//Intakes at various heights
			levelTwoElevate = new JoystickButton(operaterConsole, 4);
			levelThreeElevate = new JoystickButton(operaterConsole, 5);
			manualElevate = new JoystickButton(operaterConsole, 6);

		
		elevateToSwitchLow.whenPressed(new ElevateToSwitch("Low"));		
		elevateToSwitchHigh.whenPressed(new ElevateToSwitch("High"));	
		zeroElevator.whenPressed(new ZeroElevator());	
		levelTwoElevate.whenPressed(new IntakeFromPyramid("level 2"));	
		levelThreeElevate.whenPressed(new IntakeFromPyramid("level 3"));	
		manualElevate.whenPressed(new ManualElevate());	

		
	}
	
	public boolean manualElevatePressed() {
		return manualElevate.get();
	}
	
	
