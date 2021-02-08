package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

//manually change the targetVoltage in Constants.java

public class Shooter extends SubsystemBase {

  public TalonFX firstMotor = new TalonFX(Constants.talonFirstChannel);

// (for later use with PID control, maybe...)
   public double getVelocityError (TalonFX talonFX, int RPM) {
    double sensorVelocity = talonFX.getSelectedSensorVelocity(Constants.kPIDLoopIdx);
    // ^^^ fix to convert to RPM
    talonFX.set(ControlMode.PercentOutput, 0.5);
    double velocityError = RPM - sensorVelocity;
    return velocityError;
   }
  WPI_TalonFX // helpful for velocity control etc.? ,..... also be aware of phoenix code, they say 2000 but use 500 for dimensional analysis of velocity stuff

  //use motion magic to achieve desired velocity, then use velocity closed loop to maintain velocity
  
  
  
    //public TalonFX secondMotor = new TalonFX(Constants.talonSecondChannel);

    public double firstRPM = 200;
    public double firstCurrentVoltage;
    //public double secondRPM = 200;
    //public double secondCurrentVoltage;
    public static boolean isFirstRPMGood = false;
    public static boolean isSecondRPMGood = false;

    //public final static Encoder firstEncoder = new Encoder(Constants.firstEncoderPort1, Constants.firstEncoderPort2);
    //public final Encoder secondEncoder = new Encoder(Constants.secondEncoderPort1, Constants.secondEncoderPort2);
  
    //configures encoders, 2048 counts per revolution = 512 pulses per revolution, so multiply pulses by 1/512 to get rotations per second
    public Shooter() {}
      /* firstEncoder.setDistancePerPulse(1./512.);
      firstEncoder.setMaxPeriod(1);
      firstEncoder.setMinRate(1);
      firstEncoder.setSamplesToAverage(5);
      secondEncoder.setDistancePerPulse(1./512.);
      secondEncoder.setMaxPeriod(0.1);
      secondEncoder.setMinRate(10);
      secondEncoder.setSamplesToAverage(5);*/
  //}
  //public static double getRate() {
   // return firstEncoder.getRate();
  //}
  /* sets voltage of each motor, switched to voltage from RPM for simplicity
  public void setVoltage() {
      firstMotor.setVoltage(firstCurrentVoltage);
      secondMotor.setVoltage(secondCurrentVoltage);
    }*/
}