package soadev;

import com.soadev.mitm.domain.Department;
import com.soadev.mitm.domain.Employee;
import com.soadev.mitm.domain.Job;
import com.soadev.mitm.service.SessionEJB;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SessionEJBClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            SessionEJB sessionEJB = (SessionEJB)context.lookup("Model-SessionEJB#com.soadev.mitm.service.SessionEJB");
            for (Department department : (List<Department>)sessionEJB.getDepartmentFindAll()) {
                printDepartment(department);
            }
            for (Employee employee : (List<Employee>)sessionEJB.getEmployeeFindAll()) {
                printEmployee(employee);
            }
            for (Job job : (List<Job>)sessionEJB.findAllJobs()) {
                printJob(job);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printDepartment(Department department) {
        System.out.println( "departmentId = " + department.getDepartmentId() );
        System.out.println( "departmentName = " + department.getDepartmentName() );
        System.out.println( "locationId = " + department.getLocationId() );
        System.out.println( "manager = " + department.getManager() );
        System.out.println( "employeeList = " + department.getEmployeeList() );
    }

    private static void printEmployee(Employee employee) {
        System.out.println( "commissionPct = " + employee.getCommissionPct() );
        System.out.println( "email = " + employee.getEmail() );
        System.out.println( "employeeId = " + employee.getEmployeeId() );
        System.out.println( "firstName = " + employee.getFirstName() );
        System.out.println( "hireDate = " + employee.getHireDate() );
        System.out.println( "lastName = " + employee.getLastName() );
        System.out.println( "phoneNumber = " + employee.getPhoneNumber() );
        System.out.println( "salary = " + employee.getSalary() );
        System.out.println( "status = " + employee.getStatus() );
        System.out.println( "job = " + employee.getJob() );
        System.out.println( "department = " + employee.getDepartment() );
        System.out.println( "manager = " + employee.getManager() );
    }

    private static void printJob(Job job) {
        System.out.println( "jobId = " + job.getJobId() );
        System.out.println( "jobTitle = " + job.getJobTitle() );
        System.out.println( "maxSalary = " + job.getMaxSalary() );
        System.out.println( "minSalary = " + job.getMinSalary() );
        System.out.println( "employeeList = " + job.getEmployeeList() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        return new InitialContext( env );
    }
}
