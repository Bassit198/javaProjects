package bassit.admintest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int userid;



    @Column
    private String username;

    @Column
    private String passwordhash;

    @Column
    private String email;

    @Column
    private LocalDateTime created;

    //one user model can have multiple roles
    @JsonIgnore
    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRolesModel> userRolesModels;

    public UserModel(String username, String password_hash, String email, LocalDateTime created) {
        this.username = username;
        this.passwordhash = password_hash;
        this.email = email;
        this.created = created;
    }

}
