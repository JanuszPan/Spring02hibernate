package pl.coderslab.entity;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, unique = true)
    private String username;
    @Column(length = 100)
    private String password;
    private boolean enabled;
    @Column(length = 50, unique = true)
    private String email;
    @Transient
    private boolean accountNonExpired = true;
    @Transient
    private boolean accountNonLocked = true;
    @Transient
    private boolean credentialsNonExpired = true;
    @ManyToMany//(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();
}