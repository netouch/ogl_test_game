package test.com;

import android.app.Activity;
import android.graphics.BitmapFactory;
//import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.os.Vibrator;
import android.graphics.Bitmap;


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
		Plane plane = new Plane(1,1);
		plane.setTextureCoordinates(new float[]{0.0f , 0.0f , 
												1.0f , 0.0f ,
												0.0f , 1.0f , 
												1.0f , 1.0f });
		Bitmap bitmap=null;
		Log.d("TEST", String.format("not loaded bitmap=%s", bitmap));
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
		Log.d("TEST", String.format("loaded bitmap=%s", bitmap));
		plane.loadBitmap(bitmap);
		gameOpenGlRenderer.mRoot.addMesh(plane);
		
		//and other one plane
		plane = new Plane(1.5f , 1.5f);
		plane.setTextureCoordinates(new float[]{0.0f , 0.0f , 
												1.0f , 0.0f ,
												0.0f , 1.0f , 
												1.0f , 1.0f });
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.face);
		plane.loadBitmap(bitmap);
		plane.x += 0.2f;
		plane.z = - 0.5f;
		gameOpenGlRenderer.mRoot.addMesh(plane);		
	}
}
