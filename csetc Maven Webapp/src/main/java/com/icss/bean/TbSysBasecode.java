package com.icss.bean;

public class TbSysBasecode {
    private String id;

    private String level1id;

    private String level1name;

    private String level2id;

    private String level2name;

    private String value;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLevel1id() {
        return level1id;
    }

    public void setLevel1id(String level1id) {
        this.level1id = level1id == null ? null : level1id.trim();
    }

    public String getLevel1name() {
        return level1name;
    }

    public void setLevel1name(String level1name) {
        this.level1name = level1name == null ? null : level1name.trim();
    }

    public String getLevel2id() {
        return level2id;
    }

    public void setLevel2id(String level2id) {
        this.level2id = level2id == null ? null : level2id.trim();
    }

    public String getLevel2name() {
        return level2name;
    }

    public void setLevel2name(String level2name) {
        this.level2name = level2name == null ? null : level2name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	@Override
	public String toString() {
		return "TbSysBasecode [id=" + id + ", level1id=" + level1id + ", level1name=" + level1name + ", level2id="
				+ level2id + ", level2name=" + level2name + ", value=" + value + ", status=" + status + "]";
	}
    
    
}