package domain;

import java.util.List;


public class Type {
	

	private Integer typeId;
	private String type;
	private List<Issue> typelist;

	public Integer getTypeId() {
		return typeId;
	}

	public List<Issue> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<Issue> typelist) {
		this.typelist = typelist;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
