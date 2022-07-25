package com.mfpe.user.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.user.bean.Login;
import com.mfpe.user.entity.User;
import com.mfpe.user.exception.UserException;
import com.mfpe.user.repository.UserRepository;
import com.mfpe.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public int signUp(User user) throws UserException {

		List<User> users = (List<User>) getUsers();
		User user_temp = null;
		for (User u : users) {
			if (u.getUserName().equals(user.getUserName()) && (u.getEmail().equals(user.getEmail())))
				user_temp = u;
		}

		if (user_temp == null) {
			userRepository.save(user);
			return user.getUserId();
		} else {
			throw new UserException("User already exist with userId " + user_temp.getUserId());
		}
	}

	@Override
	public User validate(Login login) {
		User users = userRepository.findByUserNameAndPassword(login.getUserName(), login.getPassword());
		return users;
	}

	@Override
	public Collection<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
}
