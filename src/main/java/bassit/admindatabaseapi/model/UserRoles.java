package bassit.admindatabaseapi.model;

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
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int user_role_id;

    @Column
    private int user_id;

    @Column
    private int role_id;

    public UserRoles(int user_id, int role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }
}
