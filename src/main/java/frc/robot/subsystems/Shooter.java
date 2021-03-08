package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.controller.PIDController;


//manually change the targetVoltage in Constants.java


public class Shooter extends SubsystemBase {
  public final TalonFX firstMotor;
  public final CANSparkMax hoodMotor;
  

  public Shooter(){ 
  firstMotor = new TalonFX(Constants.talonFirstChannel);
  hoodMotor = new CANSparkMax(Constants.deviceIDCANSparkMax, MotorType.kBrushless);
  PIDController hoodMotorPIDController = new PIDController(Constants.kP, Constants.kI, Constants.kD);

  }
  public void hoodMotorPIDControl(){
    
  
  }



  public AnalogPotentiometer pot = new AnalogPotentiometer(0, Constants.upperBoundPotentiometer, Constants.lowerBoundPotentiometer);

    public double getPotentiometerAngle(){
      return pot.get() * (Constants.upperBoundPotentiometer - Constants.lowerBoundPotentiometer) + Constants.lowerBoundPotentiometer;
    }
  

    
  
    
  /*targetVelocity is in units/100ms and the integrated encoder is based on 2048 units/revolution, so to convert from targetRPM to targetVelocity, 
   *(targetRPM) / ((100ms per 1 second = 10) (sec per min = 60)) 
   */

   //factor in gear ratio? (with wheels) ~*~

  double targetVelocity;
  double currentSensorVelocity;
  double currentRPM;
  public static double currentPercentOutput;
  public double getVelocityError (TalonFX talonFX, double targetPercentOutput) {
    currentSensorVelocity = talonFX.getSelectedSensorVelocity();
    System.out.println("Current Sensory Velocity (raw output): " + currentSensorVelocity);

    currentRPM = (currentSensorVelocity * 10 * 60)/2048;
    // converts to RPM, 100 ms * 10 = 1 second, * 60 = 1 minute, /2048 because 2048 pulses/revolution
    double errorPercentOutput = targetPercentOutput - currentPercentOutput;
    return errorPercentOutput; 
   }

   public void PIDControl (TalonFX talonFX, double targetPercentOutput) {
     
    double errorRPM = getVelocityError(talonFX, targetPercentOutput);
    double gainsPercentOutput = (errorRPM) * Constants.kP;
    double newPercentOutput = currentPercentOutput + gainsPercentOutput;

    //failsafe
    if (newPercentOutput > 1 || newPercentOutput < -1) {
      firstMotor.set(ControlMode.PercentOutput, 0);
      System.out.println("Failsafe activated because percent output was set to a value greater than 1 or less than -1. Something is not right here!");

    }else{
    firstMotor.set(ControlMode.Velocity, targetVelocity);
    currentPercentOutput = newPercentOutput; 
    System.out.println("Current RPM =" + currentPercentOutput);
    }
   } 
 }  
 
 
 
 // WPI_TalonFX --- helpful for velocity control etc.? ,..... also be aware of phoenix code, they say 2000 but use 500 for dimensional analysis of velocity stuff

  //use motion magic to achieve desired velocity, then use velocity closed loop to maintain velocity
  