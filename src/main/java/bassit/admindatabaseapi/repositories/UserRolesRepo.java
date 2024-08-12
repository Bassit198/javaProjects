package bassit.admindatabaseapi.repositories;

import bassit.admindatabaseapi.model.UserRoles;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepo extends JpaRepository<UserRoles, Integer> {

    @Query(value = "SELECT * FROM UserRoles WHERE user_id = :userID", nativeQuery = true)
    List<UserRoles> findUserID(@Param("userID") int userID);

}
