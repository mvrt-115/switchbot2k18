package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeFromPyramid extends Command{
	private String elevateTo;
	
	public IntakeFromPyramid(String elevateTo) { 
		requires (Robot.elevator);
		this.elevateTo=elevateTo;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (this.elevateTo == "level 2") {
			Robot.elevator.setPointGenerator(Constants.kLevelTwo);
		}
		if (this.elevateTo == "level 3") {
			Robot.elevator.setPointGenerator(Constants.kLevelThree);
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
