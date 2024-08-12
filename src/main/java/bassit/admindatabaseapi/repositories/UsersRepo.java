package bassit.admindatabaseapi.repositories;

import bassit.admindatabaseapi.model.UserRoles;
import bassit.admindatabaseapi.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findUsersByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Users (username, password_hash, email, created) VALUES (:field1, :field2, :field3, :field4)", nativeQuery = true)
    void insertUser(@Param("field1") String field1, @Param("field2") String field2, @Param("field3") String field3, @Param("field4") LocalDateTime field4);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO UserRoles (user_id, role_id) VALUES (:fieldA, :fieldB)", nativeQuery = true)
    void insertUserRole(@Param("fieldA") int fieldA, @Param("fieldB") int fieldB);

    @Query(value = "SELECT * FROM Users WHERE user_id = :userID", nativeQuery = true)
    List<Users> findUsersGivenID(@Param("userID") int userID);












}
