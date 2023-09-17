package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.SubjectLines;
import com.valam.app.repo.SubjectLineRepo;

@Service
public class SubjectLinesService {
	
	@Autowired private SubjectLineRepo subjectRepo;
	
	
	public SubjectLines saveNewSubjectLine(SubjectLines subject) {
		return subjectRepo.save(subject);
	}
	
	public List<SubjectLines> getALlSubjectLines() {
		return subjectRepo.findAll();
	}
	
	public Optional<SubjectLines> findbyId(Long id) {
		return subjectRepo.findById(id);
	}
	
	
	

}
