package org.usfirst.frc.team1797.robot.subsystems;

import org.usfirst.frc.team1797.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	VictorSP left1, left2, right1, right2, dropWheel;
	DoubleSolenoid dropPiston;
	long lastAct;
	public boolean dropDown;

	public DriveTrain() {
		left1 = new VictorSP(0);
		left2 = new VictorSP(1);
		right1 = new VictorSP(2);
		right2 = new VictorSP(3);
		dropWheel = new VictorSP(4);
		dropPiston = new DoubleSolenoid(0, 1);
		lastAct = Long.MAX_VALUE;
		dropDown = false;
	}

	public void setLeft(double x) {
		left1.set(x);
		left2.set(x);
	}

	public void setRight(double x) {
		right1.set(x);
		right2.set(x);
	}

	public void stopDrive() {
		left1.set(0);
		left2.set(0);
		right1.set(0);
		right2.set(0);
	}

	public void drop() {
		dropPiston.set(DoubleSolenoid.Value.kForward);
		lastAct = System.currentTimeMillis();
	}

	public void pistonUp() {
		dropPiston.set(DoubleSolenoid.Value.kReverse);
		lastAct = System.currentTimeMillis();
	}

	public void stopPiston() {
		dropPiston.set(DoubleSolenoid.Value.kOff);
		lastAct = Long.MAX_VALUE;
	}

	public void setDropWheel(double x) {
		dropWheel.set(x);
	}

	public long getLastAct() {
		return lastAct;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveCommand());
	}
}
