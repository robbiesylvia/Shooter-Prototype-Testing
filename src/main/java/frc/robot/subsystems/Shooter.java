package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

//manually change the targetVoltage in Constants.java

public class Shooter extends SubsystemBase {

  public TalonFX firstMotor = new TalonFX(Constants.talonFirstChannel);
  double currentSensorVelocity;
  double currentRPM;
  double currentPercentOutput = 0.5;

   public double getVelocityError (TalonFX talonFX, double targetPercentOutput) {
    currentSensorVelocity = talonFX.getSelectedSensorVelocity(Constants.kPIDLoopIdx);
    currentRPM = (currentSensorVelocity * 10 * 60)/2048;
    // converts to RPM, 100 ms * 10 = 1 second, * 60 = 1 minute, /2048 because 2048 pulses/revolution
    double errorRPM = targetPercentOutput - currentRPM;
    return errorRPM; 
   }

   public void PIDControl (TalonFX talonFX, double targetPercentOutput) {
     double errorRPM = getVelocityError(talonFX, targetPercentOutput);
    double gainsPercentOutput = (errorRPM / 6380) * Constants.kP;
    double newPercentOutput = currentPercentOutput + gainsPercentOutput;
    //failsafe
    if (newPercentOutput > 1 || newPercentOutput < -1) {
      firstMotor.set(ControlMode.PercentOutput, 0.5);
      System.out.println("Failsafe activated because percent output was set to a value greater than 1 or less than -1. Something is not right here!");
    }else{
    firstMotor.set(ControlMode.PercentOutput, newPercentOutput);
    currentPercentOutput = newPercentOutput; 
    }
    System.out.println();
   } 
 }  // WPI_TalonFX --- helpful for velocity control etc.? ,..... also be aware of phoenix code, they say 2000 but use 500 for dimensional analysis of velocity stuff

  //use motion magic to achieve desired velocity, then use velocity closed loop to maintain velocity
  