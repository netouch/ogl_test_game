package test.com;

import test.com.utils.MeshFactory;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;
import android.util.Log;
import android.hardware.*;

import java.util.List;

public class first extends Activity {
    GameView view = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Log.d("TEST","\n\n-----------New Start-----------");
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		
        //GLSurfaceView view = new GLSurfaceView(this);
   		view = new GameView(this);
        
   		setInputs();
   		
   		//Set Vibrator
   		//Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
   		//view.vibrator=v;
   		setContentView(view); 
   		//view.createScene();
   		
   		MeshFactory mf = new MeshFactory(this);
   		Mesh m = mf.createMesh("monkey.obj");
   		//m.setColor(0.5f, 0.5f, 0.5f, 0.5f);
   		m.loadBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.grid));
   		m.z = -2.0f;
   		view.gameOpenGlRenderer.mRoot.addMesh(m);
    }
    
    public void setInputs(){
    	//Create end setUp Controller
   		Controller controller = new Controller(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
   		view.setController(controller);
   		
   		//Setting up sensor input
   		SensorManager sm = (SensorManager)getSystemService(SENSOR_SERVICE);
   		//List<Sensor> slist = sm.getSensorList(Sensor.TYPE_ALL);
   		Sensor mAccelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
   		//for(int i=0;i<slist.size();i++)
   			//Log.d("TEST", String.format("Sensor[%d] is %s\n", i,slist.get(i).getName()));
   		sm.registerListener(controller, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
   		
    }
}









