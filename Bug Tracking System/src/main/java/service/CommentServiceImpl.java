package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CommentMapper;
import dao.UserMapper;
import domain.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private StringSplitter stringSplitter;
	
	@Transactional
	public List<Comment> commentList(Integer issueId) throws Exception {
		return commentMapper.commentList(issueId);
	}
	
	@Transactional
	public Comment dynamicCreateComment(Comment comment) throws Exception {
		comment.setUserId(userMapper.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId());
		comment.setValue(stringSplitter.splitter(comment.getValue(), 33));
		commentMapper.createComment(comment);
		return commentMapper.getCommentById(comment.getCommentId());	
	}

	@Transactional
	public void updateComment(Comment comment) throws Exception {
		commentMapper.updateComment(comment);
	}

	@Transactional
	public void deleteComment(Integer id) {
		commentMapper.deleteComment(id);
	}

	@Transactional
	public Comment getCommentById(Integer commentId) {
		return commentMapper.getCommentById(commentId);		
	}

}
