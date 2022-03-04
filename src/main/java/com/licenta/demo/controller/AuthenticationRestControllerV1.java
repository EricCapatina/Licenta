package com.licenta.demo.controller;

import com.licenta.demo.authentication.AuthenticationRequestDTO;
import com.licenta.demo.authentication.JwtTokenProvider;
import com.licenta.demo.database.entity.User;
import com.licenta.demo.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {
    @Value("${jwt.expiration}")
    private String jwtExpiration;

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserServiceImpl userService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            User user = userService.getUserByUsername(request.getUsername());

            if (user == null) {
                throw new BadCredentialsException("Invalid email/password combination");
            }

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String token = jwtTokenProvider.createToken(request.getUsername(), user.getRole().getName());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", request.getUsername());
            response.put("token", token);
            response.put("expirationToken", jwtExpiration);
            response.put("role", user.getRole().getName());
            response.put("permissions", user.getRole().getAuthorities().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registry(@RequestBody User user) {
        try {
            User createUser = userService.saveUser(user);
            String token = jwtTokenProvider.createToken(createUser.getUserName(), createUser.getRole().getName());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", createUser.getUserName());
            response.put("token", token);
            response.put("expirationToken", jwtExpiration);
            response.put("role", createUser.getRole().getName());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}