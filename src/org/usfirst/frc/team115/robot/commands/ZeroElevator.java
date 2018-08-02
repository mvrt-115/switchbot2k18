package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroElevator extends Command {
	
	public ZeroElevator() {
		requires(Robot.elevator);
	}
	
	public void execute() {
		Robot.elevator.zero();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
