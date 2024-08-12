package bassit.admindatabaseapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int user_id;

    @Column
    private String username;

    @Column
    private String password_hash;

    @Column
    private String email;

    @Column
    private LocalDateTime created;

    public Users( String username, String password_hash, String email, LocalDateTime created) {
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
        this.created = created;
    }
}
