package com.inveitix.timeoffsystem.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="/requests")
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
	public Map<String, Set<String>> addNewRequest(@Valid 
			@RequestBody Request request, BindingResult result)
	{
		Calendar calendar = Calendar.getInstance();
		Date now = new Date(calendar.getTime().getTime());
		request.setSubmitTime(now);

		Map<String, Set<String>> errors = findRequestErrors(request, result);

		if (errors.isEmpty()) { repo.save(request); }

		return errors;
	}

	@PutMapping(path="/update/{id}")
	public String updateRequest(@RequestBody Request request, @PathVariable long id)
	{
		Calendar calendar = Calendar.getInstance();
		Date now = new Date(calendar.getTime().getTime());

		try
		{
			Request update = repo.findOne(id);
			update.setType(request.getType());
			update.setDays(request.getDays());
			update.setDates(request.getDates());
			update.setReason(request.getReason());
			update.setNote(request.getNote());
			update.setSubmitTime(now);
			repo.save(update);
		}
		catch (Exception e) { return e.toString(); }
		return "Request succesfully updated!";
	}

	@PutMapping(path="/status/{id}")
	public String statusUpdate(@RequestBody String status, @PathVariable long id)
	{
		try
		{
			Request update = repo.findOne(id);
			update.setStatus(status);
			repo.save(update);
		}
		catch (Exception e) { return e.toString(); }
		return "Status succesfully updated!";
	}

	@DeleteMapping(path="/delete/{id}")
	public String deleteRequest(@PathVariable long id)
	{
		try { repo.delete(id); }
		catch (Exception e) { return e.toString(); }
		return "Request succesfully deleted!";
	}

	private Map<String, Set<String>> findRequestErrors(
			Request request, BindingResult result)
	{
		Map<String, Set<String>> errors = new HashMap<>();

		for (FieldError fieldError : result.getFieldErrors())
		{
			String code = fieldError.getCode();
			String field = fieldError.getField();

			if (code.equals("NotBlank"))
			{
				errors.computeIfAbsent(field, key -> new HashSet<>())
				.add("required");
			}
		}
		return errors;
	}

}