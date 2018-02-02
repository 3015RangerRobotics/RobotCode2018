package org.usfirst.frc.team3015.lib.android.messages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IncomingMessage extends CommMessage{
    private boolean isValid = false;
    private String type = "unknown";
    private String message = "{}";

    public IncomingMessage(String message){
        JSONParser parser = new JSONParser();
        try{
            JSONObject jo = (JSONObject) parser.parse(message);
            this.type = (String) jo.get("type");
            this.message = (String) jo.get("message");
            this.isValid = true;
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public boolean isValid(){
        return isValid;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
