package com.example.demo.Service.JWTService;

import com.example.demo.Entity.doctor.doctorDetails;
import com.example.demo.Repository.doctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserdetailsService implements UserDetailsService {
    @Autowired
    private doctorDetailsRepository repo;
    public UserDetails loadUserByDoctorId(Integer doctor_id) throws UsernameNotFoundException {
        doctorDetails userInfo = repo.findById(doctor_id)
                // Add roles/authorities if needed
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + doctor_id));

        return User.withUsername(userInfo.getName())
                .password(userInfo.getPassword())
                // Add roles/authorities if needed
                .roles("USER")
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("This method is not supported. Use loadUserByDoctorId.");
    }
}
