package examples.stateless;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import examples.model.Employee;

@Stateless
public class EmployeeServiceBean implements EmployeeService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }

    public Employee createEmployee(int id, String name) {
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        getEntityManager().persist(emp);
        return emp;
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            getEntityManager().remove(emp);
        }
    }

    public Employee changeEmployeeName(int id, String newName) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            emp.setName(newName);
        }
        return getEntityManager().merge(emp);
        
    }

    public Employee findEmployee(int id) {
        return getEntityManager().find(Employee.class, id);
    }

    public Collection<Employee> findAllEmployees() {
        Query query = getEntityManager().createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
    }
}
