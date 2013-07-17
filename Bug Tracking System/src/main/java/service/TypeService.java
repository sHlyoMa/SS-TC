package service;

import java.util.List;

import domain.Issue;
import domain.Type;

public interface TypeService {

	public List<Type> listOfTypes();
	
	public List<Issue> listOfIssueByType(Integer id);
}
