package com.valam.app.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.valam.app.dto.UserDto;
import com.valam.app.exception.ResourceNotFoundException;
import com.valam.app.model.AuthProvider;
import com.valam.app.model.User;
import com.valam.app.payload.SignUpRequest;
import com.valam.app.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	 @Autowired
	 private JWTTokenCreator jwtCreator;
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	
	public User saveUser(SignUpRequest signUpRequest) {
		
		User user = new User();
		 user.setFirstName(signUpRequest.getFirstName());
	     user.setLastName(signUpRequest.getLastName());
	     user.setMiddleName(signUpRequest.getMiddleName());
	     user.set_major(signUpRequest.is_major());
	     user.setCreatedDate(LocalDate.now());
	     user.setName(signUpRequest.getName());
	     user.setEmail(signUpRequest.getEmail());
	     user.setPassword(signUpRequest.getPassword());
	     user.setProvider(AuthProvider.local);
	     user.setPhNum(signUpRequest.getPhNum());
	     user.setLatitude(signUpRequest.getLatitude());
	     user.setLongitude(signUpRequest.getLongitude());
	     user.setPassword(passwordEncoder.encode(user.getPassword()));
	     String token = jwtCreator.getJWTToken(signUpRequest.getEmail());
	     user.setToken(token);
		return userRepo.save(user);
	}
	
	public List<User> saveUsers(List<User> users){
		return userRepo.saveAll(users);
	}
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
	public List<User> getUserByEmail(String email) {
		System.out.println(userRepo.findByEmailId(email));
		
	   return userRepo.findByEmailId(email);
	}
	
	public UserDto userUpdate(User user) {
		
		User existingUser= userRepo.findById(user.getId()).orElse(null);
		if(user.getFirstName()!= null) {
		existingUser.setFirstName(user.getFirstName());
		}else {existingUser.setFirstName(existingUser.getFirstName());}
		if(user.getLastName() !=null) {
		existingUser.setLastName(user.getLastName());}
		else {existingUser.setLastName(existingUser.getLastName());}
		if(user.is_major() == true) {
				existingUser.set_major(user.is_major());}
		else {existingUser.set_major(existingUser.is_major());}
		if(user.getModifiedDate() != null) {
		existingUser.setModifiedDate(user.getModifiedDate());}
		else {user.setModifiedDate(LocalDate.now());}
		if(user.getLatitude() != null) {
		existingUser.setLatitude(user.getLatitude());}
		else {existingUser.setLatitude(existingUser.getLatitude());}
		if(user.getLongitude() != null) {
		existingUser.setLongitude(user.getLongitude());}
		else {existingUser.setLongitude(existingUser.getLongitude());}
		if(user.getPhNum() != null) {
		existingUser.setPhNum(user.getPhNum());}
		else {existingUser.setPhNum(existingUser.getPhNum());}
		if(user.getEmail() !=null) {
			existingUser.setEmail(user.getEmail());
		}else {existingUser.setEmail(existingUser.getEmail());}
		if(user.getMiddleName() != null) {
		existingUser.setMiddleName(user.getMiddleName());}
		else { existingUser.setMiddleName(existingUser.getMiddleName());}
		if(user.getName() != null) {
		existingUser.setName(user.getName());}
		else {existingUser.setName(existingUser.getName());}
		if(user.getToken() !=null) {
			existingUser.setToken(user.getToken());
		}else {existingUser.setToken(existingUser.getToken());}
		//existingUser.setPassword(user.getPassword());	
		
		User upuser = userRepo.save(existingUser);
		UserDto  userdto = new UserDto();
		userdto.setCreatedDate(upuser.getCreatedDate());
		userdto.setEmail(upuser.getEmail());
		userdto.setExist(upuser.getEmailVerified());
		userdto.setFirstName(upuser.getFirstName());
		userdto.setId(upuser.getId());
		userdto.setLastName(upuser.getLastName());
		userdto.setLatitude(upuser.getLatitude());
		userdto.setLongitude(upuser.getLongitude());
		userdto.setMiddleName(upuser.getMiddleName());
		userdto.setName(upuser.getPhNum());
		userdto.setName(upuser.getName());
		userdto.setSocial_signup_id(upuser.getSocial_signup_id());
		
		return userdto;
	}
    
	public User getUserById(Long id) {
		User user = userRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("User",  "id", id)
				);
		return user;
	}
	
	
	  @SuppressWarnings("finally")
	public boolean CheckUsernameExists(String email) { 
		  boolean useremailExists = false; 
		
		  try {
		  User user1 = userRepo.findByEmail(email).get();
	  if(user1.getEmail() == null) //this part does not happen even if it should 
		  {
		  System.out.println("not a existed User");
		  useremailExists = false; 
		  
	  }else {
		  
		  System.out.println("It already exists");
		  useremailExists = true; 
	  }
	  
	//  return useremailExists;
	  }
		  finally {
			  return useremailExists;
			}  
	  }
	  
	 public User findByPhNumAndEmail(UserDto user) {
		 System.out.println("user"+user.getEmail()+user.getPhNum());
		 User use = userRepo.findByMobileNumAndEmail(user.getPhNum(),user.getEmail());
		 System.out.println("user"+use.getEmail());
		 return use;
	 }
	 
	 public String updateUserToken(UserDto user) {
		 userRepo.updateToken(user.getToken(), user.getId());
		 return "Successfully Updated";
	 }
	 
	 public User findByToken(String token) {
		return userRepo.findByToken(token);
	 }
	 
	
	
}
