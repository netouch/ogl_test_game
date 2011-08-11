package test.com;

import android.app.Activity;
//import android.content.Context;
import android.opengl.GLSurfaceView;
//import android.util.Log;
import android.view.MotionEvent;
import android.os.Vibrator;


public class GameView extends GLSurfaceView {
	private OpenGLRenderer gameOpenGlRenderer=null;
	public Vibrator vibrator;
	public Controller controller;
	
	public GameView(Activity act){
		super(act);
		gameOpenGlRenderer = new OpenGLRenderer();
		setRenderer(gameOpenGlRenderer);
		
		controller = new Controller();
		gameOpenGlRenderer.setController(controller);
		//Log.d("TEST_TEST", String.format("W=%d H=%d", this.getWidth(), this.getHeight()));
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		controller.processTouch(event);
		return true;
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
