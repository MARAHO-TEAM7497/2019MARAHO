/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Uses the CameraServer class to automatically capture video from a USB webcam
 * and send it to the FRC dashboard without doing any vision processing. This
 * is the easiest way to get camera images to the dashboard. Just add this to
 * the robotInit() method in your program.
 */ 
public class Robot extends TimedRobot {
  static UsbCamera Camera1,camera2;
  static DifferentialDrive Robot;
  private static XboxController stick;
  private static WPI_VictorSPX leftfrontMotor;
  private static WPI_VictorSPX rightfrontMotor;
  @Override
  public void robotInit() {
    stick = new XboxController(0);
    Robot = new DifferentialDrive(leftfrontMotor, rightfrontMotor);
    leftfrontMotor = new WPI_VictorSPX(0);
    rightfrontMotor = new WPI_VictorSPX(1);
  }
  @Override
  public void autonomousInit(){
    
  }
  @Override 
  public void autonomousPeriodic(){

  }
  @Override
  public void teleopInit(){

  }
  @Override
  public void teleopPeriodic(){
    Robot.tankDrive(stick.getY(Hand.kLeft),stick.getY(Hand.kRight));
  }
}

