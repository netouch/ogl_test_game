package test.com;

import test.com.UI.IUIMenuListener;
import test.com.UI.UIActiveArea;
import android.util.Log;
import android.view.MotionEvent;
import android.hardware.*;
import android.hardware.SensorManager;



public class Controller implements IUIMenuListener, SensorEventListener{
	private static final int X=0;
	private static final int Y=1;
	private static final int Z=2;
	boolean cameraFocus = true;
	boolean objectFocus = false;
	boolean movingRight = false;
	boolean movingLeft = false;
	boolean movingUp = false;
	boolean movingDown = false;
	float[] accelVect = new float[3];
	
	UIActiveArea left = null;
	UIActiveArea right = null;
	UIActiveArea top = null;
	UIActiveArea bottom = null;
	
	SensorManager sm;
    Sensor mAccelerometer;
	
	Camera cam;
	
	public Controller(){
	}
	
	public Controller(float width, float height){
		left = new UIActiveArea(0.0f , 0.0f , width/5 , height);
		right = new UIActiveArea(width - width/5 , 0.0f , width , height);
		top = new UIActiveArea(0.0f, 0.0f, width, height/5);
		bottom = new UIActiveArea(0.0f, height - height/5, width, height);
	}
	
	public void setupSensors(SensorManager s){
		//Setting up sensor input
   		sm = s;
   		//List<Sensor> slist = sm.getSensorList(Sensor.TYPE_ALL);
   		mAccelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
   		//for(int i=0;i<slist.size();i++)
   			//Log.d("TEST", String.format("Sensor[%d] is %s\n", i,slist.get(i).getName()));
   		sm.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	//listener for UIMenu
	public void UIMenuListener(String menuItem){
		Log.d("TEST", String.format("Controller recive event from UIMenu: itemName is ---> %s", menuItem));
	}
	

	
	//Methods for SensorEventListener
	public void onSensorChanged(SensorEvent e){
		Log.d("TEST" , String.format("[Sensor] x=%f y=%f z=%f", e.values[0], e.values[1], e.values[2]));
		if(e.values[X] > 1.0f || e.values[X] < -1.0f)
			cam.rotateOn(0,(float)e.values[X]/20 , 0);
		if(e.values[Z] > 1.0f || e.values[Z] < -1.0f)
			cam.rotateOn((float)e.values[Z]/20 , 0 , 0);
	}
	
	public void onAccuracyChanged(Sensor s, int i){
		
	}
	
	public void processTouch(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_MOVE :
			if(event.getHistoricalY(0)!=0);
				//gameOpenGlRenderer.pl.rx -= (event.getY()-event.getHistoricalY(1))/100;
			//printSamples(event);
			break;
		
		case MotionEvent.ACTION_UP:
			movingLeft=false;
			movingRight=false;
			movingUp = false;
			movingDown = false;
			//printSamples(event);
			break;
		case MotionEvent.ACTION_DOWN:
			//vibrator.vibrate(50);
			if(left.checkIntersect(event.getX(), event.getY())){movingLeft=true;}
			
			if(right.checkIntersect(event.getX(), event.getY())){movingRight=true;}
			
			if(top.checkIntersect(event.getX(), event.getY())){movingUp = true;}
			
			if(bottom.checkIntersect(event.getX(), event.getY())){movingDown = true;}
			
			break; 
		}
	}
	
	public void updateCamera(Camera cam){
		if(movingLeft)cam.moveOn(-0.01f, 0.0f, 0.0f);
		if(movingRight)cam.moveOn(0.01f, 0.0f, 0.0f);
		if(movingUp)cam.moveOn(0.0f, 0.01f, 0.0f);
		if(movingDown)cam.moveOn(0.0f, -0.01f, 0.0f);
	}

	public void setCamera(Camera camera) {
		cam = camera;
	}

	public void onPause() {
		sm.unregisterListener(this, mAccelerometer);
	}

	public void onResume() {
		sm.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	/*
	private void printSamples(MotionEvent ev) {
	     final int historySize = ev.getHistorySize();
	     final int pointerCount = ev.getPointerCount();
	     
	     for (int h = 0; h < historySize; h++) {
	    	 android.util.Log.d("OGL",String.format("At time %d:  History size:%d", (int)ev.getHistoricalEventTime(h), historySize));
	         for (int p = 0; p < pointerCount; p++) {
	        	 android.util.Log.d("OGL",String.format("History  pointer %d: (X%f Y%f)",
	                 (int)ev.getPointerId(p), (float)ev.getHistoricalX(p, h), (float)ev.getHistoricalY(p, h)));
	         }
	     }
	     android.util.Log.d("OGL",String.format("At time %d:", (int)ev.getEventTime()));
	     for (int p = 0; p < pointerCount; p++) {
	    	 android.util.Log.d("OGL",String.format("Current  pointer %d: (X%f Y%f)",
	             (int)ev.getPointerId(p), (float)ev.getX(p), (float)ev.getY(p)));
	     }
	 }
	 */
}
