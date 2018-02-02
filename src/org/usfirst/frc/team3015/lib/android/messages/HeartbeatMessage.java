package org.usfirst.frc.team3015.lib.android.messages;

public class HeartbeatMessage extends CommMessage{
    private static HeartbeatMessage instance = new HeartbeatMessage();

    public static HeartbeatMessage getInstance(){
        return instance;
    }

    @Override
    public String getType() {
        return "heartbeat";
    }

    @Override
    public String getMessage() {
        return "{}";
    }
}
