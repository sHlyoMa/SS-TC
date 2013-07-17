package service;

import java.util.List;


import domain.Issue;
import domain.Status;


public interface StatusService {

	public List<Status> listOfStatus();
	
	public List<Issue> listOfIssueByStatus(Integer id);
	
}
