package test.com;

import javax.microedition.khronos.opengles.GL10;

public class Camera {
	private float x=0;
	private float y=0;
	private float z=0;
	
	private float rx=0;
	private float ry=0;
	private float rz=0;
	
	void moveCamera(GL10 gl){
		//gl.glTranslatef(x, y, z);
		gl.glRotatef(rx, 1, 0, 0);
	    gl.glRotatef(ry, 0, 1, 0);
	    gl.glRotatef(rz, 0, 0, 1);
	    gl.glTranslatef(x, y, z);
	}
	
	void moveOn(float dx, float dy, float dz){
		x+=dx;
		y+=dy;
		z+=dz;
	}
	
	void moveTo(float dx, float dy, float dz){
		x=dx;
		y=dy;
		z=dz;
	}
	
	void rotateOn(float dx, float dy, float dz){
		rx+=dx;
		ry+=dy;
		rz+=dz;
	}
	
	void rotateTo(float dx, float dy, float dz){
		rx=dx;
		ry=dy;
		rz=dz;
	}
}
