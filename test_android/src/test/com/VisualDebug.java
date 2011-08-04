package test.com;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class VisualDebug {

	private float verticies[]={
			 0.0f ,  0.0f , 0.0f ,
			 1.0f ,  0.0f , 0.0f ,
			 0.0f ,  1.0f , 0.0f ,
			 0.0f ,  0.0f , 1.0f 
			};
	private short indicies[]={0,1,0,2,0,3};
	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	public VisualDebug(){
		ByteBuffer vbb=ByteBuffer.allocateDirect(verticies.length*4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(verticies);
		vertexBuffer.position(0);
		
		ByteBuffer ibb = ByteBuffer.allocateDirect(indicies.length*2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indicies);
		indexBuffer.position(0);
	}
	
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CCW);
		//gl.glEnable(GL10.GL_CULL_FACE);
		//gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawElements(GL10.GL_LINES, indicies.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
}
