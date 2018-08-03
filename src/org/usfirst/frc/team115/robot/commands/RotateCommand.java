package org.usfirst.frc.team115.robot.commands;

import org.usfirst.frc.team115.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCommand extends Command 
{
    public RotateCommand() 
    {
    	requires(Robot.intake);
    }

    protected void initialize() 
    {
    	
    }

    protected void execute() 
    {
    	Robot.intake.rotateIntake();
    }

    protected boolean isFinished() 
    {
        return !(Robot.oi.rotatePressed());
    }
    
    protected void end() 
    {
    	Robot.intake.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}
