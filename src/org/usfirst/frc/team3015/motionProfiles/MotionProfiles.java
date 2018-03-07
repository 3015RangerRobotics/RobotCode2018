package org.usfirst.frc.team3015.motionProfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.usfirst.frc.team3015.robot.Constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.FitMethod;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class MotionProfiles {
	public static double[][] generate1D(double d, double maxV, double a, double jerk, boolean reverse){
		Waypoint[] waypoints = new Waypoint[] {new Waypoint(0, 0, 0), new Waypoint(d, 0, 0)};
		Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, Constants.kPeriod, maxV, a, jerk);
		Trajectory trajectory = Pathfinder.generate(waypoints, config);
		double[][] profile = new double[trajectory.length()][3];
		for(int i = 0; i < trajectory.length(); i++) {
			Segment seg = trajectory.get(i);
			profile[i][0] = (reverse) ? -seg.position : seg.position;
			profile[i][1] = (reverse) ? -seg.velocity : seg.velocity;
			profile[i][2] = (reverse) ? -seg.acceleration : seg.velocity;
		}
		return profile;
	}
	
	public static HashMap<Side, double[][]> generate2D(double dx, double dy, double endAngle, double maxV, double a, double jerk, boolean reversed){
		Waypoint[] waypoints = new Waypoint[] {new Waypoint(0, 0, 0), new Waypoint(dx, dy, endAngle)};
		Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_LOW, Constants.kPeriod, maxV, a, jerk);
		Trajectory trajectory = Pathfinder.generate(waypoints, config);
		TankModifier modifier = new TankModifier(trajectory).modify(Constants.wheelBaseWidth);
		
		double[][] left = new double[trajectory.length()][3];
		double[][] right = new double[trajectory.length()][3];
		for(int i = 0; i < trajectory.length(); i++) {
			if(reversed) {
				left[i][0] = -modifier.getRightTrajectory().get(i).position;
				left[i][1] = -modifier.getRightTrajectory().get(i).velocity;
				left[i][2] = -modifier.getRightTrajectory().get(i).acceleration;
				right[i][0] = -modifier.getLeftTrajectory().get(i).position;
				right[i][1] = -modifier.getLeftTrajectory().get(i).velocity;
				right[i][2] = -modifier.getLeftTrajectory().get(i).acceleration;
			}else {
				left[i][0] = modifier.getLeftTrajectory().get(i).position;
				left[i][1] = modifier.getLeftTrajectory().get(i).velocity;
				left[i][2] = modifier.getLeftTrajectory().get(i).acceleration;
				right[i][0] = modifier.getRightTrajectory().get(i).position;
				right[i][1] = modifier.getRightTrajectory().get(i).velocity;
				right[i][2] = modifier.getRightTrajectory().get(i).acceleration;
			}
		}
		
		HashMap<Side, double[][]> map = new HashMap<Side, double[][]>();
		map.put(Side.kLeft, left);
		map.put(Side.kRight, right);
		
		return map;
	}
	
	public static HashMap<Side, double[][]> generateProfileToCube(double angle, double distance, double maxV, double a, double jerk){
		double x = Math.cos(angle) * distance;
		double y = Math.sin(angle) * distance;
		
		Waypoint[] waypoints = new Waypoint[] {new Waypoint(0, 0, 0), new Waypoint(x, y, 0)};
		Trajectory.Config config = new Trajectory.Config(FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_LOW, Constants.kPeriod, maxV, a, jerk);
		Trajectory trajectory = Pathfinder.generate(waypoints, config);
			
		TankModifier modifier = new TankModifier(trajectory).modify(Constants.wheelBaseWidth);
		double[][] leftProfile = new double[trajectory.length()][3];
		double[][] rightProfile = new double[trajectory.length()][3];
		
		for(int i = 0; i < trajectory.length(); i++) {
			Segment leftSeg = modifier.getLeftTrajectory().get(i);
			Segment rightSeg = modifier.getRightTrajectory().get(i);
			
			leftProfile[i][0] = leftSeg.position;
			leftProfile[i][1] = leftSeg.velocity;
			leftProfile[i][2] = leftSeg.acceleration;
			
			rightProfile[i][0] = rightSeg.position;
			rightProfile[i][1] = rightSeg.velocity;
			rightProfile[i][2] = rightSeg.acceleration;
		}
		
		HashMap<Side, double[][]> map = new HashMap<Side, double[][]>();
		map.put(Side.kLeft, leftProfile);
		map.put(Side.kRight, rightProfile);
		
		return map;
	}
	
	public static double[][] loadProfile(String profileName, boolean reversed){
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
				profile[i][0] = (reversed) ? -points.get(i)[0] : points.get(i)[0];
				profile[i][1] = (reversed) ? -points.get(i)[1] : points.get(i)[1];
				profile[i][2] = (reversed) ? -points.get(i)[2] : points.get(i)[2];
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return profile;		
	}
	
	public static enum Side{
		kLeft,
		kRight
	}
}
