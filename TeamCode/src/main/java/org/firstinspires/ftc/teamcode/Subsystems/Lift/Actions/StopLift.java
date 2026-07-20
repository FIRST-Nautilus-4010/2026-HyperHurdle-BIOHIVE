package org.firstinspires.ftc.teamcode.Subsystems.Lift.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeIO;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.LiftIO;

public class StopLift implements Action {
    private final LiftIO io;


    public StopLift(LiftIO io) {
        this.io = io;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        io.setLiftPwr(0);
        return false;
    }

}
