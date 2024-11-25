package org.firstinspires.ftc.teamcode.drive;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.TwoTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.Encoder;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    ^
 *    |
 *    | ( x direction)
 *    |
 *    v
 *    <----( y direction )---->

 *        (forward)
 *    /--------------\
 *    |     ____     |
 *    |     ----     |    <- Perpendicular Wheel
 *    |           || |
 *    |           || |    <- Parallel Wheel
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
public class TwoWheelTrackingLocalizer extends TwoTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 2000;
    public static double WHEEL_RADIUS = (24/25.4); // in

    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    //TODO: GET THIS INFORMATION, PARALLEL IS PARALLEL TO WHEELS, PERPENDICULAR RUNS THE OPPOSITE
    public static double PARALLEL_X = -2; // X is the up and down direction
    public static double PARALLEL_Y = -2; // Y is the strafe direction

    public static double PERPENDICULAR_X = 0;
    public static double PERPENDICULAR_Y = -6;

    //TODO: Tune these values by running LocalizationTest and then moving the robot in the desired
    //TODO: direction, the values should be the measured distance/telemetry distance
    public static double X_MULTIPLIER=1;
    public static double Y_MULTIPLIER=0.99413129809;

    // Parallel/Perpendicular to the forward axis
    // Parallel wheel is parallel to the forward axis
    // Perpendicular is perpendicular to the forward axis
    private Encoder parallelEncoder, perpendicularEncoder;

    private SampleMecanumDrive drive1;
    private SampleMecanumDriveCancelable drive2;

    public TwoWheelTrackingLocalizer(HardwareMap hardwareMap, SampleMecanumDrive drive) {
        super(Arrays.asList(
                new Pose2d(PARALLEL_X, PARALLEL_Y, 0),
                new Pose2d(PERPENDICULAR_X, PERPENDICULAR_Y, Math.toRadians(90))
        ));
        this.drive2=null;
        this.drive1 = drive;
        //TODO: fix enc names
        parallelEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "right_front_drive"));
        perpendicularEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "right_back_drive"));

        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
        parallelEncoder.setDirection(Encoder.Direction.REVERSE);
    }
    public TwoWheelTrackingLocalizer(HardwareMap hardwareMap, SampleMecanumDriveCancelable drive) {
        super(Arrays.asList(
                new Pose2d(PARALLEL_X, PARALLEL_Y, 0),
                new Pose2d(PERPENDICULAR_X, PERPENDICULAR_Y, Math.toRadians(90))
        ));
        this.drive1=null;
        this.drive2 = drive;

        //TODO: fix enc names
        parallelEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "right_front_drive"));
        perpendicularEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "right_back_drive"));

        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
        parallelEncoder.setDirection(Encoder.Direction.REVERSE);
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @Override
    public double getHeading() {
        if(drive2==null)
            return drive1.getRawExternalHeading();
        return drive2.getRawExternalHeading();
    }

    @Override
    public Double getHeadingVelocity() {
        if(drive2==null)
            return drive1.getExternalHeadingVelocity();
        return drive2.getExternalHeadingVelocity();
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(parallelEncoder.getCurrentPosition())*X_MULTIPLIER,
                encoderTicksToInches(perpendicularEncoder.getCurrentPosition())*Y_MULTIPLIER
        );
    }

    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        // TODO: If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method

        return Arrays.asList(
                encoderTicksToInches(parallelEncoder.getRawVelocity()),
                encoderTicksToInches(perpendicularEncoder.getRawVelocity())
        );
    }
}