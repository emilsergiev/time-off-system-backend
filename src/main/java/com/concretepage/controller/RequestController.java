package com.concretepage.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.concretepage.entity.Request;
import com.concretepage.service.IRequestService;

@Controller
@RequestMapping("request")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RequestController {
	@Autowired
	private IRequestService requestService;
	@GetMapping("request")
	public ResponseEntity<Request> getRequestById(@RequestParam("id") String id) {
		Request request = requestService.getRequestById(Integer.parseInt(id));
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}
	@GetMapping("all-requests")
	public ResponseEntity<List<Request>> getAllRequests() {
		List<Request> list = requestService.getAllRequests();
		return new ResponseEntity<List<Request>>(list, HttpStatus.OK);
	}
	@PostMapping("request")
	public ResponseEntity<Void> createUser(@RequestBody Request request, UriComponentsBuilder builder) {
        boolean flag = requestService.createRequest(request);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/request?id={id}").buildAndExpand(request.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("request")
	public ResponseEntity<Request> updateRequest(@RequestBody Request request) {
		requestService.updateRequest(request);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}
	@PutMapping("request-status")
	public ResponseEntity<Request> updateRequestStatus(@RequestBody Request request) {
		requestService.updateRequestStatus(request);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}
	@DeleteMapping("request")
	public ResponseEntity<Void> deleteRequest(@RequestParam("id") String id) {
		requestService.deleteRequest(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 