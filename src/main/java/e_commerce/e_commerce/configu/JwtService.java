package e_commerce.e_commerce.configu;


import e_commerce.e_commerce.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {



    private static final String SECRET_KEY="8ba5bcf689a7b166bdbf984fb4668ecce6070d01db7e7078d2994ffffac48034";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private User user;



    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }



    public String generateToken(User user) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId()+"");
        claims.put("name", user.getName());
        claims.put("address", user.getAddress());
        claims.put("role", user.getRole());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(String.format("%s",user.getEmail()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }



    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }


    boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            System.out.println("Error parsing JWT: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }


    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }





}