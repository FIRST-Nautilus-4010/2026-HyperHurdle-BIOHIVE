package org.firstinspires.ftc.teamcode.Subsystems.Lift.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.LiftIO;

public class Raise implements Action {

    private final LiftIO liftIO;

    private boolean initialized = false;

    public Raise(LiftIO liftIO) {
        this.liftIO = liftIO;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        if (!initialized) {
            liftIO.setLiftPosition((int)Constants.LIFT_UP);
            initialized = true;
        }

        boolean isFinished = Math.abs(liftIO.getLiftPosition() - Constants.LIFT_UP) < 0.01;

        if (isFinished) {
            onEnd();
            return false;
        }

        return true;
    }

    public void onEnd(){}

}
