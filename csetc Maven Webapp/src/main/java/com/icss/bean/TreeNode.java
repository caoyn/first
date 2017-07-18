package com.icss.bean;
import java.util.List;

public class TreeNode {  
  
    private String id;  //当前id
  
    private String parentId;  //上级id
  
    private String name;  // 名称（文本）
    
    private String value; //值
  
    private List<TreeNode> children;  //子节点
    
    private boolean disabled; //是否禁用
    
    private String status; //状态
    
    private String level; // 层级
    
    public TreeNode() {
	}
    
	public TreeNode(String id, String name, String parentId) {  
        this.id = id;  
        this.parentId = parentId;  
        this.name = name;  
    }  
    public TreeNode(String id, String name, TreeNode parent) {  
        this.id = id;  
        this.parentId = parent.getId();  
        this.name = name;  
    }  
  
    public TreeNode(String id, String parentId, String name, String value, boolean disabled, String status, String level) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.value = value;
		this.disabled = disabled;
		this.status = status;
		this.level = level;
	}
	public String getParentId() {  
        return parentId;  
    }  
  
    public void setParentId(String parentId) {  
        this.parentId = parentId;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
    
    public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<TreeNode> getChildren() {  
        return children;  
    }  
  
    public void setChildren(List<TreeNode> children) {  
        this.children = children;  
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Override  
    public String toString() {  
		boolean flag = true;
		if("1".equals(status)){
			flag = false;
		}
        return "{" +  
                "'id':'" + id + '\'' +  
                ", 'pId':'" + parentId + '\'' +  
                ", 'text':'" + name + '\'' +  
                ", 'vaule':'" + value + '\'' +  
                ", 'disabled':'" + flag + '\'' +  
                ", 'status':'" + status + '\'' +  
                ", 'level':'" + level + '\'' +  
                ", 'children':" + children +  
                '}';  
    }  
  
}
