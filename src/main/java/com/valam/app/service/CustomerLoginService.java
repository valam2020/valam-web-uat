package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.valam.app.dto.CustomerLoginDto;
import com.valam.app.model.CustomerLogin;
import com.valam.app.repo.CustomerLoginRepo;

@Service
public class CustomerLoginService {

	@Autowired
	private CustomerLoginRepo customerLoginRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	public CustomerLogin getByEmailId(CustomerLoginDto customerDto) {
		CustomerLogin customerLogin = customerLoginRepo.findByEmailId(customerDto.getEmail());

		boolean isPasswordMatch = passwordEncoder.matches(customerDto.getPassword(), customerLogin.getPassword());
		System.out.println(isPasswordMatch);
		if (isPasswordMatch == true) {
			return customerLogin;
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
