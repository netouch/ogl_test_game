package test.com;

import test.com.UI.IUIMenuListener;
import test.com.UI.UIActiveArea;
import android.util.Log;
import android.view.MotionEvent;
import android.hardware.SensorEventListener;
import android.hardware.*;


public class Controller implements IUIMenuListener, SensorEventListener{
	boolean cameraFocus = true;
	boolean objectFocus = false;
	boolean movingRight = false;
	boolean movingLeft = false;
	boolean movingUp = false;
	boolean movingDown = false;
	
	UIActiveArea left = null;
	UIActiveArea right = null;
	UIActiveArea top = null;
	UIActiveArea bottom = null;
	
	public Controller(){
		left = new UIActiveArea(0.0f , 0.0f , 200.0f , 200.0f);
		right = new UIActiveArea(200.0f , 0.0f , 400.0f , 200.0f);
	}
	
	public Controller(float width, float height){
		left = new UIActiveArea(0.0f , 0.0f , width/5 , height);
		right = new UIActiveArea(width - width/5 , 0.0f , width , height);
		
		top = new UIActiveArea(0.0f, 0.0f, width, height/5);
		bottom = new UIActiveArea(0.0f, height - height/5, width, height);
	}
	
	//listener for UIMenu
	public void UIMenuListener(String menuItem){
		Log.d("TEST", String.format("Controller recive event from UIMenu: itemName is ---> %s", menuItem));
	}
	
	//Methods for SensorEventListener
	
	public void onSensorChanged(SensorEvent e){
		
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
