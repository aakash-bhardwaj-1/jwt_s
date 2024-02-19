package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctorDetails")
public class PatientDetailsController {
    @GetMapping("/check")
    public String testApi(){
        return "Hello";
    }
}
