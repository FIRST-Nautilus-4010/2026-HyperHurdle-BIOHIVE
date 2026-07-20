package org.firstinspires.ftc.teamcode.Subsystems.Lift;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Constants;

public class LiftIO {

    DcMotorEx rightMotor, leftMotor;

    private int targetPosition;
    public LiftIO(HardwareMap hardwareMap) {

        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");

        rightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightMotor.setDirection(DcMotorEx.Direction.FORWARD);
        leftMotor.setDirection(DcMotorEx.Direction.REVERSE);

        PIDFCoefficients pidf = new PIDFCoefficients(Constants.LIFT_P, Constants.LIFT_I, Constants.LIFT_D, 0);
        rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);
        leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidf);

        resetEncoders();
    }

    public void setLiftPosition(int positionInRadians) {

        int targetTicks = (int) (positionInRadians / Constants.LIFT_CONVERTION_TO_RAD);

        targetPosition = targetTicks;

        rightMotor.setTargetPosition(targetTicks);
        leftMotor.setTargetPosition(targetTicks);

        rightMotor.setPower(1);
        rightMotor.setPower(1);
    }

    public double getLiftPosition() {
        double averageTicks = (rightMotor.getCurrentPosition() + leftMotor.getCurrentPosition()) / 2;
        return averageTicks * Constants.LIFT_CONVERTION_TO_RAD;
    }

    public void setLiftPwr(double power) {
        rightMotor.setPower(power);
        leftMotor.setPower(power);
    }

    public double getTargetPosition(){
        return targetPosition;
    }

    public void resetEncoders(){
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

}
