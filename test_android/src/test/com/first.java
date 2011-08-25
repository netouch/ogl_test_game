package test.com;

import test.com.utils.MeshFactory;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;
import android.util.Log;

public class first extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Log.d("TEST","\n\n-----------New Start-----------");
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		
        //GLSurfaceView view = new GLSurfaceView(this);
   		GameView view = new GameView(this);
        
   		//Set Vibrator
   		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
   		view.vibrator=v;
   		
   		//now lets get W H
   		Log.d("TEST", String.format("Activity onCreate W=%d H=%d", getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()));
   		//TODO: push devices screen W and H to OGLRenderer, and other
   		//view.setRenderer(new OpenGLRenderer());
   		setContentView(view); 
   		//view.createScene();
   		
   		MeshFactory mf = new MeshFactory(this);
   		Mesh m = mf.createMesh("plane.obj");
   		//m.setColor(0.5f, 0.5f, 0.5f, 0.5f);
   		m.loadBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.face));
   		m.z = -2.0f;
   		view.gameOpenGlRenderer.mRoot.addMesh(m);
    }
}