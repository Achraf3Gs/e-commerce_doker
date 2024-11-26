package e_commerce.e_commerce.token;


import e_commerce.e_commerce.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {


    @Query("""
    select t from Token t inner join User u on t.user.id = u.id
    where u.id = :userId and (t.expired = false or t.revoked = false)
""")
    List<Token> findValidTokensByUser(Integer userId);


    @Query("""
        select u from User u  join u.tokens t where t.token = :token""")
    Optional<User> findUserByToken(String token);

    Optional<Token> findByToken(String token);
}

