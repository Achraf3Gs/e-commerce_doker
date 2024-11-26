package e_commerce.e_commerce.users;


import e_commerce.e_commerce.token.Token;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name= "_user")
public class User implements UserDetails {


    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String address;

    private String email;

    private String MobileNo;


    private String password;

    private String confirmPassword;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Token> tokens;




    public User(){}


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }




    @Override
    public String getPassword() {
        return password;
    }




    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' + // Add other relevant fields
                '}';
    }
}
