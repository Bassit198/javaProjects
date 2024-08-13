package bassit.admintest.repo;

import bassit.admintest.model.UserRolesModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRolesModel, Integer> {

    @Query(value = "SELECT * FROM admintest.user_roles_model WHERE userid = :userID", nativeQuery = true)
    List<UserRolesModel> findUsersGivenIDFromUserRole(@Param("userID") int userID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO admintest.user_roles_model (userid, roleid) VALUES (:fieldA, :fieldB)", nativeQuery = true)
    void insertUserRole(@Param("fieldA") int fieldA, @Param("fieldB") int fieldB);
}
