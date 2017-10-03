package com.inveitix.timeoffsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inveitix.timeoffsystem.entities.User;
import com.inveitix.timeoffsystem.repositories.UserRepository;

@RestController
@RequestMapping(path="/api/users")
public class UserController
{
	@Autowired
	UserRepository repo;

	@GetMapping(path="/all")
	public Iterable<User> getAllUsers()
	{
		return repo.findAll();
	}

	@PostMapping(path="/add")
	public String addNewUser(@RequestBody User user)
	{
		try { repo.save(user); }
		catch (Exception e) { return e.toString(); }
		return "User succesfully created with id = " + user.getId();
	}

	@DeleteMapping(path="/delete/{id}")
	public String deleteUser(@PathVariable long id)
	{
		try { repo.delete(id); }
		catch (Exception e) { return e.toString(); }
		return "User succesfully deleted!";
	}

	@PutMapping(path="/update/{id}")
	public String updateUser(@RequestBody User user, @PathVariable long id)
	{
		try
		{
			User u = repo.findOne(id);
			u.setName(user.getName());
			u.setEmail(user.getEmail());
			u.setPassword(user.getPassword());
			u.setEgn(user.getEgn());
			u.setPto(user.getPto());
			u.setUpto(user.getUpto());
			repo.save(u);
		}
		catch (Exception e) { return e.toString(); }
		return "User succesfully updated!";
	}

}