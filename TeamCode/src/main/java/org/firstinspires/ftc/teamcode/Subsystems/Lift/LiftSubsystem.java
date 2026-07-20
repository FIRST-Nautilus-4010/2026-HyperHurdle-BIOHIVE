package org.firstinspires.ftc.teamcode.Subsystems.Lift;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Lift.Actions.Lower;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.Actions.Raise;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.Actions.StopLift;

public class LiftSubsystem {

    public final LiftIO io;

    public final HardwareMap hardwareMap;

    public LiftSubsystem(HardwareMap hardwareMap) {

        this.hardwareMap = hardwareMap;

        io = new LiftIO(hardwareMap);
    }

    public Action raise() { return new Raise(io); }

    public Action lower() { return new Lower(io); }

    public Action stop() { return new StopLift(io); }

    public double getLiftPositionRadians() {
        return io.getLiftPosition();
    }

    public double getTargetPositionRadians(){
        return io.getTargetPosition();
    }
}