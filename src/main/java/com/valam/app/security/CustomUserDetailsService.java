package com.valam.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.model.User;
import com.valam.app.repo.UserRepository;
import com.valam.app.exception.ResourceNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService{
   
	@Autowired
	UserRepository userRepository;
	
	//to load User By User email and return to user principal to create access token
	@Override
	@Transactional
	public UserDetails  loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(() -> 
		    new UsernameNotFoundException("User not found with email:" + email)
		    );
		return UserPrincipal.create(user);
	}
	
	@Transactional 
	public UserDetails loadUserById(Long id) {
		User user  = userRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User",  "id", id)
				);
		return UserPrincipal.create(user);
	}
}
