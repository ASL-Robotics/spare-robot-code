package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;
import org.usfirst.frc.team1797.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

	public DriveCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Joystick joy = Robot.oi.getJoy();
		double straight = joy.getRawAxis(1);
		double turn = joy.getRawAxis(2);
		straight *= straight;
		turn *= turn;
		RobotMap.DRIVETRAIN.setLeft(turn + straight);
		RobotMap.DRIVETRAIN.setRight(turn - straight);

		RobotMap.DRIVETRAIN.setDropWheel(RobotMap.DRIVETRAIN.dropDown ? turn : 0);

		// Autostop drop module when it has extended for the right amount of
		// time
		if (RobotMap.DRIVETRAIN.getLastAct() + 1000 <= System.currentTimeMillis()) {
			RobotMap.DRIVETRAIN.stopPiston();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.DRIVETRAIN.stopDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
