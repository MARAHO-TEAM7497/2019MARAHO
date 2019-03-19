package frc.robot;


import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.System.Chassis;

import frc.System.NavxGyro;
import frc.System.Panel;
import frc.System.Ramp;
import frc.System.Stick;


public class Robot extends TimedRobot {
  private Compressor com;
 
  private Timer timer;
  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture("front",1);
    CameraServer.getInstance().startAutomaticCapture("ground",0);
    Chassis.Init();
    Panel.Init();
    Stick.Init();
    Ramp.Init();
    com = new Compressor();
    timer = new Timer();
    // NavxGyro.Init();

  }

  @Override
  public void autonomousInit() {
    
    Panel.autoInit();
    timer.reset();
    timer.start();
    // NavxGyro.resetRoll();
  
  }

  @Override
  public void autonomousPeriodic() {
    
    Ramp.Periodic();
    Stick.telop();
    Panel.autoPeriodic();
    Chassis.tankDrive();
    
    if(timer.get() < 15.0){
      com.start();
    }
    else {
      com.stop();
    }
    

  }

  @Override
  public void teleopInit() {

    Panel.teleoPeriodic();

  
  }

  @Override
  public void teleopPeriodic() {

    Stick.telop();
    Chassis.tankDrive();
    Panel.teleoPeriodic();
    Ramp.Periodic();
    // Vision.teleoP();

  }


}
