package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Chassis {
    //馬達的ID
    private static final byte L_FrontMotor_ID = 0; 
	private static final byte L_RearMotor_ID = 1;
	private static final byte R_FrontMotor_ID = 2;
    private static final byte R_RearMotor_ID = 3;
    
    //生成VictorSPX & WPI_VictorSPX 物件
    private static VictorSPX  L_RearMotor, R_RearMotor;
    private static WPI_VictorSPX L_FrontMotor, R_FrontMotor;
    
    //生成分開控制物件
    private static DifferentialDrive m_Robot;
    //是否一鍵直走
    private static  boolean arcaded= false,slowed= false;
    public static void Init(){
        L_FrontMotor = new WPI_VictorSPX(L_FrontMotor_ID);
        L_RearMotor = new VictorSPX(L_RearMotor_ID);
        R_FrontMotor = new WPI_VictorSPX(R_FrontMotor_ID);
        R_RearMotor = new VictorSPX(R_RearMotor_ID);
        //DifferentialDrive左邊是左邊右邊是右邊
        m_Robot = new DifferentialDrive(L_FrontMotor, R_FrontMotor);
        //決定正轉還是反轉
        //目前是不相反
        L_FrontMotor.setInverted(true);
		L_RearMotor.setInverted(true);
		R_FrontMotor.setInverted(true);
        R_RearMotor.setInverted(true);

        L_RearMotor.setNeutralMode(NeutralMode.Brake);
        L_FrontMotor.setNeutralMode(NeutralMode.Brake);
        R_RearMotor.setNeutralMode(NeutralMode.Brake);
        R_FrontMotor.setNeutralMode(NeutralMode.Brake);

        L_RearMotor.set(ControlMode.Follower, L_FrontMotor_ID);
		R_RearMotor.set(ControlMode.Follower, R_FrontMotor_ID);  
    }
    //這段code仍然需要測試到底可不可行
    public static void tankDrive(){

        if(Stick.y){
            arcaded =! arcaded;
        }
        if(Stick.a) {
            slowed =! slowed;
        }
        if(arcaded){
            System.out.println(0);
            // m_Robot.arcadeDrive(0.5 ,NavxGyro.getAngle()*0.01);
        }

        if(slowed){
            m_Robot.tankDrive(Stick.ly/2, Stick.ry/2);
        }
        else {
            m_Robot.tankDrive(Stick.ly*0.8, Stick.ry*0.8);        
        }

    
    }
    
}