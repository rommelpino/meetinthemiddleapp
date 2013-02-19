package com.soadev.mitm.service;

import com.soadev.mitm.domain.Department;
import com.soadev.mitm.domain.Employee;
import com.soadev.mitm.domain.Job;

import java.util.List;

import javax.ejb.Local;

@Local
public interface SessionEJBLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Job persistJob(Job job);

    Job mergeJob(Job job);

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
}
