package e_commerce.e_commerce.token;



import e_commerce.e_commerce.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // Add this annotation
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    @Column(columnDefinition = "BIT")
    private boolean expired;

    @Column(columnDefinition = "BIT")
    private boolean revoked;


    @ManyToOne
   @JoinColumn(name="user_id")
    private User user;
    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", tokenType=" + tokenType +
                ", expired=" + expired +
                ", revoked=" + revoked +
                '}';
    }
}
