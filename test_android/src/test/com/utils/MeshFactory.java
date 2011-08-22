package test.com.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.InputStream;

import android.app.Activity;
import test.com.Mesh;



public class MeshFactory {
	Activity act = null;
	
	//InputStream inputStream = null;
	StringBuffer content = null;
	//BufferedReader reader = null;
	
	Vector<VerticeUnit> v = new Vector<VerticeUnit>();
	Vector<UVCoordUnit> vt = new Vector<UVCoordUnit>();
	Vector<IndexUnit> indexBuffer = new Vector<IndexUnit>();
	
	public MeshFactory(Activity activity){
		act = activity;
	}
	
	public Mesh createMesh(String path){
		loadObjFile(path);
		
		Mesh mesh = new Mesh();
		
		content = null;
		return mesh;
	}
	
	public boolean loadObjFile(String path){
		//file = new File(fileName);
		InputStream inputStream = null;
		BufferedReader reader = null;
		
		try {
			inputStream = act.getAssets().open(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		content = new StringBuffer();
		
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while((line = reader.readLine()) != null){
				content.append(line);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e){
			e.printStackTrace();
			return false;
		} finally{
			try{
				if(reader!=null)reader.close();
			}catch (IOException e){
				e.printStackTrace();
				return false;
			}
		}
		
		System.out.println(content.toString());
		return true;
	}
	
	private boolean parseContent(){
		if(content == null)return false;
		
		String line = null;
		
		//TODO: code parser part here
		for(int i=0;i< content.length();i++){
			//line = content.get
		}
		
		return true;
	}
	
	public class VerticeUnit{
		float x;
		float y;
		float z;
	}
	
	public class UVCoordUnit{
		float uv;
		float uw;
	}
	
	public class IndexUnit{
		int i;
	}
}
