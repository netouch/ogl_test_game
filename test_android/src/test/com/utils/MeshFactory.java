package test.com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.io.InputStreamReader;
import android.content.Context;

import test.com.Mesh;



public class MeshFactory {
	//File file = null;
	InputStreamReader inputStream = null;
	StringBuffer content = null;
	BufferedReader reader = null;
	
	Vector<VerticeUnit> v = new Vector<VerticeUnit>();
	Vector<UVCoordUnit> vt = new Vector<UVCoordUnit>();
	Vector<IndexUnit> indexBuffer = new Vector<IndexUnit>();
	
	public boolean loadObjFile(InputStreamReader in){
		//file = new File(fileName);
		inputStream = in;
		content = new StringBuffer();
		
		try {
			reader = new BufferedReader(inputStream);
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
