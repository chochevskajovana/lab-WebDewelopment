package mk.finki.ukim.web.lab.model;

import lombok.Data;
import mk.finki.ukim.web.lab.model.enumerations.Role;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "shop_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
//    private String name;
//    private String surname;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User(String username, String password, String name, String surname, LocalDate dateOfBirth, Role role) {
        this.username = username;
//        this.name = name;
//        this.surname = surname;
        userFullname = new UserFullname();
        userFullname.setName(name);
        userFullname.setSurname(surname);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
//        this.name = name;
//        this.surname = surname;
        userFullname = new UserFullname();
        userFullname.setName(name);
        userFullname.setSurname(surname);
        this.password = password;
        this.role = role;
    }

    public User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
