package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;

import dao.EnvironmentMapper;
import domain.Environment;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	EnvironmentMapper environmentMapper;
	
	@Transactional
	@Cacheable(cacheName="MyCache")
	public List<Environment> getEnvironmentList(){
		return environmentMapper.getEnvironmentList();
	}
}
