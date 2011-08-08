package test.com;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Mesh {
	private FloatBuffer verticesBuffer = null;
	private ShortBuffer indicesBuffer = null;
	private int numOfIndices = -1;
	
	private float[] rgba = new float[]{1.0f , 1.0f , 1.0f , 1.0f};
	private FloatBuffer colorBuffer = null;
	
	public float x = 0.0f;
	public float y = 0.0f;
	public float z = 0.0f;
	
	public float rx = 0.0f;
	public float ry = 0.0f;
	public float rz = 0.0f;
	
	
	public void draw(GL10 gl) {
	    	// Counter-clockwise winding.
			gl.glFrontFace(GL10.GL_CCW);
			// Enable face culling.
			gl.glEnable(GL10.GL_CULL_FACE);
			// What faces to remove with the face culling.
			gl.glCullFace(GL10.GL_BACK);
			// Enabled the vertices buffer for writing and to be used during
			// rendering.
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			// Specifies the location and data format of an array of vertex
			// coordinates to use when rendering.
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
		        // Set flat color
		        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
		        // Smooth color
		        if ( colorBuffer != null ) {
		            // Enable the color array buffer to be used during rendering.
		            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		            // Point out the where the color buffer is.
		            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
		        }
		        
		    gl.glPushMatrix();
		    gl.glTranslatef(x, y, z);
		    gl.glRotatef(rx, 1, 0, 0);
		    gl.glRotatef(ry, 0, 1, 0);
		    gl.glRotatef(rz, 0, 0, 1);
		    
			gl.glDrawElements(GL10.GL_TRIANGLES, numOfIndices,
				GL10.GL_UNSIGNED_SHORT, indicesBuffer);
			
			gl.glPopMatrix();
			// Disable the vertices buffer.
			gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
			// Disable face culling.
			gl.glDisable(GL10.GL_CULL_FACE);
	    
    }
	
	
	protected void setVertices(float[] vertices){
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		verticesBuffer = vbb.asFloatBuffer();
		verticesBuffer.put(vertices);
		verticesBuffer.position(0);
	}
	
	
	protected void setIndices(short[] indices){
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length*2);
		ibb.order(ByteOrder.nativeOrder());
		indicesBuffer = ibb.asShortBuffer();
		indicesBuffer.put(indices);
		indicesBuffer.position(0);
		numOfIndices = indices.length;
	}

	protected void setColor(float r, float g , float b, float a){
		rgba[0] = r;
		rgba[1] = g;
		rgba[2] = b;
		rgba[3] = a;
	}
	
	protected void setColors(float[] colors){
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(colors);
		colorBuffer.position(0);
	}	
}
