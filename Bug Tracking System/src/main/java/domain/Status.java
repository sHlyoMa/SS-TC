package domain;

import java.util.List;

public class Status {

	private Integer statusId;
	private String status;
	private List<Status> statuslist;

	public List<Status> getStatusList() {
		return statuslist;
	}

	public void setStatusList(List<Status> statuslist) {
		this.statuslist = statuslist;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
