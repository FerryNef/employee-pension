import java.time.LocalDate;
import java.math.BigDecimal;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private BigDecimal yearlySalary;
    private PensionPlan pensionPlan;

    public Employee() {}

    public Employee(long employeeId, String firstName, String lastName, LocalDate employmentDate, BigDecimal yearlySalary, PensionPlan pensionPlan) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = pensionPlan;
    }

    public long getEmployeeId() { return employeeId; }
    public void setEmployeeId(long employeeId) { this.employeeId = employeeId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getEmploymentDate() { return employmentDate; }
    public void setEmploymentDate(LocalDate employmentDate) { this.employmentDate = employmentDate; }
    public BigDecimal getYearlySalary() { return yearlySalary; }
    public void setYearlySalary(BigDecimal yearlySalary) { this.yearlySalary = yearlySalary; }
    public PensionPlan getPensionPlan() { return pensionPlan; }
    public void setPensionPlan(PensionPlan pensionPlan) { this.pensionPlan = pensionPlan; }
}
