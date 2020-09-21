abstract public class Employee {
    private EmployeeType employeeType;
    private boolean availability = true;

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public boolean isAvalibility() {
        return availability;
    }

    public void setAvalibility(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
