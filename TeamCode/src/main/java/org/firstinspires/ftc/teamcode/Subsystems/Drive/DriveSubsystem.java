package org.firstinspires.ftc.teamcode.Subsystems.Drive;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

public class DriveSubsystem {

    public final MecanumDrive mecanumDrive;
    public final DriveLimiter driveLimiter;

    private double desiredSpeed;
    private double detectedSpeed;

    private double desiredAccel;
    private double detectedAccel;




    public DriveSubsystem(HardwareMap hardwareMap, Pose2d startPose) {
        mecanumDrive = new MecanumDrive(hardwareMap, startPose);
        driveLimiter = new DriveLimiter();

    }

    public void Drive(double x, double y, double rx, boolean centerOfGravityHigh){

        double maxSpeed = centerOfGravityHigh ? Constants.SLOW_SPEED : Constants.DEFAULT_SPEED;
        double maxAccel = centerOfGravityHigh ? Constants.SLOW_ACCEL : Constants.DEFAULT_ACCEL;

        desiredSpeed = Math.hypot(x,y);
        desiredAccel = maxAccel;

        PoseVelocity2d targetVelocity = new PoseVelocity2d(
                new Vector2d(x * maxSpeed, y * maxSpeed),
                rx * maxSpeed
        );

        PoseVelocity2d safeVelocity = driveLimiter.limit(targetVelocity, maxAccel);
        detectedSpeed = Math.hypot(safeVelocity.linearVel.x, safeVelocity.linearVel.y);
        desiredAccel = maxAccel;

        mecanumDrive.setDrivePowers(safeVelocity);

    }

    public double getDesiredSpeed(){ return desiredSpeed; }
    public double getDetectedSpeed(){ return detectedSpeed; }
    public double getDesiredAccel(){ return desiredAccel; }
    public double getDetectedAccel(){ return detectedAccel; }

    public void update(){
        mecanumDrive.updatePoseEstimate();
    }
}