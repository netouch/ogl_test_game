package test.com;

import test.com.UI.IUIMenuListener;
import test.com.UI.UIActiveArea;
import android.util.Log;
import android.view.MotionEvent;


public class Controller implements IUIMenuListener{
	boolean cameraFocus = true;
	boolean objectFocus = false;
	boolean movingRight = false;
	boolean movingLeft = false;
	
	UIActiveArea left;
	UIActiveArea right;
	
	public Controller(){
		left = new UIActiveArea(0.0f , 0.0f , 200.0f , 200.0f);
		right = new UIActiveArea(200.0f , 0.0f , 400.0f , 200.0f);
	}
	
	public void UIMenuListener(String menuItem){
		Log.d("TEST", String.format("Controller recive event from UIMenu: itemName is ---> %s", menuItem));
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
			//printSamples(event);
			break;
		case MotionEvent.ACTION_DOWN:
			//vibrator.vibrate(50);
			if(left.checkIntersect(event.getX(), event.getY())){
				movingLeft=true;
				movingRight=false;
			}
			
			if(right.checkIntersect(event.getX(), event.getY())){
				movingLeft=false;
				movingRight=true;
			}
			
			break; 
		}
	}
	
	public void updateCamera(Camera cam){
		if(movingLeft)cam.moveOn(-0.01f, 0.0f, 0.0f);
		if(movingRight)cam.moveOn(0.01f, 0.0f, 0.0f);
	}
}
