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

import com.inveitix.timeoffsystem.entities.Admin;
import com.inveitix.timeoffsystem.repositories.AdminRepository;

@RestController
@RequestMapping(path="/api/admins")
public class AdminController
{
	@Autowired
	AdminRepository repo;

	@GetMapping(path="/all")
	public Iterable<Admin> getAllAdmins()
	{
		return repo.findAll();
	}

	@PostMapping(path="/add")
	public String addNewAdmin(@RequestBody Admin admin)
	{
		try { repo.save(admin); }
		catch (Exception e) { return e.toString(); }
		return "Admin succesfully created with id = " + admin.getId();
	}

	@DeleteMapping(path="/delete/{id}")
	public String deleteAdmin(@PathVariable long id)
	{
		try { repo.delete(id); }
		catch (Exception e) { return e.toString(); }
		return "Admin succesfully deleted!";
	}

	@PutMapping(path="/update/{id}")
	public String updateAdmin(@RequestBody Admin admin, @PathVariable long id)
	{
		try
		{
			Admin a = repo.findOne(id);
			a.setName(admin.getName());
			a.setEmail(admin.getEmail());
			a.setPassword(admin.getPassword());
			a.setEgn(admin.getEgn());
			a.setPto(admin.getPto());
			a.setUpto(admin.getUpto());
			repo.save(a);
		}
		catch (Exception e) { return e.toString(); }
		return "Admin succesfully updated!";
	}

}