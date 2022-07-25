package com.mfpe.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.mfpe.user.bean.Login;
import com.mfpe.user.entity.User;
import com.mfpe.user.exception.UserException;
import com.mfpe.user.service.UserService;

@CrossOrigin()
@RestController
public class UserController {
	
	

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public String signUp(@RequestBody User user) {
		int uid;
		try {
			uid = userService.signUp(user);
			return "User added successfully with user id : " + uid;
		} catch (UserException e) {
			e.printStackTrace();
			return "" + e.getMessage();
		}
	}

	@GetMapping("/authenticate/{userName}/{password}")
	public ResponseEntity<?> authenticate(@PathVariable String userName, @PathVariable String password) {

		Login login = new Login();
		login.setUserName(userName);
		login.setPassword(password);
		User user = userService.validate(login);
		if (user != null) {
			user.setPassword(password);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid username or password", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logged out successfully";
	}

}
