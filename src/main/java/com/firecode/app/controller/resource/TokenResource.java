package com.firecode.app.controller.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
    
    @Value("${api.secure.https}")
    private boolean secure;

    @ApiOperation(value = "Logout")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Logout realizado"),
        @ApiResponse(code = 403, message = "Acesso Negado"),
        @ApiResponse(code = 500, message = "Erro no servidor"),})
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("secure: "+secure);
        
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(secure);
        cookie.setPath(request.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
