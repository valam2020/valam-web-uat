package com.valam.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.model.CommentsTable;
import com.valam.app.service.CommentService;
import com.valam.app.service.CommonApiTokenService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private CommentService commentService;
	
	 @Autowired
	    private CommonApiTokenService commonTokenService;
	
	@ApiOperation(value = "api to get comments by using userid and driverid")
	@GetMapping("/all")
	public List<CommentsTable> findComments(){
		return commentService.getComments();
	}
	
	@ApiOperation(value = "api to add comments")
	@PostMapping("/add")
	public CommentsTable createComments(@RequestHeader(value="common_token") String commonToken,@RequestBody CommentsTable comment){
		CommentsTable comments = new CommentsTable();
		if(commonTokenService.getByTokenId(commonToken) != null) {
			comments=  commentService.addComments(comment);
		}else {
			comments = null;
		}
	
      return comments;
	}
}
