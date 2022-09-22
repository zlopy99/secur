package com.sekurity.sek.controller;

import com.sekurity.sek.model.JwtRequest;
import com.sekurity.sek.model.JwtResponse;
import com.sekurity.sek.redis.RedisValueService;
import com.sekurity.sek.service.UserService;
import com.sekurity.sek.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisValueService redisValueService;

    @GetMapping("/home")
    public String home(){
        return "Wilcomen";
    }

    @GetMapping("/appartament")
    public String appartament(){
        return "Hello";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token
                = jwtUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
