package e_commerce.e_commerce.configu;


import e_commerce.e_commerce.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LogOutService implements LogoutHandler {

    private final TokenRepository tokenrepository;


    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        final String authHeader= request.getHeader("Authorization");
        final String jwt;
        if(authHeader==null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt= authHeader.substring(7);



        //var userConected= tokenrepository.findUserByToken(jwt);
        //System.out.println("userConected: " + userConected);
        var storedToken = tokenrepository.findByToken(jwt)
                .orElse(null);
        // Check if token is valid (not expired and not revoked) in the database
        System.out.println("storedToken: " + storedToken);

        if (storedToken != null ) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenrepository.save(storedToken);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            try {
                response.getWriter().write("{\"message\": \"Logout Success\"}");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
