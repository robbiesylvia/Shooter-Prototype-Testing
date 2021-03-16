// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int talonFirstChannel = 13;
    public static int talonSecondChannel = 14;
    public static int timesExecuted = 0;

	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * now we just want the primary one.
	 * Talon FX supports multiple (cascaded) PID loops. For 
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
    public static final int kTimeoutMs = 0;

	
	public static int lowerBoundPotentiometer = 32;

	public static int upperBoundPotentiometer = 90;
	public static int deviceIDCANSparkMax = 0;



	  // PID coefficients
	 /*public static double kP = 5e-5; 
	 public static double kI = 1e-6;
	 public static double kD = 0; 
	 public static double kIz = 0; 
	 public static double kFF = 0.000156; 
	 public static double kMaxOutput = 1; 
	 public static double kMinOutput = -1;
	 public static double maxRPM = 5700; */
	 //	
	 
	 public static double kP = 0.05;
	 	//							kP   kI   kD   kF               Iz    PeakOut */
	 public final static Gains kGains_Velocit  = new Gains( 0.05, 0.001, 5, 1023.0/20660.0,  300,  1.00);
}