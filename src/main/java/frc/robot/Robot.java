package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.System.Chassis;
import frc.System.NavxGyro;
import frc.System.Panel;
import frc.System.Ramp;
import frc.System.Stick;


public class Robot extends TimedRobot {

  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    Chassis.Init();
    NavxGyro.Init();
    Panel.Init();
    Stick.Init();
    Ramp.Init();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    Chassis.tankDrive();
    Panel.teleop();
    Stick.telop();
    Ramp.telop();
    
  }


}
