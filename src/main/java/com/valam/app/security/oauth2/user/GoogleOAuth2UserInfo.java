package com.valam.app.security.oauth2.user;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo{
	
	public GoogleOAuth2UserInfo(Map<String,Object> attributes) {
		super(attributes);
	}
	
	@Override
	public String getId() {
		return (String) attributes.get("sub");
	}
	
	@Override
	public String getName() {
		return (String) attributes.get("name");
	}
	
	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}
	
	
	@Override
	public String getImageUrl() {
		return(String) attributes.get("picture");
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getMiddleName() {
		// TODO Auto-generated method stub
		return null;
	}
    

	@Override
	public boolean getIS_MAJOR() {
		// TODO Auto-generated method stub
		return false;
	}

}
