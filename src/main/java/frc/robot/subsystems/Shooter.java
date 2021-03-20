package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.controller.PIDController;





//manually change the targetVoltage in Constants.java


public class Shooter extends SubsystemBase {
  public final TalonFX firstMotor;
  public final TalonFX secondMotor;


 
 public final CANSparkMax hoodMotor;


public PIDController hoodPIDController;
  

  public Shooter(){ 

  firstMotor = new TalonFX(Constants.talonFirstChannel);
  secondMotor = new TalonFX(Constants.talonSecondChannel);
  secondMotor.follow(firstMotor);
  secondMotor.setInverted(true);
  
  // 
  
  hoodMotor = new CANSparkMax(Constants.deviceIDCANSparkMax, CANSparkMaxLowLevel.MotorType.kBrushless);

  //intializing + configuring hoodPIDController
    hoodPIDController = new PIDController(Constants.hoodkP, Constants.kI, Constants.kD);
 
}
  public void hoodMotorPIDControl(){
    
  }



  public AnalogPotentiometer potentiometer = new AnalogPotentiometer(0, Constants.upperBoundPotentiometer - Constants.lowerBoundPotentiometer, 0);

    public double getPotentiometerAngle(){
      return potentiometer.get(); //* (Constants.upperBoundPotentiometer - Constants.lowerBoundPotentiometer) + Constants.lowerBoundPotentiometer;
    }
  
  public void setAngle(double targetAngle){
    
    hoodPIDController.setSetpoint(targetAngle);
    hoodMotor.set(hoodPIDController.calculate(getPotentiometerAngle()));
    if(calculateError() < 1.5){
      hoodMotor.disable();
    }

  }

  public double calculateError(){
    return(Math.abs(hoodPIDController.getSetpoint() - getPotentiometerAngle()));
  }

    
  
    
  /*targetVelocity is in units/100ms and the integrated encoder is based on 2048 units/revolution, so to convert from targetRPM to targetVelocity, 
   *(targetRPM) / ((100ms per 1 second = 10) (sec per min = 60)) 
   */

   //factor in gear ratio? (with wheels) ~*~

  double currentSensorVelocity;
  double currentRPM;

  //targetVelocity is in pulses/100 ms (as opposed to 2048 pulses/revoluion)
 

    
    //failsafe
    /*if (newPercentOutput > 1 || newPercentOutput < -1) {
      firstMotor.set(ControlMode.PercentOutput, 0);
      System.out.println("Failsafe activated because percent output was set to a value greater than 1 or less than -1. Something is not right here!");

     ** cert statement **

    }else{*/
  public void setRPM (double targetRPM){
    double targetVelocity = (targetRPM * 2048) / 600;
    
    firstMotor.set(TalonFXControlMode.Velocity, targetVelocity);
 

    	/* Configured for Velocity Closed Loop on Integrated Sensors' Sum and Arbitrary FeedForward on joyX */
			
			/* Uncomment to view RPM in Driver Station */
            // double actual_RPM = (_rightMaster.getSelectedSensorVelocity() / (double)Constants.kSensorUnitsPerRotation * 600f);
            // System.out.println("Vel[RPM]: " + actual_RPM + " Pos: " + _rightMaster.getSelectedSensorPosition());
  } 
}
 
 
 
 // WPI_TalonFX --- helpful for velocity control etc.? ,..... also be aware of phoenix code, they say 2000 but use 500 for dimensional analysis of velocity stuff

  //use motion magic to achieve desired velocity, then use velocity closed loop to maintain velocity
  