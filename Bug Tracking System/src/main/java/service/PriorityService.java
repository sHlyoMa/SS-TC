package service;

import java.util.List;

import domain.Issue;
import domain.Priority;

public interface PriorityService {

	public List<Priority> listOfPriority();

	public List<Issue> listOfIssueByPriority(Integer id);

}
