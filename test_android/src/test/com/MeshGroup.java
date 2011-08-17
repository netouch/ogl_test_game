package test.com;

import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;

public class MeshGroup extends Mesh {
	private Vector<Mesh> mChildren = new Vector<Mesh>();
	
	@Override
	public void draw(GL10 gl){
		int num = mChildren.size();
		for(int i=0;i<num;i++){
			mChildren.get(i).draw(gl);
		}
	}
	
	public boolean addMesh(Mesh object){
		return mChildren.add(object);
	}
	
	public void clear(){
		mChildren.clear();
	}
}
