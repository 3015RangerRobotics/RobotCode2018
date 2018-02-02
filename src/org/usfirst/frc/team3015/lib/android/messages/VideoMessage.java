package org.usfirst.frc.team3015.lib.android.messages;

public class VideoMessage extends CommMessage{
    private boolean recording;
    private String name;

    public VideoMessage(boolean recording){
        this.recording = recording;
    }

    public VideoMessage(boolean recording, String name){
        this.recording = recording;
        this.name = name;
    }

    @Override
    public String getType() {
        return "video";
    }

    @Override
    public String getMessage() {
        if(recording){
            return "start-" + name;
        }else{
            return "stop";
        }
    }
}
