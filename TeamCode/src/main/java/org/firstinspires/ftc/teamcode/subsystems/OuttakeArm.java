package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Globals;

public class OuttakeArm extends SubsystemBase {
    Servo armLeft;
    Servo armRight;
    Servo outClawServo;

    public OuttakeArm(HardwareMap hardwareMap, String armLName, String armRName, String outClawServoName){
        armLeft= hardwareMap.get(Servo.class, armLName);
        armRight= hardwareMap.get(Servo.class, armRName);
        armLeft.setDirection(Servo.Direction.REVERSE);
        outClawServo= hardwareMap.get(Servo.class, outClawServoName);
    }

    public void periodic(){
        //LEAVE EMPTY
    }
    public void initStuff(){
       armLeft.setPosition(Globals.outtakeArmInitPos);
       armRight.setPosition(Globals.outtakeArmInitPos);
       outClawServo.setPosition(Globals.outtakeClawInitPos);
    }
    public void callTransfer(){
        armLeft.setPosition(Globals.outtakeArmTransferPos);
        armRight.setPosition(Globals.outtakeArmTransferPos);
        openClaw();
    }

    public void closeClaw(){
        outClawServo.setPosition(Globals.outtakeClawClosePos);
    }

    public void openClaw(){
        outClawServo.setPosition(Globals.outtakeClawOpenPos);
    }


    public void basket(){
        armLeft.setPosition(Globals.outtakeArmBasketPos);
        armRight.setPosition(Globals.outtakeArmBasketPos);
    }

    public void chamberReady(){
        armLeft.setPosition(Globals.outtakeArmChamberReadyPos);
        armRight.setPosition(Globals.outtakeArmChamberReadyPos);
    }

    public void chamberPlace(){
        armLeft.setPosition(Globals.outtakeArmChamberPlacePos);
        armRight.setPosition(Globals.outtakeArmChamberPlacePos);
    }
    public void specimenPickup(){
        armLeft.setPosition(Globals.outtakeArmSpecimenPickupPos);
        armRight.setPosition(Globals.outtakeArmSpecimenPickupPos);
        openClaw();
    }

    public void reset(){
        armLeft.setPosition(Globals.outtakeArmReset);
        armRight.setPosition(Globals.outtakeArmReset);
        closeClaw();
    }



}
