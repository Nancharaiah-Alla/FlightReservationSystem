package com.mfpe.user.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.mfpe.user.bean.Login;
import com.mfpe.user.entity.User;
import com.mfpe.user.exception.UserException;

@Service
public interface UserService {	
	
	int signUp(User user) throws UserException;
	
	User validate(Login login);
	
	public Collection<User> getUsers();	
	




	
	
	

}
