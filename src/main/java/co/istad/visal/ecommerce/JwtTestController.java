package co.istad.visal.ecommerce;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JwtTestController {

    @GetMapping("/security")
    public void getSecurity(Authentication auth){
        IO.println("Auth :" + auth);
        IO.println("Priniciple :" + auth.getPrincipal());
        IO.println("Authority :" + auth.getAuthorities());
    }

    @GetMapping("/jwt")
    public Map<String,Object> getJtw(@AuthenticationPrincipal Jwt jwt){
        IO.println("Access Token:" + jwt.getTokenValue());
        IO.println("Keycloak User Id:" + jwt.getSubject());
        IO.println(jwt.getClaim("name"));

        Map<String,Object> realmAccess = jwt.getClaim("realm_access");
        IO.println("Roles: " + realmAccess.get("roles"));

        return Map.of(
                "userId", jwt.getSubject(),
                "name",jwt.getClaim("name"),
                "roles",realmAccess.get("roles"))
        ;

    }
}
