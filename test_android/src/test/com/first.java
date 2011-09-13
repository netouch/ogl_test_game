package test.com;

import test.com.utils.MeshFactory;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;

//import java.util.List;

public class first extends Activity {
    GameView view;
    Controller controller;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Log.d("TEST","\n\n-----------New Start-----------");
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		
   		view = new GameView(this);
        
   		setInputs();
   		setContentView(view);
   		
   		/*
   		Set Vibrator
   		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
   		view.vibrator=v;
   		view.createScene();
   		*/
   		
   		MeshFactory mf = new MeshFactory(this);
   		Mesh m = mf.createMesh("monkey.obj");
   		m.loadBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.grid));
   		m.z = -2.0f;
   		view.gameOpenGlRenderer.mRoot.addMesh(m);
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	controller.onPause();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	controller.onResume();
    }
    
    public void setInputs(){
   		controller = new Controller(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
   		controller.setupSensors((SensorManager)getSystemService(SENSOR_SERVICE));
   		view.setController(controller);
    }
}






