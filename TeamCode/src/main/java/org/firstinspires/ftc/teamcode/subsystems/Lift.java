package org.firstinspires.ftc.teamcode.subsystems;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.PIDEx;
import com.ThermalEquilibrium.homeostasis.Controllers.Feedforward.NoFeedforward;
import com.ThermalEquilibrium.homeostasis.Filters.Estimators.RawValue;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficientsEx;
import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Globals;

import java.util.function.DoubleSupplier;

@Config
public class Lift extends SubsystemBase {
    private DcMotor left_slide = null;
    private DcMotor right_slide = null;


   PIDFController pidfController;

    private double target_pos=0;
    private boolean atTargetPos=false;

    double tolerance;

    public Lift(final HardwareMap hardwareMap, String leftMotorName, String rightMotorName,double kP, double kI, double kD, double kF, double tol){
        left_slide = hardwareMap.get(DcMotor.class, leftMotorName);
        right_slide = hardwareMap.get(DcMotor.class, rightMotorName);

        left_slide.setDirection(DcMotor.Direction.REVERSE);


        right_slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        pidfController=new PIDFController(kP,kI,kD,kF);
        tolerance=tol;




    }

    public void  set_new_target_pos(double targetPos){
        this.target_pos=targetPos;
    }

    public void periodic(){
        double pos=right_slide.getCurrentPosition();
        double command = pidfController.calculate(pos);
        left_slide.setPower(command);
        right_slide.setPower(command);
        atTargetPos= pos >= (target_pos-tolerance)&&pos <=  (target_pos+tolerance);
        Globals.lift1PosSave=pos;
    }

    public void reset(){
        //TODO: figure out how to reset encoder after match has started
    }

    public boolean isFinished(){
        return atTargetPos;
    }

}
