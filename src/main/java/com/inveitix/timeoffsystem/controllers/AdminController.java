package com.inveitix.timeoffsystem.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inveitix.timeoffsystem.entities.Request;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/admin")
public class AdminController 
{
	RequestController reqController;
	
	@PutMapping(path="/approve-request/{id}")
	public String approveRequest(@PathVariable long id)
	{
		reqController.approveRequest(id);
		return "Successfully approved";
	}
	
	@PutMapping(path="/disapprove-request/{id}")
	public String disapproveRequest(@PathVariable long id)
	{
		reqController.disapproveRequest(id);
		return "Successfully disapproved";
	}
	
	@RequestMapping(path="/all-requests")
	public @ResponseBody Iterable<Request> getAllRequests()
	{
		return reqController.getAllRequests();
	}
}
