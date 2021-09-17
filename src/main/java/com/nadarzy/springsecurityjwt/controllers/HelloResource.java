package com.nadarzy.springsecurityjwt.controllers;

import com.nadarzy.springsecurityjwt.models.AuthenticationRequest;
import com.nadarzy.springsecurityjwt.models.AuthenticationResponse;
import com.nadarzy.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Julian Nadarzy on 17/09/2021
 */
@RestController
public class HelloResource {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
@PostMapping("/authenticate")
public ResponseEntity<?> crreateAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    } catch (AuthenticationException e) {
       throw new Exception("incorrect username or pw",e);
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

   final String jwt = jwtUtil.generateToken(userDetails);

   return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
}
