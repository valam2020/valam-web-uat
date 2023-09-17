package com.valam.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.CommonAPIToken;
import com.valam.app.repo.CommonAPITokenRepository;

@Service
public class CommonApiTokenService {

	@Autowired
	private CommonAPITokenRepository apiTokenRepo;
	
	public CommonAPIToken save(CommonAPIToken capiToken) {
	
		CommonAPIToken apiToken = new CommonAPIToken();
		apiToken.set_enabled(true);
		apiToken.setAuth_common_id(capiToken.getAuth_common_id());
		apiToken.setLoggedin_date(LocalDateTime.now());
		apiToken.setLoggedin_user_name(capiToken.getLoggedin_user_name());
		return apiTokenRepo.save(apiToken);
	}
	
	public CommonAPIToken getByTokenId(String token_id) {
		return apiTokenRepo.getByTokenId(token_id);
	}
}
