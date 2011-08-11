package test.com.UI;

import java.util.List;

import android.util.Log;

public class UIMenu {
	private IUIMenuListener listener=null;
	private List<UIMenuItem> menuItems;
	private String currentGroup = "";
	
	UIMenu(){};
	
	boolean registerUIMenuListener(IUIMenuListener listener){
		if(listener!=null){
			this.listener = listener;
			return true;
		}
		else return false;
	};
	
	boolean setStartGroup(String startGroup){
		for(int i=0;i<menuItems.size();i++){
			if(menuItems.get(i).getGroupName().equals(startGroup)){
				currentGroup = startGroup;
				Log.d("TEST", String.format("Set start groupName success: startGroup is ---> %s", currentGroup));
				return true;
			}
		}
		Log.d("TEST", String.format("Set start groupName failure: no matches found for startGroup %s", startGroup));
		return false;
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
