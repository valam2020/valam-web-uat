package com.valam.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.CommentsTable;
import com.valam.app.repo.CommentRepositary;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepositary commentRepo;
	
    public CommentsTable addComments(CommentsTable comment) {
    	CommentsTable commentData = new CommentsTable();
    	commentData.setComment(comment.getComment());
    	commentData.setCreatedDate(LocalDate.now());
    	commentData.setRating(comment.getRating());
    	commentData.setDriverId(comment.getDriverId());
    	commentData.setUserId(comment.getUserId());
    	return commentRepo.save(commentData);
    }
    
    public List<CommentsTable> getComments() {
    	return commentRepo.findAll();
    }
}
