package org.usfirst.frc.team3015.robot;

import java.util.ArrayList;

public class MotionProfiles {
	public static double[][] exampleProfile = new double[][] {
		//Motion profile here {goalPosition, goalVelocity, goalAcceleration}
	};
	
	public static double[][] generate1D(double d, double maxV, double a, double period) {
		ArrayList<double[]> points = new ArrayList<double[]>();
		points.add(new double[] {0, 0, 0});
		int state = 0;//0 = accel, 1 = maxV, 2 = decel
		double lastPos = 0;
		double lastVel = 0;
		boolean done = false;
		double vT;
		double pT;
		while(!done) {
			switch(state) {
				case 0:
					vT = lastVel + (a * period);
					pT = lastPos + (lastVel * period) + (0.5 * a * (period*period));
					
					if(vT >= maxV) {
						vT = maxV;
						state = 1;//maxV
						points.add(new double[] {pT, vT, 0});
						lastPos = pT;
						lastVel = vT;
						break;
					}
					
					if(pT <= d/2 + (lastVel * period) && pT >= d/2 - (lastVel * period)) {
						state = 2;//decel
						points.add(new double[] {pT, vT, 0});
						lastPos = pT;
						lastVel = vT;
						break;
					}
					
					points.add(new double[] {pT, vT, a});
					lastPos = pT;
					lastVel = vT;
					break;
				case 1:
					vT = maxV;
					pT = lastPos + (lastVel * period);
					
					double vToA = vT / a;
					double pVoA = lastPos + (lastVel * vToA) + (0.5 * a * (vToA * vToA));
					if(pVoA <= d - pT + (maxV * period) && pVoA >= d - pT - (maxV * period)) {
						state = 2;//decel
						points.add(new double[] {pT, vT, 0});
						lastPos = pT;
						lastVel = vT;
						break;
					}
					
					points.add(new double[] {pT, vT, 0});
					lastPos = pT;
					lastVel = vT;
					break;
				case 2:
					vT = lastVel + (-a * period);
					pT = lastPos + (lastVel * period) + (0.5 * -a * (period*period));
					
					if(pT <= d + (lastVel * period) && pT >= d - (lastVel * period)) {
						points.add(new double[] {pT, vT, 0});
						done = true;//stop
						break;
					}
					
					points.add(new double[] {pT, vT, -a});
					lastPos = pT;
					lastVel = vT;
					break;
			}
		}
		points.add(new double[] {d, 0, 0});
		double[][] ret = new double[points.size()][3];
		for(int i = 0; i < points.size(); i++) {
			ret[i] = points.get(i);
		}
		return ret;
	}
}
