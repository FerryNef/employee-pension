import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeePensionApp {
    public static void main(String[] args) throws Exception {
        List<Employee> employees = List.of(
                new Employee(1, "Daniel", "Agar", LocalDate.parse("2018-01-17"), new BigDecimal("105945.50"), null),
                new Employee(2, "Benard", "Shaw", LocalDate.parse("2018-10-03"), new BigDecimal("197750.00"),
                        new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), new BigDecimal("100.00"))),
                new Employee(3, "Carly", "Agar", LocalDate.parse("2014-05-16"), new BigDecimal("842000.75"),
                        new PensionPlan("SM2307", LocalDate.parse("2019-11-04"), new BigDecimal("1555.50"))),
                new Employee(4, "Wesley", "Schneider", LocalDate.parse("2018-11-02"), new BigDecimal("74500.00"), null),
                new Employee(7, "Elena", "Smith", LocalDate.parse("2022-08-15"), new BigDecimal("59000.00"), null)


        );

        printAllEmployees(employees);
        printQuarterlyUpcomingEnrollees(employees);
    }

    public static void printAllEmployees(List<Employee> employees) throws Exception {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparing(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());

        System.out.println("All Employees (JSON):");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sorted));
    }

    public static void printQuarterlyUpcomingEnrollees(List<Employee> employees) throws Exception {
        LocalDate now = LocalDate.now();
        int currentQuarter = (now.getMonthValue() - 1) / 3 + 1;
        int nextQuarter = currentQuarter == 4 ? 1 : currentQuarter + 1;
        int year = currentQuarter == 4 ? now.getYear() + 1 : now.getYear();

        LocalDate quarterStart = LocalDate.of(year, (nextQuarter - 1) * 3 + 1, 1);
        LocalDate quarterEnd = quarterStart.plusMonths(3).minusDays(1);

        List<Employee> enrollees = employees.stream()
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> {
                    LocalDate eligibilityDate = e.getEmploymentDate().plusYears(3);
                    return (!eligibilityDate.isBefore(quarterStart) && !eligibilityDate.isAfter(quarterEnd));
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        System.out.printf("Quarterly Upcoming Enrollees (%s to %s) JSON:\n", quarterStart, quarterEnd);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(enrollees));
    }
}
