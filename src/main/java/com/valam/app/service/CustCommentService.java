package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.dto.CustomerLoginDto;
import com.valam.app.model.CustomerCommentTable;
import com.valam.app.model.CustomerLogin;
import com.valam.app.repo.CustCommentRepo;
import com.valam.app.repo.CustomerLoginRepo;

@Service
public class CustCommentService {

	@Autowired
	private CustomerLoginRepo customerLoginRepo;
	
	@Autowired
	private CustCommentRepo custCommentRepo;
	
	
	public List<CustomerCommentTable> findAll() {
		return custCommentRepo.findAll();
	}
	
	public CustomerCommentTable saveComment(CustomerCommentTable customerDto) {
		CustomerCommentTable newComment = new CustomerCommentTable();
		newComment.setCustomerId(customerDto.getCustomerId());
		newComment.setEmpId(customerDto.getEmpId());
		newComment.setIs_reviewbyadmin(false);
		newComment.setIs_reviewbyexecutive(false);
		newComment.setAdmincomments(customerDto.getAdmincomments());
		newComment.setExecutivecomments(customerDto.getExecutivecomments());
		newComment.setReason(customerDto.getReason());
		newComment.setReviewbyadmin(customerDto.getReviewbyadmin());
		newComment.setReviewbyexecutive(customerDto.getReviewbyexecutive());
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
	
	public CustomerCommentTable updateComment(CustomerCommentTable customerDto) {
		CustomerCommentTable newComment = custCommentRepo.findById(customerDto.getCommentId()).get();
		newComment.setCustomerId(customerDto.getCustomerId());
		newComment.setEmpId(customerDto.getEmpId());
		newComment.setIs_reviewbyadmin(customerDto.getIs_reviewbyadmin());
		newComment.setIs_reviewbyexecutive(customerDto.getIs_reviewbyexecutive());
		newComment.setAdmincomments(customerDto.getAdmincomments());
		newComment.setExecutivecomments(customerDto.getExecutivecomments());
		newComment.setReason(customerDto.getReason());
		newComment.setReviewbyadmin(customerDto.getReviewbyadmin());
		newComment.setReviewbyexecutive(customerDto.getReviewbyexecutive());
		newComment.setRideId(customerDto.getRideId());
		newComment.setUsercomments(customerDto.getUsercomments());
		return custCommentRepo.save(newComment);
	}
	
	public void deleteCustomerComment(long id) {
		custCommentRepo.deleteById(id); 
	}
	
	
}
