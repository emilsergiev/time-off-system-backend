package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concretepage.dao.IRequestDAO;
import com.concretepage.entity.Request;
@Service
public class RequestService implements IRequestService {
	@Autowired
	private IRequestDAO requestDAO;
	@Override
	public Request getRequestById(int id) {
		Request obj = requestDAO.getRequestById(id);
		return obj;
	}
	@Override
	public List<Request> getAllRequests(){
		return requestDAO.getAllRequests();
	}
	@Override
	public synchronized boolean createRequest(Request request){
       requestDAO.createRequest(request);
       return true;
	}
	@Override
	public void updateRequest(Request request) {
		requestDAO.updateRequest(request);
	}
	@Override
	public void updateRequestStatus(Request request) {
		requestDAO.updateRequestStatus(request);
	}
	@Override
	public void deleteRequest(int id) {
		requestDAO.deleteRequest(id);
	}
}
