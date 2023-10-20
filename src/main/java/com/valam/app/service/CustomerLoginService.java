package com.valam.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.valam.app.dto.CustomerLoginDto;

import com.valam.app.model.CommonAPIToken;
import com.valam.app.model.CustomerLogin;
import com.valam.app.repo.CustomerLoginRepo;

@Service
public class CustomerLoginService {

	@Autowired
	private CustomerLoginRepo customerLoginRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	 private CommonApiTokenService tokenService;

	public List<CustomerLogin> findAll() {
		return customerLoginRepo.findAll();
	}

	public CustomerLogin saveCustomerLogin(CustomerLoginDto customerDto) {
		int size = customerLoginRepo.findAll().size();
		CustomerLogin customer = new CustomerLogin();
		customer.setEmpId(customerDto.getRoleCode()+size);
		customer.set_active(false);
		customer.set_major(false);
		customer.setAddress(customerDto.getAddress());
		customer.setEmail(customerDto.getEmail());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setReason(customerDto.getReason());
		customer.setRole_id(customerDto.getRole_id());
		customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
		return customerLoginRepo.save(customer);
	}

	public Optional<CustomerLogin> findById(Long id) {
		return customerLoginRepo.findById(id);
	}
	
	public CustomerLogin findByEmpId(String empid) {
		return customerLoginRepo.findByEmpId(empid);
	}

	public CustomerLogin updateCustomerLogin(CustomerLoginDto customerDto) {
		CustomerLogin customer = customerLoginRepo.findById(customerDto.getCustomerId()).get();
		if (customer != null && customerDto.getRole_id() !=null) {
			customer.setAddress(customerDto.getAddress());
			customer.setEmail(customerDto.getEmail());
			customer.setFirstName(customerDto.getFirstName());
			customer.setLastName(customerDto.getLastName());
			customer.setPhoneNumber(customerDto.getPhoneNumber());
			customer.setReason(customerDto.getReason());
			customer.setRole_id(customerDto.getRole_id());
			return customerLoginRepo.save(customer);
		} else {
			return customer = null;
		}
	}

	public CustomerLogin getByEmail(CustomerLoginDto customerDto) {
		return customerLoginRepo.findByEmailId(customerDto.getAddress());
	}

	public CustomerLoginDto getByEmailId(CustomerLoginDto customerDto) {
		CustomerLogin customerLogin = customerLoginRepo.findByEmailId(customerDto.getEmail());
		CustomerLoginDto loginDto = new CustomerLoginDto();
		boolean isPasswordMatch = passwordEncoder.matches(customerDto.getPassword(), customerLogin.getPassword());
		System.out.println(isPasswordMatch);
		if (isPasswordMatch == true) {
			CommonAPIToken apiToken = new CommonAPIToken();
			apiToken.setAuth_common_id("Customer"+"-"+LocalDate.now()+"-"+String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
        	apiToken.setLoggedin_user_name(customerLogin.getFirstName());
        	apiToken = tokenService.save(apiToken);
        	loginDto.setCustomerId(customerLogin.getCustomerId());
        	loginDto.setAddress(customerLogin.getAddress());
        	loginDto.setAuth_common_id(apiToken.getAuth_common_id());
        	loginDto.setEmail(customerLogin.getEmail());
        	loginDto.setFirstName(customerLogin.getFirstName());
        	loginDto.setLastName(customerLogin.getLastName());
        	loginDto.setPhoneNumber(customerLogin.getPhoneNumber());
        	loginDto.setReason(customerLogin.getReason());
        	loginDto.setRole_id(customerLogin.getRole_id());
        	loginDto.setRoleCode(customerLogin.getRole_id());
        	loginDto.setUserName(customerLogin.getUserName());;
			return loginDto;
		} else
			return null;
	}

	public CustomerLogin resetPassword(CustomerLoginDto customerDto) {

		if (customerLoginRepo.findByEmailId(customerDto.getEmail()) != null) {
			Long id = customerLoginRepo.findByEmailId(customerDto.getEmail()).getCustomerId();
			CustomerLogin driv = customerLoginRepo.findById(id).get();
			driv.setPassword(passwordEncoder.encode(customerDto.getPassword()));
			return customerLoginRepo.save(driv);
		} else
			return null;

	}
	
	public void deleteCustomerLogin(long id) {
		customerLoginRepo.deleteById(id); 
	}

}
