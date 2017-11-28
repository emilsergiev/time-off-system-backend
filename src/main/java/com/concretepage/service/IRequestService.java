package com.concretepage.service;

import java.util.List;

import com.concretepage.entity.Request;

public interface IRequestService {
     List<Request> getAllRequests();
     Request getRequestById(int id);
     boolean createRequest(Request request);
     void updateRequest(Request request);
     void deleteRequest(int id);
}
