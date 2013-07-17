package domain;

import java.sql.Date;

public class Comment {
	
	private Integer commentId;
	private Integer issueId;
	private Integer userId;
	private Date creatingDate;
	private String value;
	private UserInfo userName;
	
	public Integer getCommentId() {
		return this.commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getIssueId() {
		return this.issueId;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreatingDate() {
		return this.creatingDate;
	}
	public void setCreatingDate(Date creatingDate) {
		this.creatingDate = creatingDate;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public UserInfo getUserName() {
		return userName;
	}
	public void setUserName(UserInfo userName) {
		this.userName = userName;
	}
	
}
