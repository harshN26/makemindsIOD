package org.firstinspires.ftc.teamcode;

import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

//TODO: Update all values, etc. THIS IS THE MOST IMPORTANT CLASS
@Config
public class Globals {


    //TODO:Do f, then do p, then d, understand what each constant does. I is lowk not that important for FTC so do that last
    public static double slidekP=0.0;
    public static double slidekI=0.0;
    public static double slidekD=0.0;
    public static double slidekF=0.0;

    public static double lift1TargetPos;

    public static double lift1PosSave;


    public static double intakeSlideServoInitPos=0.0;
    public static double intakeSlideTransferPos=0.0;
    public static double intakeSlideMax=0.0;

    public static double intakeWristInitPos=0.0;
    public static double intakeWristTransferPos=0.0;
    public static double intakeWristPickupPos=0.0;

    public static double intakeClawInitPos=0.0;
    public static double intakeClawOpenPos=0.0;
    public static double intakeClawClosePos=0.0;

    public static double outtakeClawInitPos=0.0;
    public static double outtakeClawOpenPos=0.0;
    public static double outtakeClawClosePos=0.0;

    public static double outtakeArmInitPos=0.0;
    public static double outtakeArmTransferPos=0.0;
    public static double outtakeArmBasketPos=0.0;
    public static double outtakeArmChamberReadyPos=0.0;

    public static double outtakeArmChamberPlacePos=0.0;

    public static double outtakeArmSpecimenPickupPos=0.0;
    public static double outtakeArmReset=0.0; //up in line with slides


    public static double lowBasketHeight=0.0; //encoder position
    public static double highBasketHeight=0.0; //encoder position
    public static double lowChamberHeight=0.0; //encoder position
    public static double highChamberHeight=0.0; //encoder position
    public static double lift1TransferPos=0.0; //encoder position
    public static double lift1InitPos=0.0; //encoder position

    public static String liftLeftMName="leftSlide";
    public static String liftRightMName="rightSlide";

    public static String inClawName="name";
    public static String outClawName="name";
    public static String inWristName="name";
    public static String outArmLName="name";
    public static String outArmRName="name";
    public static String inSlideName="name";




    //starting poses
    public static Pose2d redRightPoseAuto = new Pose2d(0,0);
    public static Pose2d redLeftPoseAuto = new Pose2d(0,0);
    public static Pose2d blueRightPoseAuto = new Pose2d(0,0);
    public static Pose2d blueLeftPoseAuto = new Pose2d(0,0);


    //game poses
    public static Pose2d specimenIntake = new Pose2d(0,0);
    public static Pose2d basketOuttake = new Pose2d(0,0);

    //saves last pose2d so that teleop isnt lost
    public static Pose2d autoPoseSave;








    public static gameState gamePeriod;

    public static RobotState robotState;

    public static rrTELEOPPATHS rrPathSelected;

    public enum driverState{ROADRUNNER_CONTROLLED,DRIVER_CONTROLLED}
    public enum RobotState{INTAKING,TRANSFERING, SPECIMEN_STUFF,SAMPLE_OUTTAKING}

    public enum rrTELEOPPATHS{PATH1,PATH2}//TODO: add more paths
    public enum gameState{AUTONOMOUS,TELEOP}
}
