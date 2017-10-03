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

import com.inveitix.timeoffsystem.entities.Request;
import com.inveitix.timeoffsystem.repositories.RequestRepository;

@RestController
@RequestMapping(path="/api/requests")
public class RequestController
{
	@Autowired
	RequestRepository repo;

	@GetMapping(path="/all")
	public Iterable<Request> getAllRequests()
	{
		return repo.findAll();
	}

	@PostMapping(path="/add")
	public String addNewRequest(@RequestBody Request request)
	{
		try { repo.save(request); }
		catch (Exception e) { return e.toString(); }
		return "Request succesfully created with id = " + request.getId();
	}

	@DeleteMapping(path="/delete/{id}")
	public String deleteRequest(@PathVariable long id)
	{
		try { repo.delete(id); }
		catch (Exception e) { return e.toString(); }
		return "Request succesfully deleted!";
	}

	@PutMapping(path="/update/{id}")
	public String updateRequest(@RequestBody Request request, @PathVariable long id)
	{
		try
		{
			Request r = repo.findOne(id);
			r.setType(request.getType());
			r.setDays(request.getDays());
			r.setDates(request.getDates());
			r.setSubmit(request.getSubmit());
			r.setStatus(request.getStatus());
			repo.save(r);
		}
		catch (Exception e) { return e.toString(); }
		return "Request succesfully updated!";
	}

}