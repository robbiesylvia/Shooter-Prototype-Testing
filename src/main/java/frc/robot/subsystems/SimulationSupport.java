//based on: https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java%20General/VelocityClosedLoop/src/main/java/frc/robot/sim/TalonSRXSimProfile.java#L12



package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Holds information about a simulated TalonSRX.
 */
class SimulationSupport extends SubsystemBase {
    private final TalonSRX talon;
    private final double accelToFullTime;
    private final double fullVel;
    private final boolean sensorPhase;

    /** The current position */
    private double pos = 0;
    /** The current velocity */
    private double vel = 0;

    /**
     * Creates a new simulation profile for a TalonSRX device.
     * 
     * @param talon
     *        The TalonSRX device
     * @param accelToFullTime
     *        The time the motor takes to accelerate from 0 to full, in seconds
     * @param fullVel
     *        The maximum motor velocity, in ticks per 100ms
     * @param sensorPhase
     *        The phase of the TalonSRX sensors
     */
    public SimulationSupport(final TalonSRX talon, final double accelToFullTime, final double fullVel, final boolean sensorPhase) {
        this.talon = talon;
        this.accelToFullTime = accelToFullTime;
        this.fullVel = fullVel;
        this.sensorPhase = sensorPhase;
    }

    /**
     * Runs the simulation profile.
     * 
     * This uses very rudimentary physics simulation and exists to allow users to test
     * features of our products in simulation using our examples out of the box.
     * Users may modify this to utilize more accurate physics simulation.
     */
    public void run() {
        final double period = getPeriod();
        final double accelAmount = fullVel / accelToFullTime * period / 1000;

        /// DEVICE SPEED SIMULATION

        double outPerc = talon.getMotorOutputPercent();
        if (sensorPhase) {
            outPerc *= -1;
        }
        // Calculate theoretical velocity with some randomness
        double theoreticalVel = outPerc * fullVel * random(0.95, 1);
        // Simulate motor load
        if (theoreticalVel > vel + accelAmount) {
            vel += accelAmount;
        }
        else if (theoreticalVel < vel - accelAmount) {
            vel -= accelAmount;
        }
        else {
            vel += 0.9 * (theoreticalVel - vel);
        }
        pos += vel * period / 100;

        /// SET SIM PHYSICS INPUTS

        talon.getSimCollection().addQuadraturePosition((int)(vel * period / 100));
        talon.getSimCollection().setQuadratureVelocity((int)vel);

        double supplyCurrent = Math.abs(outPerc) * 30 * random(0.95, 1.05);
        double statorCurrent = outPerc == 0 ? 0 : supplyCurrent / Math.abs(outPerc);
        talon.getSimCollection().setSupplyCurrent(supplyCurrent);
        talon.getSimCollection().setStatorCurrent(statorCurrent);

        talon.getSimCollection().setBusVoltage(12 - outPerc * outPerc * 3/4 * random(0.95, 1.05));
    }
}