package test.com;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;


public class GameView extends GLSurfaceView {
	public OpenGLRenderer gameOpenGlRenderer=null;
	public Controller controller = null;
	
	public GameView(Activity act){
		super(act);
		gameOpenGlRenderer = new OpenGLRenderer();
		setRenderer(gameOpenGlRenderer);
		//Log.d("TEST", String.format("W=%d H=%d", this.getWidth(), this.getHeight()));		
	}
	
	public void setController(Controller ctrl){
		controller = ctrl;
		gameOpenGlRenderer.setController(controller);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		if(controller!=null)controller.processTouch(event);
		return true;
	}
	
	public void createScene(){
	}
}
