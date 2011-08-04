package test.com;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;

public class first extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		//GLSurfaceView view = new GLSurfaceView(this);
   		GameView view = new GameView(this);
        //Set Vibrator
   		android.os.Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
   		view.vibrator=v;
   		//view.setRenderer(new OpenGLRenderer());
   		setContentView(view);
   		
   		
    }
}