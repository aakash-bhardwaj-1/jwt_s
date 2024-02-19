package com.example.demo.JWT;

import lombok.Data;

@Data
public class JwtResponseDTO {
    private String doctor_name;
    private String jwtToken;
}
