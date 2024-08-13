package bassit.admintest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserRolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int userroleid;

    /*@Column
    private int roleid;*/

    //user id in roles are dependent on if the user is created
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private RoleModel roleModel;

}
