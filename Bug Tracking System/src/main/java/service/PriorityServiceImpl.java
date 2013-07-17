package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;

import dao.PriorityMapper;
import domain.Issue;
import domain.Priority;

@Service
@Transactional
public class PriorityServiceImpl implements PriorityService {

	@Autowired
	PriorityMapper priorityMapper;

	@Transactional
	@Cacheable(cacheName="MyCache")
	public List<Priority> listOfPriority() {
		return priorityMapper.statusList();
	}

	@Transactional
	public List<Issue> listOfIssueByPriority(Integer id) {
		return priorityMapper.selectIssueByPriority(id);
	}
}
