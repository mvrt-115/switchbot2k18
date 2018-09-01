package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Hardware;
import org.usfirst.frc.team115.robot.commands.IntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem 
{
	private DoubleSolenoid intake;
	public DigitalInput breakbeam;
	public double setPoint;
	public double rotations;
	int state;
	
	public Intake()  
	{
		state = 0; //0 = disabled, 1 = rotating out, 2 = rotating in, 3 = hold
		Hardware.left = new TalonSRX(Constants.talonLeftIntake);	//left cantalon port tbd
		Hardware.right = new TalonSRX(Constants.talonRightIntake);   //right cantalon port tbd
		Hardware.rot = new TalonSRX(Constants.talonRotateIntake);     //rot cantalon port tbd
		
		/* first choose the sensor */
		Hardware.rot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		Hardware.rot.setSensorPhase(true);
		Hardware.rot.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		Hardware.rot.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		Hardware.rot.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* set the peak and nominal outputs */
		Hardware.rot.configNominalOutputForward(0, Constants.kTimeoutMs);
		Hardware.rot.configNominalOutputReverse(0, Constants.kTimeoutMs);
		Hardware.rot.configPeakOutputForward(0.5, Constants.kTimeoutMs);
		Hardware.rot.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		Hardware.rot.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		Hardware.rot.config_kF(0, 0.2, Constants.kTimeoutMs);
		Hardware.rot.config_kP(0, 0.2, Constants.kTimeoutMs);
		Hardware.rot.config_kI(0, 0, Constants.kTimeoutMs);
		Hardware.rot.config_kD(0, 0, Constants.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		Hardware.rot.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		Hardware.rot.configMotionAcceleration(6000, Constants.kTimeoutMs);
		/* zero the sensor */
		Hardware.rot.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		
		new Notifier(new Runnable()
		{
			@Override
			public void run()
			{
				loop();
			}
		}).startPeriodic(0.005);
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
		Hardware.left.set(ControlMode.PercentOutput, -0.65);
		Hardware.right.set(ControlMode.PercentOutput, 0.65);
		//Robot.carriage.intakeCube(-0.45); //use if carriage is needed
	}
	
	public void outtakeCube() 
	{
		Hardware.left.set(ControlMode.PercentOutput, 0.65);
		Hardware.right.set(ControlMode.PercentOutput, -0.65);
		//Robot.carriage.outtakeCube(-1); //use if carriage is needed
	}
	
	public void rotateIn()
	{
		setPoint = 4096 * rotations;
		Hardware.rot.set(ControlMode.MotionMagic, setPoint);
	}
	
	public void rotateOut()
	{
		setPoint = -4096 * rotations;
		Hardware.rot.set(ControlMode.MotionMagic, setPoint);
	}
	
	public void loop()
	{
		if(state == 1)
		{
			if(Hardware.rot.getSelectedSensorPosition(0) > Constants.lowBufferIntakeOut)
			{
				stop();
			}
			else
			{
				Hardware.rot.set(ControlMode.MotionMagic, setPoint);
			}
		}
		else if(state == 2)
		{
			if(Hardware.rot.getSelectedSensorPosition(0) < Constants.lowBufferIntakeIn)
			{
				stop();
			}
			else
			{
				Hardware.rot.set(ControlMode.MotionMagic, setPoint);
			}
		}
	}
	
	public void stop()  
	{
		Hardware.left.set(ControlMode.PercentOutput, 0);
		Hardware.right.set(ControlMode.PercentOutput, 0);
		Hardware.rot.set(ControlMode.PercentOutput, 0);
	}
	
	public void initDefaultCommand()  
	{
		setDefaultCommand(new IntakeCommand());
	}
}

