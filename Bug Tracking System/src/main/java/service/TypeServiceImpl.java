package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;

import dao.TypeMapper;
import domain.Issue;
import domain.Type;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
	
	@Autowired
	TypeMapper typeMapper;
	
	@Transactional
	@Cacheable(cacheName="MyCache")
	public List<Type> listOfTypes(){
		return typeMapper.listOfTypes();
	}
	
	@Transactional
	public List<Issue> listOfIssueByType(Integer id){
		return typeMapper.selectIssueByType(id);
	}
}
