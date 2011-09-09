package test.com.UI;

public class UIMenuItem {
	private String groupName = "";
	private String itemName = "";
	
	
	public UIMenuItem(){};
	public UIMenuItem(String group, String item){
		groupName = group;
		itemName = item;
	};
	
	public String getItemName(){
		return itemName;
	};
	
	public String getGroupName(){
		return groupName;
	}
}
