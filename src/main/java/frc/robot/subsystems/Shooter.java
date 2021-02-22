package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.BaseTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//manually change the targetVoltage in Constants.java

public class Shooter extends SubsystemBase {

  public BaseTalon firstMotor = new TalonSRX(Constants.talonFirstChannel);
  double currentSensorVelocity;
  double currentRPM;
  public static double currentPercentOutput;
  public double getVelocityError (BaseTalon baseTalon, double targetPercentOutput) {
    currentSensorVelocity = baseTalon.getSelectedSensorVelocity();
    System.out.println("Current Sensory Velocity (raw output): " + currentSensorVelocity);

    currentRPM = (currentSensorVelocity * 10 * 60)/2048;
    // converts to RPM, 100 ms * 10 = 1 second, * 60 = 1 minute, /2048 because 2048 pulses/revolution
    
    currentPercentOutput = currentRPM / 6380;
    //6380 RPM is approx. max output (does not have to be exact)

    double errorPercentOutput = targetPercentOutput - currentPercentOutput;
    return errorPercentOutput; 
   }

   public void PIDControl (BaseTalon baseTalon, double targetPercentOutput) {
    double errorRPM = getVelocityError(baseTalon, targetPercentOutput);
    double gainsPercentOutput = (errorRPM) * Constants.kP;
    double newPercentOutput = currentPercentOutput + gainsPercentOutput;

    //failsafe
    if (newPercentOutput > 1 || newPercentOutput < -1) {
      firstMotor.set(ControlMode.PercentOutput, 0);
      System.out.println("Failsafe activated because percent output was set to a value greater than 1 or less than -1. Something is not right here!");

    }else{
    firstMotor.set(ControlMode.PercentOutput, newPercentOutput);
    currentPercentOutput = newPercentOutput; 
    System.out.println("Current percent output =" + currentPercentOutput);
    }
   } 
 }  
 
 
 
 // WPI_TalonFX --- helpful for velocity control etc.? ,..... also be aware of phoenix code, they say 2000 but use 500 for dimensional analysis of velocity stuff

  //use motion magic to achieve desired velocity, then use velocity closed loop to maintain velocity
  