package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
		
	TalonSRX left, right;
	private int rIndex = 1;
	private int	lIndex = 2;
	
	public Elevator(){
		
		//initialize the motors
		left = new TalonSRX(lIndex); // Left talon lIndex is the number of the talon
		right = new TalonSRX(rIndex);// same as above
		right.set(ControlMode.Follower, lIndex); // right motor fowllows the left
		
		//configure feedback device
		left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		left.setSensorPhase(true);
		left.setInverted(false);
		
		// frame period is the interval between each loop. Sets the status frame to be atleast as fast as the periodic rate
		left.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		left.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
		
		// sets the nominal and peak outputs
		left.configNominalOutputForward(0, Constants.kTimeoutMs);
		left.configNominalOutputReverse(0, Constants.kTimeoutMs);
		left.configPeakOutputForward(1, Constants.kTimeoutMs);
		left.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		
		//configure cruise speed 
		left.configMotionCruiseVelocity(15000, Constants.kTimeoutMs); //  This is the peak target velocity that the motion magic curve generator can use.
		left.configMotionAcceleration(6000, Constants.kTimeoutMs); // This is the target acceleration that the motion magic curve generator can use.
		

		left.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);//zeros the encoder
		
	}
	
	public void elevateManually() {
		left.set(ControlMode.PercentOutput, 1);
	}
	
	public void setPointGenerator(double height) { //(height measured in meters thus need to convert)
		double target = height * 39.3048  / (1.273 * Math.PI) * 4096; // pulley diameter is not final
		left.set(ControlMode.MotionMagic, target); 
	}
	
	public void zero() {
		setPointGenerator(0.00);
	}
	
	public void stop() {
		left.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
