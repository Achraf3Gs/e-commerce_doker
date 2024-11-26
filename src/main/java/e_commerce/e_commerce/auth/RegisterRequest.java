package e_commerce.e_commerce.auth;



import e_commerce.e_commerce.users.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;

    private String address;

    private String email;

    private String password;

    private String confirmPassword;

    private Role role;

    public String getEmail() {
        return this.email;
    }



}
