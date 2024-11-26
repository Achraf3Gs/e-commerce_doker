package e_commerce.e_commerce.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import e_commerce.e_commerce.users.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String message;

    @JsonProperty("access_Token")
    private String accesstoken;

    private String name;

    private String address;

    private Role role;

    private Integer id;

    private Date accessTokenExpiration;



}