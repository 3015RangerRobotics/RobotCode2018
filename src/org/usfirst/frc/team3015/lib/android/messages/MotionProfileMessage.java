package org.usfirst.frc.team3015.lib.android.messages;

import org.json.simple.JSONObject;

public class MotionProfileMessage extends CommMessage{
	private double d;
	private double maxV;
	private double a;
	private double period;
	
	public MotionProfileMessage(double d, double maxV, double a, double period) {
		this.d = d;
		this.maxV = maxV;
		this.a = a;
		this.period = period;
	}

	@Override
	public String getType() {
		return "motion1D";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getMessage() {
		JSONObject jo = new JSONObject();
		jo.put("d", d);
		jo.put("maxV", maxV);
		jo.put("a", a);
		jo.put("period", period);
		return jo.toJSONString();
	}

}
