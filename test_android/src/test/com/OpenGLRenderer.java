package test.com;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class OpenGLRenderer implements Renderer {
	public int screenWidth=0;
	public int screenHeight=0;
	
	public Controller controller = null;
	public Camera camera;
	public MeshGroup mRoot = null;
	
	VisualDebug debug;
	
	public OpenGLRenderer() {
		mRoot = new MeshGroup();
		debug = new VisualDebug();
		camera = new Camera();
		camera.moveTo(0, 0, -4.0f);
		Log.d("TEST", String.format("OGLRenderer constructor W=%d H=%d", screenWidth, screenHeight));
	}
	
	public void setController(Controller ctrl){
		controller = ctrl;
		controller.setCamera(camera);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition
	 * .khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
	 */
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Set the background color to black ( rgba ).
		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f);
		// Enable Smooth Shading, default not really needed.
		gl.glShadeModel(GL10.GL_SMOOTH);
		// Depth buffer setup.
		gl.glClearDepthf(1.0f);
		// Enables depth testing.
		gl.glEnable(GL10.GL_DEPTH_TEST);
		// The type of depth testing to do.
		gl.glDepthFunc(GL10.GL_LEQUAL);
		// Really nice perspective calculations.
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		Log.d("TEST", String.format("onSurfaceCreated W=%d H=%d", screenWidth, screenHeight));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
	 * khronos.opengles.GL10)
	 */
	public void onDrawFrame(GL10 gl) {
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// Replace the current matrix with the identity matrix
		gl.glLoadIdentity();

		if(controller!=null)controller.updateCamera(camera);
		camera.moveCamera(gl);
		
		// Draw.
		debug.draw(gl);
		mRoot.draw(gl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Sets the current view port to the new size.
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// Reset the modelview matrix
		gl.glLoadIdentity();
		
		screenWidth = width;
		screenHeight = height;
		Log.d("TEST", String.format("onSurfaceChanged W=%d H=%d", screenWidth, screenHeight));
	}
}
