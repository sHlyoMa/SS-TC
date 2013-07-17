package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ActionMapper;
import domain.Action;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	ActionMapper actionMapper;
	
	public List<Action> actionList(){
		return actionMapper.actionList();
	}
	
}
