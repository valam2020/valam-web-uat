package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valam.app.model.CommentsTable;

@Repository
public interface CommentRepositary  extends JpaRepository<CommentsTable,Long>{
 
	
}
