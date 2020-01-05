package com.jan.testingoauth.controller;

import com.jan.testingoauth.response.AuthenticationResponse;
import com.jan.testingoauth.model.UserEntity;
import com.jan.testingoauth.response.ResponseDao;
import com.jan.testingoauth.service.MyUserDetailsService;
import com.jan.testingoauth.service.UserService;
import com.jan.testingoauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    // GET ALL USER
    @GetMapping("/user")
    public ResponseEntity<ResponseDao> getAllUser(){
        return userService.getAllUser();
    }

    // GET USER BY ID
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDao> getUserById(@PathVariable(value = "id") int id){
        return userService.getUserById(id);
    }

    // CREATE USER
    @PostMapping("/user")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        return userService.addUser(user);
    }

    // UPDATE DATA USER
    @PutMapping("/user/{id}/edit")
    public ResponseEntity<UserEntity> updateUser(@PathVariable(name = "id")int id, @RequestBody UserEntity user){
        return userService.updateUser(id,user);
    }

    // DELETE
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable(name = "id")int id){
        return userService.deleteUser(id);
    }

    // AUTHENTICATE
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserEntity authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            throw new Exception("incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
