package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.commands.IntakeCommand;
import org.usfirst.frc.team115.robot.commands.OuttakeCommand;
import org.usfirst.frc.team115.robot.commands.RotateCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI 
{
	Joystick driverJoystick;
	JoystickButton intake;
	JoystickButton outtake;
	JoystickButton rotate;
	
	public OI()
	{
		driverJoystick = new Joystick(0);
		intake = new JoystickButton(driverJoystick, 0);	
		outtake = new JoystickButton(driverJoystick, 1); 
		rotate = new JoystickButton(driverJoystick, 2);
		
		intake.whenPressed(new IntakeCommand());
		outtake.whenPressed(new OuttakeCommand());
		rotate.whenPressed(new RotateCommand());
	}
	
	public boolean intakePressed() 
	{
		return intake.get();
	}
	
	public boolean outtakePressed() 
	{
		return outtake.get();
	}
	
	public boolean rotatePressed()
	{
		return rotate.get();
	}
}
