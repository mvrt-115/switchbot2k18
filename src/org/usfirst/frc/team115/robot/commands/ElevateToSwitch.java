package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevateToSwitch extends Command {
	private String elevateTo;
	
	public ElevateToSwitch(String elevateTo) { 
		requires (Robot.elevator);
		this.elevateTo=elevateTo;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (this.elevateTo == "high") {
			Robot.elevator.setPointGenerator(Constants.kSwitchHigh);
		}
		if (this.elevateTo == "default") {
			Robot.elevator.setPointGenerator(Constants.kSwitchDefault);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	
	@Override
	protected void interrupted() {
	}
}
