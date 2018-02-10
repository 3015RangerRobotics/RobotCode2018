package org.usfirst.frc.team3015.motionProfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.usfirst.frc.team3015.lib.android.AndroidServer;

public class MotionProfiles {
	public static double[][] generate1D(double d, double maxV, double a, double period){
		return AndroidServer.getInstance().generateMotion1D(d, maxV, a, period);
	}
	
	public static double[][] loadProfile(String profileName){
		double[][] profile = new double[][] {};
		try {
			ArrayList<double[]> points = new ArrayList<double[]>();
			BufferedReader br = new BufferedReader(new InputStreamReader(MotionProfiles.class.getResourceAsStream(profileName + ".txt")));
			
			String line = "";
			while((line = br.readLine()) != null) {
				String[] pointString = line.split(",");
				double[] point = new double[3];
				for(int i = 0; i < 3; i++) {
					point[i] = Double.parseDouble(pointString[i]);
				}
				points.add(point);
			}
			profile = new double[points.size()][3];
			for(int i = 0; i < points.size(); i++) {
				profile[i] = points.get(i);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return profile;		
	}
}
