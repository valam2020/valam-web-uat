package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valam.app.model.CustomerCommentTable;
import com.valam.app.repo.CustCommentRepo;


@Service
public class CustCommentService {

	
	@Autowired
	private CustCommentRepo custCommentRepo;
	
	
	public List<CustomerCommentTable> findAll() {
		return custCommentRepo.findAll();
	}
	
	public CustomerCommentTable saveComment(CustomerCommentTable customerDto) {
		CustomerCommentTable newComment = new CustomerCommentTable();
		newComment.setCustomerId(customerDto.getCustomerId());
		newComment.setEmpId(customerDto.getEmpId());
		newComment.setReason(customerDto.getReason());
		newComment.setRideId(customerDto.getRideId());
		newComment.setUsercomments(customerDto.getUsercomments());
		return custCommentRepo.save(newComment);
	}

	public Optional<CustomerCommentTable> findById(Long id) {
		return custCommentRepo.findById(id);
	}
	
	public CustomerCommentTable getByCustomer(Long id) {
		return custCommentRepo.findByCustomerId(id);
	}
	
	public List<CustomerCommentTable> getByRideId(Long id) {
		return custCommentRepo.findByRideId(id);
	}
	
	public CustomerCommentTable updateComment(CustomerCommentTable customerDto) {
		CustomerCommentTable newComment = custCommentRepo.findById(customerDto.getCommentId()).get();
		newComment.setCustomerId(customerDto.getCustomerId());
		newComment.setEmpId(customerDto.getEmpId());
		newComment.setReason(customerDto.getReason());
		newComment.setRideId(customerDto.getRideId());
		newComment.setUsercomments(customerDto.getUsercomments());
		return custCommentRepo.save(newComment);
	}
	
	public void deleteCustomerComment(long id) {
		custCommentRepo.deleteById(id); 
	}
	
	
}
