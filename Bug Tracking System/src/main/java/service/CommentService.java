package service;

import java.util.List;
import domain.Comment;

public interface CommentService {

	public List<Comment> commentList(Integer issueId) throws Exception;
	public Comment dynamicCreateComment(Comment comment) throws Exception;
	public void updateComment(Comment comment) throws Exception;
	public void deleteComment(Integer commentId);
	public Comment getCommentById (Integer commentId);
	
}
