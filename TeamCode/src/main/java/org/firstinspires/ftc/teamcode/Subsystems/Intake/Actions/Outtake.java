package org.firstinspires.ftc.teamcode.Subsystems.Intake.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeIO;

public class Outtake implements Action {

    private final IntakeIO intakeIO;

    public Outtake(IntakeIO intakeIO) {
        this.intakeIO = intakeIO;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {


        intakeIO.setIntakeVelRpm(Constants.OUTAKE_VEL);

        return false;
    }

}

