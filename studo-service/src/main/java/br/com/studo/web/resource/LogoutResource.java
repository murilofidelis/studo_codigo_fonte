package br.com.studo.web.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("/logout")
public class LogoutResource {

    @DeleteMapping("/revoke")
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.setStatus(HttpStatus.NO_CONTENT.value());

    }

}
