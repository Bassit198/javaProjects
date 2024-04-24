package bassit.employeetracker.employee.service;

import bassit.employeetracker.admin.model.Admin;
import bassit.employeetracker.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Optional<Employee> findEmployeeByEmail(String email);

    Optional<Employee> findAdminByUsername(String username);

    Employee findByEmail(String email);

    Employee findByUsername(String username);

    List<Employee> findAllByStatus(Integer status);

    List<Employee> findAllByAdminAccess(Integer adminAccess);
}
