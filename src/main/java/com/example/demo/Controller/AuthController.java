package com.example.demo.Controller;

import com.example.demo.JWT.JwtRequestDTO;
import com.example.demo.JWT.JwtResponseDTO;
import com.example.demo.JWT.JwtHelper;
import com.example.demo.Service.JWTService.UserdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserdetailsService userDetailsService; // typo in field name
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    public AuthController(UserdetailsService userDetailsService, AuthenticationManager manager, JwtHelper helper){
        this.userDetailsService = userDetailsService;
        this.helper = helper;
        this.manager = manager;
    }

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody JwtRequestDTO request) throws BadCredentialsException {
        try {
            // Perform authentication
            this.doAuthenticate(request.getDoctor_id(), request.getPassword());
            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByDoctorId(request.getDoctor_id());
            // Generate JWT token
            String token = helper.generateToken(userDetails);
            // Build response DTO
            JwtResponseDTO response = new JwtResponseDTO();
            response.setJwtToken(token);
            response.setDoctor_name(userDetails.getUsername()); // Assuming username holds the doctor's name
            return response;
        } catch (BadCredentialsException e) {
            // Handle authentication failure
          throw e;
        } catch (Exception e) {
            // Handle other exceptions
           throw e;
        }
    }

    private void doAuthenticate(int doctor_id, String password) {
        // You need to implement authentication logic here
        Integer doctorId = doctor_id;
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(doctorId, password);
        manager.authenticate(authentication);
    }
}
