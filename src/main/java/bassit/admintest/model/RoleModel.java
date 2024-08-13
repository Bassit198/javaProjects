package bassit.admintest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int roleid;

    @Column
    private String rolename;

    @Column
    private String description;

    //one user model can have multiple roles
    @OneToMany(mappedBy = "roleModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRolesModel> userRolesModels;
}
