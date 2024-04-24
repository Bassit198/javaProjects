package bassit.employeetracker.admin.service;

import bassit.employeetracker.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAllByStatus(int status);

    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin> findAdminByUsername(String email);
    Optional<Admin> findAdminByEmployeeID(Long employeeID);

    Admin findByEmail(String email);

    Admin findByUsername(String username);
}
