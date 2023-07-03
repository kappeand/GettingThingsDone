package ch.zhaw.students.gtd.boundary;

import ch.zhaw.students.gtd.security.TokenGenerator;
import ch.zhaw.students.gtd.security.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@CrossOrigin
public class TokenEndpoint {

    @Autowired
    private TokenGenerator tokenGenerator;

    @RequestMapping(path = "/auth/token", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public UserAuthResponse token(Principal principal, HttpServletResponse response) {
        return createTokenSetCookie(principal, response);
    }

    @RequestMapping(path = "/auth/refresh", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public UserAuthResponse refresh(Principal principal, HttpServletResponse response) {
        return createTokenSetCookie(principal, response);
    }

    @RequestMapping(path = "/auth/logout", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public void logout(Principal principal, HttpServletResponse response) {
        Cookie cookie = new Cookie("Authentication", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    private UserAuthResponse createTokenSetCookie(Principal principal, HttpServletResponse response) {
        String username = principal.getName();
        UserAuthResponse userAuthResponse = tokenGenerator.generateJWT(username);
        Cookie cookie = new Cookie("Authentication", userAuthResponse.getJwsToken());

        // This is essential! It must be HTTP-Only, otherwise it can be accessed by JavaScript
        cookie.setHttpOnly(true);

        cookie.setPath("/");
        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        return userAuthResponse;

    }

}