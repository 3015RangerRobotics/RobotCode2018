package org.usfirst.frc.team3015.lib.android.messages;

import org.json.simple.JSONObject;

public abstract class CommMessage {
    public abstract String getType();
    public abstract String getMessage();
    public String toJson(){
        JSONObject jo = new JSONObject();
        jo.put("type", getType());
        jo.put("message", getMessage());
        return jo.toString();
    }
}
