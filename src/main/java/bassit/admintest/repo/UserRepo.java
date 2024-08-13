package bassit.admintest.repo;

import bassit.admintest.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

    UserModel findUserModelByUsername(String username);

    UserModel findUserModelByUserid(int userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO adminTest.user_model (username, passwordhash, email, created) VALUES (:field1, :field2, :field3, :field4)", nativeQuery = true)
    void insertUser(@Param("field1") String field1, @Param("field2") String field2, @Param("field3") String field3, @Param("field4") LocalDateTime field4);
}
