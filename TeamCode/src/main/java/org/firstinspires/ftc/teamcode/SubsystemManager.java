package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.RobotState;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.LiftSubsystem;

import java.util.LinkedList;
import java.util.Queue;

public class SubsystemManager {

    private States states;
    private final IntakeSubsystem intake;

    private final LiftSubsystem lift;
    private final Queue<States> stateQueue;

    private Action runningAction = null;
    private States cachedState = null;



    public SubsystemManager(HardwareMap hardwareMap){
        lift = new LiftSubsystem(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        stateQueue = new LinkedList<>();
    }

    public void scheduleState(States state){
        if (state == null ) return;
        stateQueue.add(state);
    }

    public void setStates(States state){
        if (state == null) return;
        stateQueue.clear();
        stateQueue.add(state);
    }

    public void periodic(TelemetryPacket telemetryPacket){
        if (stateQueue.isEmpty()) {
            scheduleState(States.TRAVEL);
        }

        States currentState = stateQueue.peek();

        if (cachedState != currentState){
            runningAction = null;
            cachedState = currentState;

            switch (currentState){
                case TRAVEL:
                    runningAction = null;
                    break;
                case INTAKE:
                    runningAction = new SequentialAction(
                            lift.lower(),
                            intake.take()
                    );
                    break;
                case RAISE:
                    runningAction = lift.raise();
                    break;
                case DROP:
                    runningAction = new SequentialAction(
                            intake.outtake()
                    );
                    break;
                case STOP:
                    runningAction = new ParallelAction(
                            lift.stop(),
                            intake.stop()
                    );
                    break;
            }
        }

        if (runningAction != null) {
            boolean isFinished = !runningAction.run(telemetryPacket);

            if (isFinished && stateQueue.size() > 1) {
                stateQueue.poll();
            }
        }

    }

    public boolean centerOfGravityHigh(){
        States current = stateQueue.peek();

        if (current == null ) current = cachedState;

        return current == States.RAISE;
    }

    public String getCurrentState() {
        States current = stateQueue.peek();
        if (current == null) {
            return "UNKNOWN";
        }
        return current.toString();
    }

    public IntakeSubsystem getIntake() {
        return intake;
    }

    public LiftSubsystem getLift() {
        return lift;
    }
}
