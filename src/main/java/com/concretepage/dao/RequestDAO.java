package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Request;
@Transactional
@Repository
public class RequestDAO implements IRequestDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Request getRequestById(int id) {
		return entityManager.find(Request.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Request> getAllRequests() {
		String hql = "FROM Request as request ORDER BY request.id DESC";
		return (List<Request>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createRequest(Request request) {
		entityManager.persist(request);
	}
	@Override
	public void updateRequest(Request request) {
		Request requestObj = getRequestById(request.getId());
		requestObj.setDates(request.getDates());
		requestObj.setDays(request.getDays());
		requestObj.setNote(request.getNote());
		requestObj.setReason(request.getReason());
		requestObj.setStatus(request.getStatus());
		requestObj.setSubmit_time(request.getSubmit_time());
		requestObj.setType(request.getType());
		requestObj.setUser_id(request.getUser_id());
		entityManager.flush();
	}
	@Override
	public void updateRequestStatus(Request request) {
		Request requestObj = getRequestById(request.getId());
		requestObj.setDates(request.getDates());
		requestObj.setDays(request.getDays());
		requestObj.setNote(request.getNote());
		requestObj.setReason(request.getReason());
		requestObj.setStatus(request.getStatus());
		requestObj.setSubmit_time(request.getSubmit_time());
		requestObj.setType(request.getType());
		requestObj.setUser_id(request.getUser_id());
		entityManager.flush();
	}
	@Override
	public void deleteRequest(int id) {
		entityManager.remove(getRequestById(id));
	}
}
