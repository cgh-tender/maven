package com.dw.util;

import java.io.Serializable;
import java.util.List;

public class BaseTreeGrid implements Serializable{  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = -9189631784252440402L;  
      
    public String id;//节点id  
    public String name;//节点名称
    public String parentId;//节点父id  
    public String level;
    public String noede_desc;//描述
    public String ULR;//链接
	public String iconCls = "folder";//节点样式，默认即可  
    public Boolean leaf = true;//是否为叶子节点，true表示是叶子节点，false表示不是叶子节点  
    public Boolean expanded = true; //是否展开，默认true,展开  
      
    public List<BaseTreeGrid> children;//孩子节点  
  
  
    public String getULR() {
		return ULR;
	}

	public void setULR(String uLR) {
		ULR = uLR;
	}
    public BaseTreeGrid() {  
          
    }  
      
    public BaseTreeGrid(String id, String parentId) {  
        this.id=id;  
        this.parentId=parentId;  
    }  
  
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
 

	public String getNoede_desc() {
		return noede_desc;
	}

	public void setNoede_desc(String noede_desc) {
		this.noede_desc = noede_desc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    public String getParentId() {  
        return parentId;  
    }  
  
    public void setParentId(String parentId) {  
        this.parentId = parentId;  
    }  
  
    public String getIconCls() {  
        return iconCls;  
    }  
  
    public void setIconCls(String iconCls) {  
        this.iconCls = iconCls;  
    }  
  
    public Boolean getLeaf() {  
        return leaf;  
    }  
  
    public void setLeaf(Boolean leaf) {  
        this.leaf = leaf;  
    }  
  
    public Boolean getExpanded() {  
        return expanded;  
    }  
  
    public void setExpanded(Boolean expanded) {  
        this.expanded = expanded;  
    }  
  
    public List<BaseTreeGrid> getChildren() {  
        return children;  
    }  
  
    public void setChildren(List<BaseTreeGrid> children) {  
        this.children = children;  
    }  
}
