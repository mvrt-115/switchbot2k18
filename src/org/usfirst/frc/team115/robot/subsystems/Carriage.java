package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.commands.CarriageCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Carriage extends Subsystem 
{
	private TalonSRX left, right, rot;
	
	public Carriage()  
	{
		left = new TalonSRX(5);	//port tbd
		right = new TalonSRX(6); //port tbd
		rot = new TalonSRX(7); //port tbd
		
		right.set(ControlMode.Follower, left.getDeviceID());
		right.setInverted(true);
	}
	
	public void intakeCube(double motorSpeed) 
	{
		left.set(ControlMode.PercentOutput, motorSpeed);
	}
	
	public void outtakeCube(double motorSpeed) //motorSpeed should be negative
	{ 
		left.set(ControlMode.PercentOutput, (motorSpeed > 0 ? -1.0 : 1.0) * motorSpeed);
	}
	
	public void rotate(double motorSpeed)
	{
		rot.set(ControlMode.PercentOutput, motorSpeed);
	}
	public void stop()  
	{
		left.set(ControlMode.PercentOutput, 0);
		rot.set(ControlMode.PercentOutput, 0);
	}
	
	protected void initDefaultCommand() 
	{
		setDefaultCommand(new CarriageCommand());
	}

}