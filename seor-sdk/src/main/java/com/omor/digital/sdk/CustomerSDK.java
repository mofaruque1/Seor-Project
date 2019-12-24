package com.omor.digital.sdk;

import com.omor.digital.sdk.model.User;
import com.omor.digital.sdk.utils.Utils;

public class CustomerSDK extends BaseSDK {
	
	public CustomerSDK() {
		super();
	}

	public boolean registerNewCustomer(User user) {
		User existingUser = ddbTableMapper.load(User.class, user.getEmail());
		if (Utils.checkIfNullObject(existingUser)) {
			ddbTableMapper.save(user);
			return true;
		}
		return false;
	}
	
	public User signinCustomer(String email, String password) {
		User existingUser = ddbTableMapper.load(User.class, email);
		if (!Utils.checkIfNullObject(existingUser) && existingUser.getPassword().contentEquals(password)) {
			return existingUser;
		}
		return null;
	}
	

}
