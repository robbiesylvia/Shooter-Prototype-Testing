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
    public static int talonFirstChannel = 0;
    public static int talonSecondChannel = 1;
    public static int firstEncoderPort1 = 0;
    public static int firstEncoderPort2 = 1;
    public static int secondEncoderPort1 = 2;
    public static int secondEncoderPort2 = 3;
    public static double kP = 0.001;
    public static int timesExecuted = 0;
    public static double targetVoltage = 6;
    //change targetVoltage manually for testing
}
