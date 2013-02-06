package examples.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Employee {
    @EmbeddedId
    private EmployeeId id;
    private String name;
    private long salary;

    public Employee() {}
    // Important - creation of employee use embeded object
    public Employee(String country, int id) {
        this.id = new EmployeeId(country, id);
    }
    // no need of setter methods for embeded object fields
    public int getId() {
    	// traverse through embeded object
        return id.getId();
    }

    public String getCountry() {
        return id.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " country: " + getCountry();
    }
}
