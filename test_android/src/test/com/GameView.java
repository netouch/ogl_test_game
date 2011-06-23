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
			//if(event.getHistoricalY(0)!=0)
				//gameOpenGlRenderer.pl.y -= (event.getY()-event.getHistoricalY(0))/100;
			gameOpenGlRenderer.pl.rx += -(gameOpenGlRenderer.screenHeight/2- event.getY())/10;
			gameOpenGlRenderer.pl.ry += -(gameOpenGlRenderer.screenWidth/2- event.getX())/10;
			break;
		
		case MotionEvent.ACTION_UP:
			//gameOpenGlRenderer.pl.rx=0;
			System.out.printf("ACTION_UP at: %f | %f", event.getX(),event.getY());

			break;
		//case MotionEvent.ACTION_DOWN:
		//	gameOpenGlRenderer.pl.rx = event.getX()/10;
		//	break; 
	}
		return true;
	}
}
