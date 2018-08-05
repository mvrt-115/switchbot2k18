package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.commands.IntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem 
{
	private DoubleSolenoid intake;
	private TalonSRX left;
	private TalonSRX right;
	private TalonSRX rot;
	public DigitalInput breakbeam;

	public Intake()  
	{
		left = new TalonSRX(16);	//left cantalon port tbd
		right = new TalonSRX(17);   //right cantalon port tbd
		rot = new TalonSRX(18);     //rot cantalon port tbd
	}

	public void extendIntake() 
	{
		intake.set(Value.kReverse);
	}

	public void retractIntake() 
	{
		intake.set(Value.kForward);	
	}
	
	public void intakeCube() 
	{
		left.set(ControlMode.PercentOutput, -0.65);
		right.set(ControlMode.PercentOutput, 0.65);
		//Robot.carriage.intakeCube(-0.45); //use if carriage is needed
	}
	
	public void outtakeCube() 
	{
		left.set(ControlMode.PercentOutput, 0.65);
		right.set(ControlMode.PercentOutput, -0.65);
		//Robot.carriage.outtakeCube(-1); //use if carriage is needed
	}
	
	public void rotateIn()
	{
		rot.set(ControlMode.PercentOutput, 0.65);
	}
	
	public void rotateOut()
	{
		rot.set(ControlMode.PercentOutput, -0.65);
	}
	
	public void stop()  
	{
		left.set(ControlMode.PercentOutput, 0);
		right.set(ControlMode.PercentOutput, 0);
	}
	
	public void initDefaultCommand()  
	{
		setDefaultCommand(new IntakeCommand());
	}
}

