package org.usfirst.frc.team3015.lib.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.usfirst.frc.team3015.lib.android.messages.CommMessage;
import org.usfirst.frc.team3015.lib.android.messages.HeartbeatMessage;
import org.usfirst.frc.team3015.lib.android.messages.IncomingMessage;
import org.usfirst.frc.team3015.lib.android.messages.MotionProfileMessage;
import org.usfirst.frc.team3015.lib.android.messages.TargetUpdateReceiver;
import org.usfirst.frc.team3015.lib.android.messages.VideoMessage;
import org.usfirst.frc.team3015.lib.util.Threaded;

public class AndroidServer extends Threaded {
    private static final int serverPort = 3015;
    private static AndroidServer instance = null;
    private ServerSocket serverSocket;
    private boolean isRunning = true;
    private ArrayList<TargetUpdateReceiver> receivers = new ArrayList<>();
    private double lastMessageReceivedTime = 0;
    private ArrayList<ServerThread> serverThreads = new ArrayList<>();
    private boolean isConnected = false;
    private volatile double[][] currentProfile = null;

    public static AndroidServer getInstance(){
        if(instance == null){
            instance = new AndroidServer(serverPort);
        }
        return instance;
    }

    private AndroidServer(int port){
        try {
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            e.printStackTrace();
        }
        new Thread(this).start();
        new Thread(new AppMaintenance()).start();
    }

    public void addTargetUpdateReceiver(TargetUpdateReceiver receiver){
        if(!receivers.contains(receiver)){
            receivers.add(receiver);
        }
    }

    public void removeTargetUpdateReceiver(TargetUpdateReceiver receiver){
        if(receivers.contains(receiver)){
            receivers.remove(receiver);
        }
    }

    public void requestStartRecording(String videoName){
        for(ServerThread s:serverThreads){
            if(s.isAlive()){
                s.send(new VideoMessage(true, videoName));
            }
        }
    }

    public void requestStopRecording(){
        for(ServerThread s:serverThreads){
            if(s.isAlive()){
                s.send(new VideoMessage(false));
            }
        }
    }
    
    public double[][] generateMotion1D(double d, double maxV, double a, double period) {
    	currentProfile = null;
    	for(ServerThread s:serverThreads) {
    		if(s.isAlive()) {
    			s.send(new MotionProfileMessage(d, maxV, a, period));
    		}
    	}
    	
    	while(currentProfile == null) {
    		try {
    			Thread.sleep(2);
//    			System.out.println("wait");
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	return currentProfile;
    }

    public boolean isConnected(){
        return isConnected;
    }

    @Override
    public void runThreaded() {
        while (isRunning){
            try{
                System.out.println("Android Server: Waiting for connection.");
                Socket client = serverSocket.accept();
                ServerThread s = new ServerThread(client);
                new Thread(s).start();
                serverThreads.add(s);
                System.out.println("Android Server: Connected.");
            }catch (IOException e){
                System.err.println("Android Server: Error accepting socket connection.");
            }finally {
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private class ServerThread extends Threaded {
        private Socket socket;

        public ServerThread(Socket socket){
            this.socket = socket;
        }

        public void send(CommMessage message){
            String toSend = message.toJson() + "\n";
            if(socket != null && socket.isConnected()){
                try{
                    OutputStream os = socket.getOutputStream();
                    os.write(toSend.getBytes());
                }catch (IOException e){
                    System.err.println("AndroidServer: Could not send data to socket");
                }
            }
        }

        public void handleMessage(CommMessage message){
            //handle message types
            if(message.getType().equals("targets")){
                TargetUpdate update = TargetUpdate.generateFromJsonString(message.getMessage());
                receivers.removeAll(Collections.singleton(null));
                if(update.isValid()){
                    for (TargetUpdateReceiver receiver:receivers){
                        receiver.onUpdateReceived(update);
                    }
                }
            }else if(message.getType().equals("heartbeat")){
                send(HeartbeatMessage.getInstance());
            }else if(message.getType().equals("motion")) {
            	JSONParser parser = new JSONParser();
            	try {
					JSONArray array = (JSONArray) parser.parse(message.getMessage());
					double[][] test = new double[array.size()][3];
					for(int i = 0; i < array.size(); i++) {
						JSONArray array2 = (JSONArray) array.get(i);
						for(int j = 0; j < array2.size(); j++) {
							Object o = array2.get(j);
							if(o instanceof Long) {
								test[i][j] = ((Long) o).doubleValue();
							}else {
								test[i][j] = (double) o;
							}
						}
					}
					currentProfile = test;
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        }

        public boolean isAlive(){
            return socket != null && socket.isConnected() && !socket.isClosed();
        }

        @Override
        public void runThreaded() {
            if(socket == null) return;
            try{
                InputStream is = socket.getInputStream();
                byte[] buffer = new byte[32768];
                int read;
                while(socket.isConnected() && (read = is.read(buffer)) != -1){
                    lastMessageReceivedTime = getTimestamp();
                    String messageRaw = new String(buffer, 0, read);
                    String[] messages = messageRaw.split("\n");
                    for(String message:messages){
                        IncomingMessage parsedMessage = new IncomingMessage(message);
                        if(parsedMessage.isValid()){
                            handleMessage(parsedMessage);
                        }
                    }
                }
                System.out.println("Android Server: Disconnected");
            }catch (IOException e){
                System.err.println("AndroidServer: Could not communicate with socket: " + socket);
            }
        }
    }

    private class AppMaintenance extends Threaded {
        @Override
        public void runThreaded() {
            while(isRunning){
                if(getTimestamp() - lastMessageReceivedTime > 500){
                    //phone disconnected
                    isConnected = false;
                }else{
                    isConnected = true;
                }

                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private double getTimestamp(){
        return System.currentTimeMillis();
    }
}
