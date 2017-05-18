package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;
import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropCommand extends Command {

	public DropCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(RobotMap.DRIVETRAIN);
	}

	// Called just before this Command runs the first time
	boolean goingDown;
	boolean finished;

	protected void initialize() {
		if (RobotMap.DRIVETRAIN.dropDown == true) {
			RobotMap.DRIVETRAIN.pistonUp();
			RobotMap.DRIVETRAIN.dropDown = false;
			goingDown = false;
		} else {
			RobotMap.DRIVETRAIN.drop();
			goingDown = true;
		}
		finished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (goingDown = true) {
			if (RobotMap.DRIVETRAIN.getLastAct() + 1000 <= System.currentTimeMillis()) {
				RobotMap.DRIVETRAIN.stopPiston();
				RobotMap.DRIVETRAIN.dropDown = true;
			}
			if (RobotMap.DRIVETRAIN.dropDown == true) {
				RobotMap.DRIVETRAIN.setDropWheel(Robot.oi.getJoy().getRawAxis(0));
			}
		} else {
			if (RobotMap.DRIVETRAIN.getLastAct() + 1000 <= System.currentTimeMillis()) {
				RobotMap.DRIVETRAIN.stopPiston();
				finished = true;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.DRIVETRAIN.stopPiston();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
