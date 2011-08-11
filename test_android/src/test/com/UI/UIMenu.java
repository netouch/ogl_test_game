package test.com.UI;

import java.util.List;

import android.util.Log;

public class UIMenu {
	IUIMenuListener listener=null;
	List<UIMenuItem> menuItems;
	
	UIMenu(){};
	
	boolean registerUIMenuListener(IUIMenuListener listener){
		this.listener = listener;
		return true;
	};
	
	void touchAt(int x, int y){
		if(listener!=null) listener.UIMenuListener("Somebody touched menu!");
	};
	
	boolean addUIMenuItem(String group, String item){
		if(group.length()>0 & item.length()>0){
			menuItems.add(new UIMenuItem(group, item));
			Log.d("TEST", String.format("Menu item added groupName: itemName is ---> %s:%s", group, item));
		}
		return true;
	}
	
}
