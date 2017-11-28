package com.concretepage.dao;
import java.util.List;
import com.concretepage.entity.Request;
public interface IRequestDAO {
    List<Request> getAllRequests();
    Request getRequestById(int id);
    void createRequest(Request request);
    void updateRequest(Request request);
    void deleteRequest(int id);
}
 