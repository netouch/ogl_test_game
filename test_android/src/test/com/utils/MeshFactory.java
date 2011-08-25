package test.com.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.InputStream;
import android.util.Log;

import android.app.Activity;
import test.com.Mesh;

//TODO: добавить загрузку текстур.

public class MeshFactory {
	Activity act = null;
	
	Vector<String> cont = null;
	
	Vector<VerticeUnit> vb = new Vector<VerticeUnit>();
	Vector<UVCoordUnit> vtb = new Vector<UVCoordUnit>();
	Vector<IndexUnit> ib = new Vector<IndexUnit>();
	
	public MeshFactory(Activity activity){
		act = activity;
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
		
		cont = new Vector<String>();
		
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while((line = reader.readLine()) != null){
				cont.add(line);
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
		
		//System.out.println(content.toString());
		return true;
	}
/*	
	private boolean loadObjFile(String path){
		//file = new File(fileName);
		FileInputStream fstream = null;
		BufferedReader reader = null;
		
		try {
			fstream = new FileInputStream(path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		DataInputStream in = new DataInputStream(fstream);
	
		//content = new StringBuffer();
		cont = new Vector<String>();
		
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			
			while((line = reader.readLine()) != null){
				//content.append(line);
				cont.add(line);
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
		
		//System.out.println(content.toString());
		
		for(int i =0 ;i < cont.size() ; i++)System.out.println(cont.get(i));
		return true;
	}
*/


	private void parseCont(){
		String line = null;
		for(int i =0 ;i < cont.size() ; i++){
			line = cont.get(i);
			
			if(line.startsWith("v")){
				
				if(line.startsWith("vt")){
					//fill vertice buffer
					String params[] = new String[3];
					params = line.split(" ", 3);
					
					UVCoordUnit vt = new UVCoordUnit();
					vt.uv = Float.valueOf(params[1]).floatValue();
					vt.uw = Float.valueOf(params[2]).floatValue();
					vtb.add(vt);
				}else {
				
					//fill vertice buffer
					String params[] = new String[4];
					params = line.split(" ", 4);
					VerticeUnit v=new VerticeUnit();
					
					v.x = Float.valueOf(params[1]).floatValue();
					v.y = Float.valueOf(params[2]).floatValue();
					v.z = Float.valueOf(params[3]).floatValue();
					
					vb.add(v);
				}
			}
			
			if(line.startsWith("f")){
				//fill vertice buffer
				String params[] = new String[4];
				params = line.split(" ", 4);
				//тут делим индексы на индекс вершины и индекс текстурных координат.
				for(int t=0;t<3;t++){
					if(params[t+1].contains("/")){
						//узнать UV индекс
						String ivivt[] = new String[2];
						ivivt = params[t+1].split("/", 2);
						ib.add(new IndexUnit(Short.parseShort(ivivt[0]),Integer.parseInt(ivivt[1]) ));
					}
					else ib.add(new IndexUnit(Short.parseShort(params[t+1])));
				}
			}
			
		}
		
		Log.d("TEST", String.format("num of verticies is %d\nnum of UV is %d\nnum of indices is %d", vb.size(), vtb.size(), ib.size()));
		for(int i=0;i<ib.size();i++)Log.d("TEST", String.format("[%d] vi= %d vti = %d\n", i, ib.get(i).mvi, ib.get(i).mvti));
	}

	private Mesh generateMesh(){
		Mesh mesh = new Mesh();
		float[] vertices = new float[ib.size()*3];
		float[] uvTexCoord = null;
		short[] index = new short[ib.size()];
		
		if(vtb.size()!=0){
			uvTexCoord = new float[ib.size()*2];
		}
		
		for(int i=0; i< ib.size();i++){
			//index[i] = ib.get(i).mvi;
			index[i] = (short)i;
			
			vertices[i*3+0] = vb.get(ib.get(i).mvi-1).x;
			vertices[i*3+1] = vb.get(ib.get(i).mvi-1).y;
			vertices[i*3+2] = vb.get(ib.get(i).mvi-1).z;
			
			if(uvTexCoord != null){
				uvTexCoord[i*2+0] = 1 - vtb.get(ib.get(i).mvti-1).uv;
				uvTexCoord[i*2+1] = vtb.get(ib.get(i).mvti-1).uw;
			}
		}
		mesh.setVertices(vertices);
		if(uvTexCoord!=null)mesh.setTextureCoordinates(uvTexCoord);
		mesh.setIndices(index);
		
		return mesh;
	}

	public Mesh createMesh(String file){
		loadObjFile(file);
		//TODO: убрать вывод контента
		for(int i=0; i<cont.size();i++)Log.d("TEST", String.format("%s\n", cont.get(i)));
		
		parseCont();
		return generateMesh();
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
		short mvi=0;
		int mvti=0;
		public IndexUnit(short vi){mvi=vi;}
		public IndexUnit(short vi, int vti){mvi=vi; mvti = vti;}
	}
}
