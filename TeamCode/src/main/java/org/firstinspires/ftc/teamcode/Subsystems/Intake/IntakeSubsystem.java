package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Intake.Actions.Outtake;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.Actions.StopIntake;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.Actions.Take;

public class IntakeSubsystem {

    public final IntakeIO io;

    public final HardwareMap hardwareMap;

    public IntakeSubsystem(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;

        io = new IntakeIO(hardwareMap);
    }

    public Action take() { return new Take(io); }

    public Action outtake() { return new Outtake(io); }

    public Action stop() { return new StopIntake(io); }

    public double getIntakeVelRpm() {
        return io.getIntakeVel();
    }

    public double getTargetVelocityRpm(){
        return io.getTargetVelocity();
    }

}