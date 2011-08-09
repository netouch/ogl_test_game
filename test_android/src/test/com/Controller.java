package test.com;

import test.com.UI.UIActiveArea;
import android.view.MotionEvent;


public class Controller {
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
	
	public void processTouch(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_MOVE :
			if(event.getHistoricalY(0)!=0);
				//gameOpenGlRenderer.pl.rx -= (event.getY()-event.getHistoricalY(1))/100;
			//printSamples(event);
			break;
		
		case MotionEvent.ACTION_UP:
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
}
