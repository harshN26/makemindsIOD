package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Globals;

public class Intake extends SubsystemBase {
    Servo slideServo;
    Servo wristServo;
    Servo inClawServo;

    public Intake(HardwareMap hardwareMap, String slideServoName, String wristServoName, String inClawServoName){
        slideServo= hardwareMap.get(Servo.class, slideServoName);
        wristServo= hardwareMap.get(Servo.class, wristServoName);
        inClawServo= hardwareMap.get(Servo.class, inClawServoName);
    }

    public void periodic(){
        //LEAVE EMPTY
    }
    public void initStuff(){
        slideIntakeSetNewPos(Globals.intakeSlideServoInitPos);
        wristServo.setPosition(Globals.intakeWristInitPos);
        inClawServo.setPosition(Globals.intakeClawInitPos);
    }
    public void callTransfer(){
        slideIntakeSetNewPos(Globals.intakeSlideTransferPos);
        wristServo.setPosition(Globals.intakeWristTransferPos);
        closeClaw();
    }

    public void closeClaw(){
        inClawServo.setPosition(Globals.intakeClawClosePos);
    }

    public void openClaw(){
        inClawServo.setPosition(Globals.intakeClawOpenPos);
    }


    public void intakeWristDown(){
        wristServo.setPosition(Globals.intakeWristPickupPos);
    }
    public void intakeWristUp(){
        wristServo.setPosition(Globals.intakeWristInitPos);
    }

    public void slideIntakeSetFull(){
        slideIntakeSetNewPos(Globals.intakeSlideMax);
    }

    public void slideIntakeSetNewPos(double newPos){
        slideServo.setPosition(Math.min(Globals.intakeSlideMax,Math.abs(newPos)));
    }
    public void reset(){
        slideServo.setPosition(Globals.intakeSlideServoInitPos);
        wristServo.setPosition(Globals.intakeWristInitPos);
        closeClaw();
    }



}
