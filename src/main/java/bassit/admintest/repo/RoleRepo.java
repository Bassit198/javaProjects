package bassit.admintest.repo;

import bassit.admintest.model.RoleModel;
import bassit.admintest.model.UserRolesModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Integer> {

    @Query(value = "SELECT * FROM admintest.role_model WHERE roleid = :roleID", nativeQuery = true)
    List<RoleModel> findRoleModelGivenRoleID(@Param("roleID") int roleID);

    /*@Modifying
    @Transactional
    @Query(value = "INSERT INTO admintest.user_roles_model (rolename, description, roleid) VALUES (:fieldA, :fieldB, :fieldC)", nativeQuery = true)
    void insertUserRole(@Param("fieldA") int fieldA, @Param("fieldB") int fieldB);*/

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO admintest.role_model(rolename, description, roleid) VALUES (:fieldA, :fieldB, :fieldC)", nativeQuery = true)
    void insertUserRole(@Param("fieldA") String fieldA, @Param("fieldB") String fieldB, @Param("fieldC") int fieldC);

}
