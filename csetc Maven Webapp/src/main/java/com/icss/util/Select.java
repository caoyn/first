package com.icss.util;

import java.util.ArrayList;
import java.util.List;

public class Select {
	
	public final String VALUE_NAME = "value";//下拉框值对应的键名称
	
	public final String LABEL_NAME = "label";//下拉框文本对应的键名称
	
	public final String DISABLED_NAME = "disabled";//下拉框选项对应的是否禁用名称
	
	public final String LEFT_SPLICING_SYMBOL = "{";//一个对象数据前面的包裹
	
	public final String RIGHT_SPLICING_SYMBOL = "}";//一个对象数据后面的包裹
	
	public final String SPLICING_SYMBOL = ",";//每个数据的间隔
	
	public final String QUOTES = "'";//数据键、值的包裹
	
	public final String SEPARATE = ":";//数据键与值的分隔
	
	private String id;
	
	private String pid;
	
	private String value;
	
	private String label;
	
	private boolean disabled;
	
	private String status;
	
	public Select() {
	}

	public Select(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public Select(String value, String label, String status) {
		this.value = value;
		this.label = label;
		this.status = status;
	}

	public Select(String value, String label, boolean disabled, String status) {
		this.value = value;
		this.label = label;
		this.disabled = disabled;
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	//简单拼接字符串
	public String simpleSplitJointStringToJson(List<Select> list){
		String result = "[";
		String valueLeft = LEFT_SPLICING_SYMBOL + QUOTES + VALUE_NAME + QUOTES + SEPARATE + QUOTES;
		String valueRight =  QUOTES + SPLICING_SYMBOL;
		String labelLeft =  QUOTES + LABEL_NAME + QUOTES + SEPARATE + QUOTES;
		String labelRight =  QUOTES + SPLICING_SYMBOL;
		String disabledLeft =  QUOTES + DISABLED_NAME + QUOTES + SEPARATE ;
		String disabledRight =  RIGHT_SPLICING_SYMBOL;
		for(int i = 0; i < list.size(); i++){
			boolean flag = true;
			if(list.get(i).getStatus().equals("1")){
				flag = false;
			}
			result += valueLeft + list.get(i).getValue() + valueRight;
			result += labelLeft + list.get(i).getLabel() + labelRight;
			result += disabledLeft + flag + disabledRight;
			
			if(i != list.size()-1){
				result += SPLICING_SYMBOL;
			}
			
		}
		result += "]";
		return result;
	}
	
	public static void main(String[] args) {
		List<Select> list = new ArrayList<Select>();
		list.add(new Select("huangjingao", "黄金糕", "1"));
		list.add(new Select("huangjingao1", "黄金糕1", "1"));
		list.add(new Select("huangjingao2", "黄金糕2", "0"));
		list.add(new Select("huangjingao3", "黄金糕3", "1"));
		list.add(new Select("huangjingao4", "黄金糕4", "0"));
		list.add(new Select("huangjingao5", "黄金糕5", "1"));
		Select s = new Select();
		System.out.println(s.simpleSplitJointStringToJson(list));
	}
}
