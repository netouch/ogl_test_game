package test.com;

import android.app.Activity;
import android.graphics.BitmapFactory;
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
		//Log.d("TEST", String.format("W=%d H=%d", this.getWidth(), this.getHeight()));		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		controller.processTouch(event);
		return true;
	}
	
	public void createScene(){
		Plane plane = new Plane(10,10);
		plane.loadBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
		gameOpenGlRenderer.mRoot.addMesh(plane);
	}
}
