package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Chassis {


    //馬達的ID
    private static final byte L_FrontMotor_ID = 0; 
	private static final byte L_RearMotor_ID = 1;
	private static final byte R_FrontMotor_ID = 2;
    private static final byte R_RearMotor_ID = 3;

    private static Init speed = 1;
    
    //生成VictorSPX & WPI_VictorSPX 物件
    private static VictorSPX  L_RearMotor, R_RearMotor;
    private static WPI_VictorSPX L_FrontMotor, R_FrontMotor;
    
    //生成分開控制物件
    private static DifferentialDrive m_Robot;
    //是否一鍵直走
    private static  boolean isSlow= true,isArcade = false;
    public static void Init(){
        L_FrontMotor = new WPI_VictorSPX(L_FrontMotor_ID);
        L_RearMotor = new VictorSPX(L_RearMotor_ID);
        R_FrontMotor = new WPI_VictorSPX(R_FrontMotor_ID);
        R_RearMotor = new VictorSPX(R_RearMotor_ID);
        //DifferentialDrive 左邊是左邊 右邊是右邊
        m_Robot = new DifferentialDrive(L_FrontMotor, R_FrontMotor);
        //決定正轉還是反轉
        //目前是相反
        L_FrontMotor.setInverted(false);
		L_RearMotor.setInverted(false);
		R_FrontMotor.setInverted(false);
        R_RearMotor.setInverted(false);

        L_RearMotor.setNeutralMode(NeutralMode.Brake);
        L_FrontMotor.setNeutralMode(NeutralMode.Brake);
        R_RearMotor.setNeutralMode(NeutralMode.Brake);
        R_FrontMotor.setNeutralMode(NeutralMode.Brake);

        L_RearMotor.set(ControlMode.Follower, L_FrontMotor_ID);
		R_RearMotor.set(ControlMode.Follower, R_FrontMotor_ID);  
    }

    public static void PeriodicInit(){
        isSlow = true;
        // if (isArcade != false || isSlow != false){
        //     isArcade = false;
        //     isSlow = false;
         
        // }
        SmartDashboard.setDefaultBoolean("Chassis",true);
    }
    //這段code仍然需要測試到底可不可行
    public static void tankDrive(){
        
        //should task whether it can work or not.要使用需先去Stcik改狀態
        
        // if (Stick.y){
        //     isArcade =! isArcade;
        // }
        // else if (Stick.a){
        //     isSlow =! isSlow;    
        //     isArcade = false;
        // }

        // if(isArcade && Math.abs(Stick.ly+Stick.ry)/2 == 0){
            
        //     m_Robot.tankDrive(0.5,0.5);
            

        // }
        // else if(isSlow){

        //     m_Robot.tankDrive(Stick.ly*0.95, Stick.ry*0.95);     
        
        // }
        // else {
            
        //     m_Robot.tankDrive(Stick.ly/2, Stick.ry/2);

        // }
        if(Stick.y){
            m_Robot.tankDrive(0.5,0.5);
            SmartDashboard.putNumber("Chassis", 3);
        }  
        else{
            
            /*if(Stick.a) {
                isSlow =! isSlow;
            }
            if(isSlow){
                SmartDashboard.putNumber("Chassis", 2);
                m_Robot.tankDrive(Stick.ly/2, Stick.ry/2);
            }
            else {
                SmartDashboard.putNumber("Chassis", 1);
                m_Robot.tankDrive(Stick.ly*0.95, Stick.ry*0.95);        
            }*/

            if(Stick.a){
                speed = speed + 1;
            }
            if(speed==3){
                speed = speed - 2;
            }
            if(speed==1){
                SmartDashboard.putNumber("Chassis", 1);
                m_Robot.tankDrive(Stick.ly*0.95, Stick.ry*0.95);
            }
            else if(speed == 2){
                SmartDashboard.putNumber("Chassis", 2);
                m_Robot.tankDrive(Stick.ly/2, Stick.ry/2);
            }
        }
    }        
}

    //     if (isArcade){
    //         if(Stick.y){
    //             m_Robot.tankDrive(0.5,0.5);
              
    //     }

 
    


    




    
    
    
