package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;

import dao.StatusMapper;
import domain.Issue;
import domain.Status;

@Service
@Transactional
public class StatusServiceImpl implements StatusService{
	
	@Autowired
	StatusMapper statusMapper;
	
	@Transactional
	@Cacheable(cacheName="MyCache")
	public List<Status> listOfStatus(){
		return statusMapper.listOfStatus();
	}
	@Transactional	
	public List<Issue> listOfIssueByStatus(Integer id){
		return statusMapper.selectIssueByStatus(id);
	}
}
