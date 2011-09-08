package test.com;

import java.util.Random;



public class Plane extends Mesh {
	static Random rnd;
	
	public Plane(){
		this(1,1,1,1);
	}
	
	public Plane(float width , float height){
		this(width , height , 1 , 1);
	}
	
	public Plane(float width , float height , int widthSegments , int heightSegments){
		float[] vertices = new float[(widthSegments+1)*(heightSegments+1)*3];
		float[] colors = new float[(widthSegments+1)*(heightSegments+1)*4];
		short[] indices = new short[(widthSegments)*(heightSegments)*6];
		
		rnd=new Random();
		
		float xOffcet = -(width / 2);
		float yOffcet = -(height / 2);
		float xWidth = width / (float)widthSegments;
		float xHeight = height / (float)heightSegments;
		int curVertex=0;
		int curColor=0;
		int curIndex=0;
		short w=(short)(widthSegments+1);
		
		for(int y=0;y<heightSegments+1;y++)
			for(int x=0;x<widthSegments+1;x++)
			{
				vertices[curVertex+0] = xOffcet + x*xWidth;
				vertices[curVertex+1] = yOffcet + y*xHeight;
				vertices[curVertex+2] = rnd.nextFloat()/10.0f;
								
				curVertex +=3;
				
				colors[curColor+0] = rnd.nextFloat();
				colors[curColor+1] = rnd.nextFloat();
				colors[curColor+2] = rnd.nextFloat();
				colors[curColor+3] = rnd.nextFloat();
				curColor += 4;
				
				if(x<widthSegments && y<heightSegments)
				{
					int n=w*y+x;
					//face one
					indices[curIndex+0] = (short)(n);
					indices[curIndex+1] = (short)(n + 1);
					indices[curIndex+2] = (short)(n + w);
					
					//face two
					indices[curIndex+3] = (short)(n + w);
					indices[curIndex+4] = (short)(n+1);
					indices[curIndex+5] = (short)(n + w + 1);
					curIndex += 6;
				}
				
			}
		
		setVertices(vertices);
		setIndices(indices);
		setColors(colors);
	}
}
