package test.com.UI;

public class UIActiveArea {
		float top;
		float left;
		float down;
		float right;

		public UIActiveArea(float l , float t , float r, float d){
			top = t;
			left = l;
			down = d;
			right = r;
		}
		
		public boolean checkIntersect(float x, float y){
			if(x >= left && x <= right && y >= top && y <= down)return true;
			else return false;
		}
}
