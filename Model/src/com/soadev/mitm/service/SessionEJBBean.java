package com.soadev.mitm.service;


import com.soadev.mitm.domain.Department;
import com.soadev.mitm.domain.Employee;
import com.soadev.mitm.domain.Job;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.jws.WebMethod;
import javax.jws.WebService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import oracle.adf.share.logging.ADFLogger;

@Stateless(name = "SessionEJB", mappedName = "Model-SessionEJB")

public class SessionEJBBean implements SessionEJB, SessionEJBLocal {
    @PersistenceContext(unitName = "Model")
    private EntityManager em;
    private static ADFLogger _logger =
        ADFLogger.createADFLogger(SessionEJBBean.class);

    public SessionEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Job persistJob(Job job) {
        em.persist(job);
        return job;
    }

    public String hello(String name) {
        _logger.info("hello [name: " + name);
        System.out.println("sop hello " + name);
        return "Hello " + name;
    }

    public Job mergeJob(Job job) {
        return em.merge(job);
    }

    public void removeJob(Job job) {
        job = em.find(Job.class, job.getJobId());
        em.remove(job);
    }

    public Job findJobById(String id) {
        _logger.info("findJob param: " + id);
        System.out.println("sop findJobById: " + id);
        if (id == "EMPTY") {
            return new Job();
        }
        Job result = null;
        if (id == null) {
            System.out.println("replacement...");
            result = em.find(Job.class, "AD_PRES");
        } else {
            result = em.find(Job.class, id);
        }
        result = em.find(Job.class, id);
        System.out.println(result.getJobTitle());
        return result;
    }


    public Employee persistEmployee(Employee employee) {
        em.persist(employee);
        return employee;
    }

    public Employee mergeEmployee(Employee employee) {
        return em.merge(employee);
    }

    public void removeEmployee(Employee employee) {
        employee = em.find(Employee.class, employee.getEmployeeId());
        em.remove(employee);
    }

    /** <code>select o from Employee o</code> */
    public List<Employee> getEmployeeFindAll() {
        return em.createNamedQuery("Employee.findAll").getResultList();
    }

    public Department persistDepartment(Department department) {
        em.persist(department);
        return department;
    }

    public Department mergeDepartment(Department department) {
        return em.merge(department);
    }

    public void removeDepartment(Department department) {
        department = em.find(Department.class, department.getDepartmentId());
        em.remove(department);
    }

    /** <code>select o from Department o</code> */
    public List<Department> getDepartmentFindAll() {
        return em.createNamedQuery("Department.findAll").getResultList();
    }

    /** <code>select o from Job o</code> */
    public List<Job> findAllJobs() {
        return em.createNamedQuery("findAllJobs").getResultList();
    }
}
