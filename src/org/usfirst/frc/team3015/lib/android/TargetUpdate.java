package org.usfirst.frc.team3015.lib.android;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TargetUpdate {
    private boolean isValid = false;
    private List<TargetInfo> targets;
    private static JSONParser parser = new JSONParser();

    private static Optional<Double> parseDouble(JSONObject jo, String key) throws ClassCastException{
        Object d = jo.get(key);
        if(d == null){
            return Optional.empty();
        }else{
            return Optional.of((double) d);
        }
    }

    public static TargetUpdate generateFromJsonString(String updateString){
        TargetUpdate update = new TargetUpdate();
        try{
            JSONObject jo = (JSONObject) parser.parse(updateString);
            JSONArray targets = (JSONArray) jo.get("targets");
            ArrayList<TargetInfo> targetInfos = new ArrayList<>(targets.size());
            for(Object targetObj:targets){
                JSONObject target = (JSONObject) targetObj;
                Optional<Double> xAngle = parseDouble(target, "xAngle");
                Optional<Double> yAngle = parseDouble(target, "yAngle");
                Optional<Double> distance = parseDouble(target, "distance");
                if(xAngle.isPresent() && yAngle.isPresent() && distance.isPresent()){
                    targetInfos.add(new TargetInfo(xAngle.get(), yAngle.get(), distance.get()));
                }else{
                    update.isValid = false;
                    return update;
                }
            }
            update.targets = targetInfos;
            update.isValid = true;
        }catch (ParseException e){
            System.err.println("Parse error: " + e);
            System.err.println(updateString);
        }catch (ClassCastException e){
            System.err.println("Data type error: " + e);
            System.err.println(updateString);
        }
        return update;
    }

    public List<TargetInfo> getTargets(){
        return targets;
    }

    public boolean isValid(){
        return isValid;
    }
}
