package examples.stateless;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import examples.model.Employee;
import examples.model.LogRecord;

@Stateless
public class AuditServiceBean implements AuditService {
    @PersistenceContext(unitName="EmployeeService")
    private EntityManager em;
    
    public void logTransaction(int empId, String action) {
        
       /* if (em.find(Employee.class, empId) == null) {
            throw new IllegalArgumentException("Unknown employee id");
        }*/
    	
        LogRecord lr = new LogRecord(empId, action);
        em.persist(lr);
    }
    // following will work
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    // This will not log transaction
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void logTransaction2(int empId, String action) {
        logTransaction(empId, action);
    }
}

