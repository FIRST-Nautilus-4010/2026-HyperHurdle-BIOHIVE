package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeIO {

    DcMotorEx intakeMotor;
    private double targetVelocity;

    public IntakeIO(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
        intakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }

    public void setIntakePwr(double power) {
        intakeMotor.setPower(power);
    }

    public void setIntakeVelRpm(double velocity) {

        intakeMotor.setVelocity(velocity);
        targetVelocity = velocity;
    }

    public double getIntakeVel() {
        return intakeMotor.getVelocity();
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }



}
