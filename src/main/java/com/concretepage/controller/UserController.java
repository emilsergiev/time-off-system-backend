package com.concretepage.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.User;
import com.concretepage.service.IUserService;
import com.concretepage.service.EmailService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("user")
	public ResponseEntity<User> getUserById(@RequestParam("id") String id) {
		User user = userService.getUserById(Integer.parseInt(id));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@GetMapping("login")
	public ResponseEntity<Boolean> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		boolean isLoginValid = false;
		System.out.println("hello isme");
		return new ResponseEntity<Boolean>(isLoginValid, HttpStatus.OK);
	}
	@GetMapping("user-email")
	public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
		User user = userService.getUserByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@GetMapping("all-users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	@PostMapping("user")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
        boolean flag = userService.createUser(user);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user?id={id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@DeleteMapping("user")
	public ResponseEntity<Void> deleteUser(@RequestParam("id") String id) {
		userService.deleteUser(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
	@GetMapping(path="/send-email")
	public @ResponseBody String sendEmail(@RequestParam("email") String email, @RequestParam("subject") String subject, @RequestParam("body") String body) {
		try {
		emailService.sendEmail(email, subject, body);
		} catch(MailException e) {
			e.printStackTrace();
		}
		
		return "email sent";
	}
} 