package test.com;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;


public class GameView extends GLSurfaceView {
	private OpenGLRenderer gameOpenGlRenderer=null;
	
	public GameView(Activity act){
		super(act);
		gameOpenGlRenderer = new OpenGLRenderer();
		setRenderer(gameOpenGlRenderer);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_MOVE :
			if(event.getHistoricalY(0)!=0)
				gameOpenGlRenderer.pl.rx -= (event.getY()-event.getHistoricalY(1))/100;
			//gameOpenGlRenderer.pl.rx += -(gameOpenGlRenderer.screenHeight/2- event.getY())/30;
			//gameOpenGlRenderer.pl.ry += -(gameOpenGlRenderer.screenWidth/2- event.getX())/30;
			printSamples(event);
			break;
		
		case MotionEvent.ACTION_UP:
			//gameOpenGlRenderer.pl.rx=0;
			//System.out.printf("ACTION_UP at: %f | %f", event.getX(),event.getY());
			//android.util.Log.d("OGL",String.format("asdasdds X:%d Y:%d", (int)event.getX(), (int)event.getY()) );
			printSamples(event);
			break;
		//case MotionEvent.ACTION_DOWN:
		//	gameOpenGlRenderer.pl.rx = event.getX()/10;
		//	break; 
		}
		return true;
	}
	
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
}
