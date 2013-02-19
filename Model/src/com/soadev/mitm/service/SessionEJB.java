package com.soadev.mitm.service;


import com.soadev.mitm.domain.Department;
import com.soadev.mitm.domain.Employee;
import com.soadev.mitm.domain.Job;

import com.sun.xml.internal.txw2.annotation.XmlNamespace;

import java.util.List;

import javax.ejb.Remote;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@Remote
public interface SessionEJB {
    

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Job persistJob(Job job);
    
    Job mergeJob(@WebParam(mode = WebParam.Mode.IN, name = "job") Job job);
    void removeJob(Job job);

    Employee persistEmployee(Employee employee);

    Employee mergeEmployee(Employee employee);

    void removeEmployee(Employee employee);

    List<Employee> getEmployeeFindAll();

    Department persistDepartment(Department department);

    Department mergeDepartment(Department department);

    void removeDepartment(Department department);

    List<Department> getDepartmentFindAll();
    
    List<Job> findAllJobs();

    Job findJobById(String id);
    
    String hello(String name);
}
