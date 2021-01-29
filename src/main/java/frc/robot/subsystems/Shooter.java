package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private Talon firstMotor = new Talon(Constants.talonFirstChannel);
    private Talon secondMotor = new Talon(Constants.talonSecondChannel);
    
    private double firstRPM;
    private double firstCurrentVoltage;
    private double secondRPM;
    private double secondCurrentVoltage;
    public static boolean isFirstRPMGood = false;
    public static boolean isSecondRPMGood = false;

    private final Encoder firstEncoder = new Encoder(Constants.firstEncoderPort1, Constants.firstEncoderPort2);
    private final Encoder secondEncoder = new Encoder(Constants.secondEncoderPort1, Constants.secondEncoderPort2);
  
    public Shooter() {
      firstEncoder.setDistancePerPulse(1./512.);
      firstEncoder.setMaxPeriod(.1);
      firstEncoder.setMinRate(1);
      firstEncoder.setSamplesToAverage(5);
      secondEncoder.setDistancePerPulse(1./512.);
      secondEncoder.setMaxPeriod(.1);
      secondEncoder.setMinRate(1);
      secondEncoder.setSamplesToAverage(5);
  }
  @Override
  public void periodic() {
      // Assuming no wheel slip, the difference in encoder distances is proportional to the heading error
      double firstError = firstRPM - (firstEncoder.getRate() * 60);
      firstCurrentVoltage += firstError * Constants.kP;
      double secondError = secondRPM - (secondEncoder.getRate() * 60);
      secondCurrentVoltage += secondError * Constants.kP;
        setRPM(firstRPM, secondRPM);
        setRPM(firstRPM, secondRPM);
        if(firstError < 50){
            isFirstRPMGood = true;
        }   
        if(secondError < 50){
            isSecondRPMGood = true;
        }
  }
    


  public void setRPM(double firstRPM, double secondRPM) {
      this.firstRPM = firstRPM;
      this.secondRPM = secondRPM;
      firstMotor.setVoltage(firstCurrentVoltage);
      secondMotor.setVoltage(secondCurrentVoltage);
  }
}